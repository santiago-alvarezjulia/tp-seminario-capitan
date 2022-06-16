package capitan

import spock.lang.Specification

import java.time.LocalDateTime

class PagoPartidoSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "montoAPagar cuando t <= 24hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto a pagar es 5_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        EquipoAmateur equipoAmateur = new EquipoAmateur()
        PagoPartido pagoPartido = new PagoPartido(equipo: equipoAmateur, partido: partido)
        pagoPartido.montoAPagar() == 5_000
    }

    void "montoAPagar cuando 24hs < t < 72hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto a pagar es 5_250"
        EquipoAmateur equipoAmateur = new EquipoAmateur()
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        PagoPartido pagoPartido = new PagoPartido(equipo: equipoAmateur, partido: partido)
        pagoPartido.montoAPagar() == 5_250
    }

    void "montoAPagar cuando t >= 72hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto a pagar es 5_500"
        EquipoAmateur equipoAmateur = new EquipoAmateur()
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        PagoPartido pagoPartido = new PagoPartido(equipo: equipoAmateur, partido: partido)
        pagoPartido.montoAPagar() == 5_500
    }

    void "montoAPagar cuando t <= 24hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto a pagar es 10_000"
        EquipoProfesional equipoProfesional = new EquipoProfesional()
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        PagoPartido pagoPartido = new PagoPartido(equipo: equipoProfesional, partido: partido)
        pagoPartido.montoAPagar() == 10_000
    }

    void "montoAPagar cuando 24hs < t < 72hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto a pagar es 10_500"
        EquipoProfesional equipoProfesional = new EquipoProfesional()
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        PagoPartido pagoPartido = new PagoPartido(equipo: equipoProfesional, partido: partido)
        pagoPartido.montoAPagar() == 10_500
    }

    void "montoAPagar cuando t >= 72hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto a pagar es 11_000"
        EquipoProfesional equipoProfesional = new EquipoProfesional()
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        PagoPartido pagoPartido = new PagoPartido(equipo: equipoProfesional, partido: partido)
        pagoPartido.montoAPagar() == 11_000
    }
}