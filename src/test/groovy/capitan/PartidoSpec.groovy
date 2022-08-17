package capitan

import domain.Coordenadas
import exceptions.FechaDeCreacionPosteriorAInicio
import exceptions.NoPuedenJugarMismaJerarquia
import spock.lang.Specification

import java.time.LocalDateTime

class PartidoSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "fecha de creacion de partido posterior a la de inicio"() {
        expect:"lanza excepcion FechaDeCreacionPosteriorAInicio"
        when:
            Equipo equipo = new Equipo(saldo: 10000, jerarquia: "amateur")
            Equipo equipo2 = new Equipo(saldo: 10000, jerarquia: "profesional")
            equipo.partidos = []
            equipo.pagoPartidos = []
            equipo2.partidos = []
            equipo2.pagoPartidos = []
            Coordenadas coordenadas = new Coordenadas(latitud: -50, longitud: -50)
            Cancha cancha = new Cancha(coordenadas: coordenadas)
            new Partido(LocalDateTime.now().minusDays(1), equipo, equipo2, cancha)
        then:
            thrown FechaDeCreacionPosteriorAInicio
    }

    void "no pueden jugar entre si equipos de la misma jerarquia"() {
        expect:"lanza excepcion NoPuedenJugarMismaJerarquia"
        when:
            Equipo equipo = new Equipo(saldo: 10000, jerarquia: "amateur")
            Equipo equipo2 = new Equipo(saldo: 10000, jerarquia: "amateur")
            equipo.partidos = []
            equipo.pagoPartidos = []
            equipo2.partidos = []
            equipo2.pagoPartidos = []
            Coordenadas coordenadas = new Coordenadas(latitud: -50, longitud: -50)
            Cancha cancha = new Cancha(coordenadas: coordenadas)
            new Partido(LocalDateTime.now().plusDays(1), equipo, equipo2, cancha)
        then:
            thrown NoPuedenJugarMismaJerarquia
    }
}
