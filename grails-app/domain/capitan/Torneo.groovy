package capitan

import exceptions.CuposDisponiblesCompletos
import exceptions.EquipoNoInscriptoEnTorneo
import exceptions.EquipoYaInscripto
import exceptions.NoHayCupoParaJerarquia
import exceptions.YaExistePartidoDeEstosEquiposEnEsteTorneo

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

    void agregarPartido(Partido partido) {
        if (!haSidoInscriptoEnTorneo(partido.equipoLocal) || !haSidoInscriptoEnTorneo(partido.equipoVisitante))
            throw new EquipoNoInscriptoEnTorneo()
        if (yaJugaron(partido.equipoLocal, partido.equipoVisitante))
            throw new YaExistePartidoDeEstosEquiposEnEsteTorneo()
        addToPartidos(partido)
    }

    private Boolean yaJugaron(Equipo equipoLocal, Equipo equipoVisitante) {
        partidos.forEach { partido ->
            if (partido.esEntreEstosEquipos(equipoLocal, equipoVisitante))
                return true
        }
        false
    }

    void sumarPuntos(Equipo equipo, Integer cantidadGoles) {
        InscripcionTorneo inscripcionTorneo = inscripcionTorneoParaEquipo(equipo)
        Integer puntosPorGolesQueSeSuman = equipo.puntosPorGolesQueSeSuman(this, cantidadGoles)
        inscripcionTorneo.sumarPuntos(puntosPorGolesQueSeSuman)
    }

    InscripcionTorneo inscripcionTorneoParaEquipo(Equipo equipo) {
        inscripcionTorneos.find { it.equipo.id == equipo.id } as InscripcionTorneo
    }
}
