package capitan

import domain.Amateur
import domain.Profesional
import spock.lang.Specification

class JerarquiaSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "cantidad de puntos para jerarquia amateur"() {
        expect:"3 puntos por gol amateur, 2 goles entonces 6 puntos"
        when:
        Amateur amateur = new Amateur()
        Torneo torneo = new Torneo(puntosPorGolAmateur: 3)
        Integer puntos = amateur.puntosPorGolesQueSeSuman(torneo, 2)
        then:
        puntos == 6
    }

    void "cantidad de puntos para jerarquia profesional"() {
        expect:"1 punto por gol profesional, 9 goles entonces 9 puntos"
        when:
        Profesional profesional = new Profesional()
        Torneo torneo = new Torneo(puntosPorGolProfesional: 1)
        Integer puntos = profesional.puntosPorGolesQueSeSuman(torneo, 9)
        then:
        puntos == 9
    }
}
