package capitan

class PagoPartido {

    private Equipo equipo
    private Partido partido

    private int MONTO_FIJO_SIN_INTERES = 10_000
    private int CIEN_PORCIENTO = 100

    private BigDecimal montoFijoSinInteres() {
        (MONTO_FIJO_SIN_INTERES * equipo.porcentajePago()) / CIEN_PORCIENTO
    }

    BigDecimal montoAPagar() {
        BigDecimal montoFijoSinInteres = montoFijoSinInteres()
        BigDecimal interes = PagoPartidoInteres.crearInteresPago(partido).interes(montoFijoSinInteres)
        montoFijoSinInteres + interes
    }
}
