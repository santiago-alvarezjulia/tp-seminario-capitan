package capitan

import exceptions.CuposDisponiblesCompletos
import exceptions.EquipoYaInscripto
import exceptions.NoHayCupoParaJerarquia

class Torneo {
    Integer cupoEquipos
    Integer puntosPorGolAmateur
    Integer puntosPorGolProfesional

    static hasMany = [partidos: Partido, inscripcionTorneos: InscripcionTorneo]

    InscripcionTorneo inscribirEquipo(Equipo equipo) {
        if (cuposDisponiblesCompletos())
            throw new CuposDisponiblesCompletos()
        if (haSidoInscriptoEnTorneo(equipo))
            throw new EquipoYaInscripto()
        if (noHayCupoParaJerarquia(equipo))
            throw new NoHayCupoParaJerarquia()
        new InscripcionTorneo(jerarquiaEquipoAlInscribirse: equipo.jerarquia)
    }

    private Boolean haSidoInscriptoEnTorneo(Equipo equipo) {
        inscripcionTorneos.any { inscripcionTorneo -> inscripcionTorneo.equipo.id == equipo.id }
    }

    private Boolean cuposDisponiblesCompletos() {
        cupoEquipos == inscripcionTorneos.size()
    }

    private Boolean noHayCupoParaJerarquia(Equipo equipo) {
        Integer inscripcionesMismaJerarquia = inscripcionTorneos.count {
            it.jerarquiaEquipoAlInscribirse == equipo.jerarquia
        }
        (cupoEquipos / 2) == inscripcionesMismaJerarquia
    }
}
