package capitan

import capitan.interfaces.ITorneoService
import grails.converters.JSON

class TorneoController {
    private static String CUPO_EQUIPOS_KEY = "cupo_equipos"
    private static String PUNTOS_POR_GOL_AMATEUR_KEY = "puntos_por_gol_amateur"
    private static String PUNTOS_POR_GOL_PROFESIONAL_KEY = "puntos_por_gol_profesional"

    ITorneoService torneoService

    def crear() {
        Integer cupoEquipos = request.JSON.get(CUPO_EQUIPOS_KEY)
        Integer puntosPorGolAmateur = request.JSON.get(PUNTOS_POR_GOL_AMATEUR_KEY)
        Integer puntosPorGolProfesional = request.JSON.get(PUNTOS_POR_GOL_PROFESIONAL_KEY)
        Torneo torneo = torneoService.crear(cupoEquipos, puntosPorGolAmateur, puntosPorGolProfesional)
        render torneo as JSON
    }
}
