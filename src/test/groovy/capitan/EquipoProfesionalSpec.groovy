package capitan

import domain.Coordenadas
import domain.Profesional
import spock.lang.Specification

import java.time.LocalDateTime

class EquipoProfesionalSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajePago vale 100 para EquipoProfesional"() {
        expect:"porcentajePago es 100"
        Profesional equipoProfesional = new Profesional()
        equipoProfesional.porcentajePago() == 100
    }

    void "el pago de un partido para un equipo profesional"() {
        expect:"el monto a pagar es 10000 y el saldo del equipo queda en 0"
        when:
            Equipo equipo = new Equipo(saldo: 10000, jerarquia: "amateur")
            Equipo equipo2 = new Equipo(saldo: 10000, jerarquia: "profesional")
            equipo.partidos = []
            equipo.pagoPartidos = []
            equipo2.partidos = []
            equipo2.pagoPartidos = []
            Coordenadas coordenadas = new Coordenadas(latitud: -50, longitud: -50)
            Cancha cancha = new Cancha(coordenadas: coordenadas)
            Partido partido = new Partido(LocalDateTime.now().plusDays(1), equipo, equipo2, cancha)
            equipo2.partidos << partido
            PagoPartido pagoPartido = equipo2.pagarPartido(partido)
        then:
            pagoPartido.monto == 10000
            equipo2.saldo == 0
    }
}
