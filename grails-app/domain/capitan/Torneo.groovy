package capitan

import exceptions.CuposDisponiblesCompletos
import exceptions.EquipoNoInscriptoEnTorneo
import exceptions.EquipoYaInscripto
import exceptions.NoHayCupoParaJerarquia
import exceptions.YaJugaronEntreSiEsteTorneo

class Torneo {
    Integer cupoEquipos
    Integer puntosPorGolAmateur
    Integer puntosPorGolProfesional

    static hasMany = [partidos: Partido, inscripcionTorneos: InscripcionTorneo]

    InscripcionTorneo inscribirEquipo(Equipo equipo) {
        if (cuposDisponiblesCompletos())
            throw new CuposDisponiblesCompletos()
        if (equipoEstaInscripto(equipo))
            throw new EquipoYaInscripto()
        if (noHayCupoParaJerarquia(equipo))
            throw new NoHayCupoParaJerarquia()
        new InscripcionTorneo(jerarquiaEquipoAlInscribirse: equipo.jerarquia)
    }

    private Boolean equipoEstaInscripto(Equipo equipo) {
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

    void agregarPartido(Partido partido, Equipo equipoLocal, Equipo equipoVisitante) {
        if (!equipoEstaInscripto(equipoLocal) || !equipoEstaInscripto(equipoVisitante))
            throw new EquipoNoInscriptoEnTorneo()
        if (yaJugaronEntreSiEsteTorneo(equipoLocal, equipoVisitante))
            throw new YaJugaronEntreSiEsteTorneo()
        addToPartidos(partido)
    }

    private Boolean yaJugaronEntreSiEsteTorneo(Equipo equipoLocal, Equipo equipoVisitante) {
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
