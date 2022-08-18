package capitan.helpers

import capitan.Partido
import capitan.Torneo

class TorneoHelper {
    static Torneo crearTorneo(Integer cupo) {
        Torneo torneo = new Torneo(cupoEquipos: cupo, puntosPorGolAmateur: 3, puntosPorGolProfesional: 1)
        torneo.inscripcionTorneos = []
        torneo.partidos = []
        torneo
    }
}
