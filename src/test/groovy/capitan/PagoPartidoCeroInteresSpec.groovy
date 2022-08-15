package capitan

import domain.PagoPartidoCeroInteres
import spock.lang.Specification

class PagoPartidoCeroInteresSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajeInteres vale 0% para PagoPartidoCeroInteres"() {
        expect:"porcentajeInteres es 5"
        PagoPartidoCeroInteres pagoPartidoCeroInteres = new PagoPartidoCeroInteres()
        pagoPartidoCeroInteres.porcentajeInteres() == 0
    }

    void "el interes vale 0 para cualquier monto fijo para PagoPartidoCeroInteres"() {
        expect:"interes para 1000 es 0"
        PagoPartidoCeroInteres pagoParticoCeroInteres = new PagoPartidoCeroInteres()
        pagoParticoCeroInteres.interes(1000 as BigDecimal) == 0
    }
}
