package capitan

class PagoPartido {

    private Equipo equipo
    private Partido partido
    BigDecimal monto

    private static int MONTO_FIJO_SIN_INTERES = 10_000
    private static int CIEN_PORCIENTO = 100

    static BigDecimal montoAPagar(Partido partido, Equipo equipo) {
        BigDecimal montoFijoSinInteres = (MONTO_FIJO_SIN_INTERES * equipo.porcentajePago()) / CIEN_PORCIENTO
        BigDecimal interes = PagoPartidoInteres.crearInteresPago(partido).interes(montoFijoSinInteres)
        montoFijoSinInteres + interes
    }
}
