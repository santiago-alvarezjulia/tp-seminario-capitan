package capitan

import capitan.helpers.TorneoHelper
import exceptions.CupoEquiposNoEsParMayorACero
import spock.lang.Specification

class TorneoSpec extends Specification {

    void "cupo equipos 0 para el torneo"() {
        expect:"lanza excepcion CupoEquiposNoEsParMayorACero"
        when:
            TorneoHelper.crearTorneo(0)
        then:
            thrown CupoEquiposNoEsParMayorACero
    }

    void "cupo equipos 3 para el torneo"() {
        expect:"lanza excepcion CupoEquiposNoEsParMayorACero"
        when:
            TorneoHelper.crearTorneo(3)
        then:
            thrown CupoEquiposNoEsParMayorACero
    }

    void "cupo equipos 2 para el torneo"() {
        expect:"lanza excepcion CupoEquiposNoEsParMayorACero"
        when:
            Torneo torneo = TorneoHelper.crearTorneo(2)
        then:
            torneo.cupoEquipos == 2
    }
}
