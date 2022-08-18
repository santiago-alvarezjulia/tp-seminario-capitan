package capitan

import capitan.interfaces.IPartidoService
import grails.converters.JSON

class GolesJugadorController {
    private static String ID_PARTIDO_KEY = "id_partido"
    private static String ID_JUGADOR_KEY = "id_jugador"
    private static String ID_EQUIPO_KEY = "id_equipo"
    private static String CANTIDAD_GOLES_KEY = "cantidad_goles"

    IPartidoService partidoService

    def crear() {
        Integer idPartido= request.JSON.get(ID_PARTIDO_KEY)
        Integer idEquipo = request.JSON.get(ID_EQUIPO_KEY)
        Integer idJugador = request.JSON.get(ID_JUGADOR_KEY)
        Integer cantidadGoles = request.JSON.get(CANTIDAD_GOLES_KEY)
        GolesJugadorPartido golesJugadorPartido =
                partidoService.crearGolesJugadorPartido(idPartido, idEquipo, idJugador, cantidadGoles)
        render golesJugadorPartido as JSON
    }

    def golesJugadoresPartido() {
        render GolesJugadorPartido.getAll() as JSON
    }
}
