package capitan

import domain.PagoPartidoCincoInteres
import spock.lang.Specification

class PagoPartidoCincoInteresSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajeInteres vale 5% para PagoPartidoCincoInteres"() {
        expect:"porcentajeInteres es 5"
        PagoPartidoCincoInteres pagoPartidoCincoInteres = new PagoPartidoCincoInteres()
        pagoPartidoCincoInteres.porcentajeInteres() == 5
    }

    void "el interes vale el 5% para cualquier monto fijo para PagoPartidoCincoInteres"() {
        expect:"interes para 1000 es 50"
        PagoPartidoCincoInteres pagoPartidoCincoInteres = new PagoPartidoCincoInteres()
        pagoPartidoCincoInteres.interes(1000 as BigDecimal) == 50
    }
}
