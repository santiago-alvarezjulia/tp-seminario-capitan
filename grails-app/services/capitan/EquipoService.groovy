package capitan

import grails.gorm.transactions.Transactional

@Transactional
class EquipoService {

    Equipo crear(String nombre, String jerarquia) {
        Equipo equipo = new Equipo(
                nombre: nombre,
                saldo: PagoPartido.MONTO_FIJO_SIN_INTERES,
                jerarquia: jerarquia
        )
        equipo.save()
        equipo
    }

    Equipo editar(Integer id, BigDecimal saldo) {
        Equipo equipo = Equipo.get(id)
        equipo.nuevoSaldo(saldo)
        equipo
    }
}
