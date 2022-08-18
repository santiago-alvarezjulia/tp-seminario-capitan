package capitan

import spock.lang.Specification

class InscripcionTorneoSpec extends Specification {

    void "sumar puntos a una inscripcion de torneo"() {
        expect:"sumar 3 puntos a una inscripcion con 0 puntos, queda una inscripcion con 3 puntos"
        when:
            InscripcionTorneo inscripcionTorneo = new InscripcionTorneo(puntaje: 0)
            inscripcionTorneo.sumarPuntos(3)
        then:
            inscripcionTorneo.puntaje == 3
    }
}
