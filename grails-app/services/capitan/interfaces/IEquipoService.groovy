package capitan.interfaces

import capitan.Equipo

interface IEquipoService {
    Equipo crear(String nombre, String jerarquia)
    Equipo cambiarSaldo(Integer id, BigDecimal nuevoSaldo)
}