package capitan

import capitan.interfaces.IJugadorService
import grails.converters.JSON

class JugadorController {
    private static String NOMBRE_KEY = "nombre"
    private static String ID_EQUIPO_KEY = "id_equipo"
    private static String ID_JUGADOR_KEY = "id_jugador"
    private static String ID_EQUIPO_ACTUAL_KEY = "id_equipo_actual"
    private static String ID_EQUIPO_NUEVO_KEY = "id_equipo_nuevo"

    IJugadorService jugadorService

    def crear() {
        String nombre = request.JSON.get(NOMBRE_KEY)
        Integer idEquipo = request.JSON.get(ID_EQUIPO_KEY)
        Jugador jugador = jugadorService.crear(nombre, idEquipo)
        render jugador as JSON
    }

    def transferir() {
        Integer idJugador = request.JSON.get(ID_JUGADOR_KEY)
        Integer idEquipoActual = request.JSON.get(ID_EQUIPO_ACTUAL_KEY)
        Integer idEquipoNuevo = request.JSON.get(ID_EQUIPO_NUEVO_KEY)
        Jugador jugador = jugadorService.transferir(idJugador, idEquipoActual, idEquipoNuevo)
        render jugador as JSON
    }

    def jugadores() {
        render Jugador.getAll() as JSON
    }
}
