package capitan

import domain.Amateur
import domain.Coordenadas
import spock.lang.Specification

import java.time.LocalDateTime

class EquipoAmateurSpec extends Specification {

    void "el porcentajePago vale 50 para EquipoAmateur"() {
        expect:"porcentajePago es 50"
        Amateur equipoAmateur = new Amateur()
        equipoAmateur.porcentajePago() == 50
    }
}
