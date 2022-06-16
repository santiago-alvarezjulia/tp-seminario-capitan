package capitan

import spock.lang.Specification

class EquipoAmateurSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajePago vale 50 para EquipoAmateur"() {
        expect:"porcentajePago es 50"
        EquipoAmateur equipoAmateur = new EquipoAmateur()
        equipoAmateur.porcentajePago() == 50
    }
}
