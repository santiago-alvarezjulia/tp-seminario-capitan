package capitan

import grails.converters.JSON

class PartidoController {
    private static String FECHA_INICIO_KEY = "fecha_inicio"

    def crear() {
        Long fechaInicio = request.JSON.get(FECHA_INICIO_KEY)
        Partido partido = new PartidoService().crear(fechaInicio)
        render partido as JSON
    }
}
