package capitan

import spock.lang.Specification

class PagoPartidoDiezInteresSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajeInteres vale 10% para PagoPartidoDiezInteres"() {
        expect:"porcentajeInteres es 10"
        PagoPartidoDiezInteres pagoPartidoDiezInteres = new PagoPartidoDiezInteres()
        pagoPartidoDiezInteres.porcentajeInteres() == 10
    }

    void "el interes vale el 10% para cualquier monto fijo para PagoPartidoDiezInteres"() {
        expect:"interes para 1000 es 100"
        PagoPartidoDiezInteres pagoPartidoDiezInteres = new PagoPartidoDiezInteres()
        pagoPartidoDiezInteres.interes(1000 as BigDecimal) == 100
    }
}