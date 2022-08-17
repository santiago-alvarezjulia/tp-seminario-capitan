package capitan

import domain.Amateur
import spock.lang.Specification

class EquipoAmateurSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajePago vale 50 para EquipoAmateur"() {
        expect:"porcentajePago es 50"
        Amateur equipoAmateur = new Amateur()
        equipoAmateur.porcentajePago() == 50
    }
}
