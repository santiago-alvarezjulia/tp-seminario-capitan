package capitan

import exceptions.JerarquiaDesconocida
import exceptions.SaldoInsuficiente

class Equipo {

    String nombre
    BigDecimal saldo
    String jerarquia

    static hasMany = [pagoPartidos: PagoPartido]

    private static String AMATEUR = "amateur"
    private static String PROFESIONAL = "profesional"

    PagoPartido pagarPartido(Partido partido) {
        BigDecimal montoAPagar = PagoPartido.montoAPagar(partido, this)
        if (montoAPagar > saldo)
            throw new SaldoInsuficiente()
        saldo -= montoAPagar
        PagoPartido pagoPartido = new PagoPartido(monto: montoAPagar)
        pagoPartido
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
}
