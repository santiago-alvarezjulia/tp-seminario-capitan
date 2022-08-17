package capitan

import domain.Amateur
import domain.Coordenadas
import spock.lang.Specification

import java.time.LocalDateTime

class EquipoAmateurSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajePago vale 50 para EquipoAmateur"() {
        expect:"porcentajePago es 50"
        Amateur equipoAmateur = new Amateur()
        equipoAmateur.porcentajePago() == 50
    }

    void "el pago de un partido para un equipo amateur"() {
        expect:"el monto a pagar es 5000 y el saldo del equipo queda en 5000"
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
            equipo.partidos << partido
            PagoPartido pagoPartido = equipo.pagarPartido(partido)
        then:
            pagoPartido.monto == 5000
            equipo.saldo == 5000
    }
}
