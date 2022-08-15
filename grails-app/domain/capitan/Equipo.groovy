package capitan

import exceptions.SaldoInsuficiente

abstract class Equipo {

    BigDecimal saldo
    List<PagoPartido> pagoPartidos = []

    abstract BigDecimal porcentajePago()

    PagoPartido pagarPartido(Partido partido) {
        BigDecimal montoAPagar = PagoPartido.montoAPagar(partido, this)
        if (montoAPagar > saldo)
            throw new SaldoInsuficiente()
        saldo -= montoAPagar
        PagoPartido pagoPartido = new PagoPartido(equipo: this, partido: partido, monto: montoAPagar)
        pagoPartidos << pagoPartido
        pagoPartido
    }
}
