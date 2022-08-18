package capitan.helpers

import capitan.Cancha
import capitan.Equipo
import capitan.PagoPartido
import capitan.Partido

import java.time.LocalDateTime

class PartidoHelper {
    static Partido crear(Equipo equipoLocal, Equipo equipoVisitante, Cancha cancha) {
        Partido partido = new Partido(LocalDateTime.now().plusDays(1), equipoLocal, equipoVisitante, cancha)
        partido.pagoPartidos = []
        partido.golesJugadoresPartido = []
        partido
    }
}
