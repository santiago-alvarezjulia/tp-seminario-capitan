package capitan

import capitan.interfaces.IJugadorService
import grails.converters.JSON

class JugadorController {
    private static String NOMBRE_KEY = "nombre"
    private static String ID_EQUIPO_KEY = "id_equipo"

    IJugadorService jugadorService

    def crear() {
        String nombre = request.JSON.get(NOMBRE_KEY)
        Integer idEquipo = request.JSON.get(ID_EQUIPO_KEY)
        Jugador jugador = jugadorService.crear(nombre, idEquipo)
        render jugador as JSON
    }
}
