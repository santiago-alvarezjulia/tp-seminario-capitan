package capitan.helpers

import capitan.Cancha
import capitan.Equipo
import capitan.PagoPartido
import capitan.Partido

import java.time.LocalDateTime

class PartidoHelper {
    static Partido crear(LocalDateTime fechaInicio, Equipo equipoLocal, Equipo equipoVisitante, Cancha cancha) {
        Partido partido = new Partido(fechaInicio, equipoLocal, equipoVisitante, cancha)
        partido.pagoPartidos = []
        partido.golesJugadoresPartido = []
        partido
    }
}
