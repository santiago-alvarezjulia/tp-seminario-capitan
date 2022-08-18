package capitan

import capitan.interfaces.IEquipoService
import grails.gorm.transactions.Transactional

@Transactional
class EquipoService implements IEquipoService {

    Equipo crear(String nombre, String jerarquia) {
        Equipo equipo = new Equipo(
                nombre: nombre,
                saldo: PagoPartido.MONTO_FIJO_SIN_INTERES,
                jerarquia: jerarquia
        )
        equipo.save(failOnError:true)
        equipo
    }

    Equipo cambiarSaldo(Integer id, BigDecimal nuevoSaldo) {
        Equipo equipo = Equipo.get(id)
        equipo.nuevoSaldo(nuevoSaldo)
        equipo
    }
}
