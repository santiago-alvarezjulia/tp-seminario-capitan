package capitan

import grails.converters.JSON

class PagoPartidoController {
    private static String ID_EQUIPO_KEY = "id_equipo"
    private static String ID_PARTIDO_KEY = "id_partido"

    def crear() {
        Integer idEquipo = request.JSON.get(ID_EQUIPO_KEY)
        Integer idPartido = request.JSON.get(ID_PARTIDO_KEY)
        PagoPartido pagoPartido = new PagoPartidoService().crear(idEquipo, idPartido)
        render pagoPartido as JSON
    }
}
