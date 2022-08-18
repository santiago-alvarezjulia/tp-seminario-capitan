package capitan

import capitan.interfaces.ICanchaService
import grails.converters.JSON

class CanchaController {
    private static String LATITUD_KEY = "latitud"
    private static String LONGITUD_KEY = "longitud"

    ICanchaService canchaService

    def crear() {
        Float latitud = request.JSON.get(LATITUD_KEY)
        Float longitud = request.JSON.get(LONGITUD_KEY)
        Cancha cancha = canchaService.crear(latitud, longitud)
        render cancha as JSON
    }

    def canchas() {
        render Cancha.getAll() as JSON
    }
}
