package capitan

import grails.converters.JSON

class EquipoController {
    private static String NOMBRE_KEY = "nombre"
    private static String JERARQUIA_KEY = "jerarquia"

    def crear() {
        String nombre = request.JSON.get(NOMBRE_KEY)
        String jerarquia = request.JSON.get(JERARQUIA_KEY)
        Equipo equipo = new EquipoService().crear(nombre, jerarquia)
        render equipo as JSON
    }
}
