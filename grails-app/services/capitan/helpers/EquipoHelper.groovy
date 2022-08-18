package capitan.helpers

import capitan.Equipo

class EquipoHelper {
    static Equipo crear(String jerarquia = "amateur", Integer saldo = 10000) {
        Equipo equipo = new Equipo(nombre: "Falso equipo", saldo: saldo, jerarquia: jerarquia)
        equipo.partidos = []
        equipo.pagoPartidos = []
        equipo
    }
}
