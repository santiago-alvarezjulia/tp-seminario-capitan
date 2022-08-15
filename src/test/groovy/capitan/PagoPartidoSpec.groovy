package capitan

import spock.lang.Specification

import java.time.LocalDateTime

class PagoPartidoSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "monto cuando t <= 24hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto del pago es 5_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        EquipoAmateur equipoAmateur = new EquipoAmateur(saldo: 50_000)
        equipoAmateur.pagarPartido(partido).monto == 5_000
    }

    void "monto cuando 24hs < t < 72hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto del pago es 5_250"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        EquipoAmateur equipoAmateur = new EquipoAmateur(saldo: 50_000)
        equipoAmateur.pagarPartido(partido).monto == 5_250
    }

    void "monto cuando t >= 72hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto del pago es 5_500"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        EquipoAmateur equipoAmateur = new EquipoAmateur(saldo: 50_000)
        equipoAmateur.pagarPartido(partido).monto == 5_500
    }

    void "monto cuando t <= 24hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto del pago es 10_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        EquipoProfesional equipoProfesional = new EquipoProfesional(saldo: 50_000)
        equipoProfesional.pagarPartido(partido).monto == 10_000
    }

    void "monto cuando 24hs < t < 72hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto del pago es 10_500"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        EquipoProfesional equipoProfesional = new EquipoProfesional(saldo: 50_000)
        equipoProfesional.pagarPartido(partido).monto == 10_500
    }

    void "monto cuando t >= 72hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto del pago es 11_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        EquipoProfesional equipoProfesional = new EquipoProfesional(saldo: 50_000)
        equipoProfesional.pagarPartido(partido).monto == 11_000
    }
}