package capitan

import exceptions.CantidadDeGolesNoEsMayorQueCero
import spock.lang.Specification

class GolesJugadorPartidoSpec extends Specification {

    void "cero goles para el jugador en el partido"() {
        expect:"lanza excepcion CantidadDeGolesNoEsMayorQueCero"
        when:
            new GolesJugadorPartido(0)
        then:
            thrown CantidadDeGolesNoEsMayorQueCero
    }

    void "tres goles para el jugador en el partido"() {
        expect:"la cantidad de goles sea 3"
        when:
            GolesJugadorPartido golesJugadorPartido = new GolesJugadorPartido(3)
        then:
            golesJugadorPartido.cantidad == 3
    }
}
