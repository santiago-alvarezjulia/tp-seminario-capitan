package capitan.helpers

import capitan.Partido
import capitan.Torneo

class TorneoHelper {
    static Torneo crearTorneo(Integer cupo) {
        Torneo torneo = new Torneo(cupo)
        torneo.inscripcionTorneos = []
        torneo.partidos = []
        torneo
    }
}
