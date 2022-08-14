package capitan

import spock.lang.Specification

import java.time.LocalDateTime

class PagoPartidoInteresSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajeInteres vale 0 para PagoPartidoInteres cuando t <= 24hs desde el inicio del partido"() {
        expect:"porcentajeInteres es 0"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        PagoPartidoInteres pagoPartidoInteres = PagoPartidoInteres.crearInteresPago(partido)
        pagoPartidoInteres.porcentajeInteres() == 0
    }

    void "el porcentajeInteres vale 5 para PagoPartidoInteres cuando 24hs < t < 72hs desde el inicio del partido"() {
        expect:"porcentajeInteres es 5"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        PagoPartidoInteres pagoPartidoInteres = PagoPartidoInteres.crearInteresPago(partido)
        pagoPartidoInteres.porcentajeInteres() == 5
    }

    void "el porcentajeInteres vale 10 para PagoPartidoInteres cuando t >= 72hs desde el inicio del partido"() {
        expect:"porcentajeInteres es 10"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        PagoPartidoInteres pagoPartidoInteres = PagoPartidoInteres.crearInteresPago(partido)
        pagoPartidoInteres.porcentajeInteres() == 10
    }
}
