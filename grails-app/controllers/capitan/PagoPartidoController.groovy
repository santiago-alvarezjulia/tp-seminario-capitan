package capitan

import capitan.interfaces.IPagoPartidoService
import grails.converters.JSON

class PagoPartidoController {
    private static String ID_EQUIPO_KEY = "id_equipo"
    private static String ID_PARTIDO_KEY = "id_partido"

    IPagoPartidoService pagoPartidoService

    def crear() {
        Integer idEquipo = request.JSON.get(ID_EQUIPO_KEY)
        Integer idPartido = request.JSON.get(ID_PARTIDO_KEY)
        PagoPartido pagoPartido = pagoPartidoService.crearPagoDePartidoPorParteDeEquipo(idEquipo, idPartido)
        render pagoPartido as JSON
    }

    def pagosPartido() {
        render PagoPartido.getAll() as JSON
    }
}
