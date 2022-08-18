package capitan

import capitan.interfaces.ITorneoService
import grails.converters.JSON

class TorneoController {
    private static String CUPO_EQUIPOS_KEY = "cupo_equipos"

    ITorneoService torneoService

    def crear() {
        Integer cupoEquipos = request.JSON.get(CUPO_EQUIPOS_KEY)
        Torneo torneo = torneoService.crear(cupoEquipos)
        render torneo as JSON
    }

    def torneos() {
        render Torneo.getAll() as JSON
    }
}
