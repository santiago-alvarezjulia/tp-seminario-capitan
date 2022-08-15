package capitan

import capitan.interfaces.IPartidoService
import grails.converters.JSON

class PartidoController {
    private static String FECHA_INICIO_KEY = "fecha_inicio"

    IPartidoService partidoService

    def crear() {
        Long fechaInicio = request.JSON.get(FECHA_INICIO_KEY)
        Partido partido = partidoService.crear(fechaInicio)
        render partido as JSON
    }
}
