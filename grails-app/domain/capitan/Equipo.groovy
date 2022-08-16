package capitan

import exceptions.EquipoDebePagarTodosSusPartidos
import exceptions.EquipoYaInscripto
import exceptions.JerarquiaDesconocida
import exceptions.PartidoYaPagado
import exceptions.SaldoInsuficiente

class Equipo {

    String nombre
    BigDecimal saldo
    String jerarquia

    static hasMany = [partidos: Partido, pagoPartidos: PagoPartido, inscripcionTorneos: InscripcionTorneo]

    private static String AMATEUR = "amateur"
    private static String PROFESIONAL = "profesional"

    Boolean haPagadoElPartido(Partido partido) {
        pagoPartidos.any { pagoPartido -> pagoPartido.partido.id == partido.id }
    }

    Boolean noHaPagadoTodosSusPartidos() {
        partidos.size() != pagoPartidos.size()
    }

    PagoPartido pagarPartido(Partido partido) {
        BigDecimal montoAPagar = PagoPartido.montoAPagar(partido, this)
        if (montoAPagar > saldo)
            throw new SaldoInsuficiente()
        if (haPagadoElPartido(partido))
            throw new PartidoYaPagado()
        saldo -= montoAPagar
        new PagoPartido(monto: montoAPagar)
    }

    InscripcionTorneo inscribirEnTorneo(Torneo torneo) {
        if (noHaPagadoTodosSusPartidos())
            throw new EquipoDebePagarTodosSusPartidos()
        torneo.inscribirEquipo(this)
    }

    BigDecimal porcentajePago() {
        switch (jerarquia) {
            case AMATEUR:
                50
                break
            case PROFESIONAL:
                100
                break
            default:
                throw new JerarquiaDesconocida()
        }
    }

    void nuevoSaldo(BigDecimal nuevoSaldo) {
        setSaldo nuevoSaldo
    }

    Boolean tieneLaMismaJerarquia(Equipo equipo) {
        jerarquia == equipo.jerarquia
    }
}
