package capitan

import domain.PagoPartidoInteres

class PagoPartido {

    BigDecimal monto
    static belongsTo = [equipo: Equipo, partido: Partido]

    static int MONTO_FIJO_SIN_INTERES = 10_000
    private static int CIEN_PORCIENTO = 100

    static BigDecimal montoAPagar(Partido partido, Equipo equipo) {
        BigDecimal montoFijoSinInteres = (MONTO_FIJO_SIN_INTERES * equipo.porcentajePago()) / CIEN_PORCIENTO
        BigDecimal interes = PagoPartidoInteres.crearInteresPago(partido).interes(montoFijoSinInteres)
        montoFijoSinInteres + interes
    }
}
