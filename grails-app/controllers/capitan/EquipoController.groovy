package capitan

import grails.converters.JSON

class EquipoController {
    private static String NOMBRE_KEY = "nombre"
    private static String JERARQUIA_KEY = "jerarquia"
    private static String SALDO_KEY = "saldo"

    def crear() {
        String nombre = request.JSON.get(NOMBRE_KEY)
        String jerarquia = request.JSON.get(JERARQUIA_KEY)
        Equipo equipo = new EquipoService().crear(nombre, jerarquia)
        render equipo as JSON
    }

    def editar(Integer id) {
        BigDecimal saldo = request.JSON.get(SALDO_KEY)
        Equipo equipo = new EquipoService().editar(id, saldo)
        render equipo as JSON
    }
}
