package capitan

import capitan.interfaces.IEquipoService
import grails.converters.JSON

class EquipoController {
    private static String NOMBRE_KEY = "nombre"
    private static String JERARQUIA_KEY = "jerarquia"
    private static String SALDO_KEY = "saldo"

    IEquipoService equipoService

    def crear() {
        String nombre = request.JSON.get(NOMBRE_KEY)
        String jerarquia = request.JSON.get(JERARQUIA_KEY)
        Equipo equipo = equipoService.crear(nombre, jerarquia)
        render equipo as JSON
    }

    def editar(Integer id) {
        BigDecimal saldo = request.JSON.get(SALDO_KEY)
        Equipo equipo = equipoService.cambiarSaldo(id, saldo)
        render equipo as JSON
    }
}
