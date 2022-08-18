package capitan.helpers

import capitan.Equipo

class EquipoHelper {
    static Equipo crear(String jerarquia = "amateur") {
        Equipo equipo = new Equipo(nombre: "Falso equipo", saldo: 10000, jerarquia: jerarquia)
        equipo.partidos = []
        equipo.pagoPartidos = []
        equipo
    }
}
