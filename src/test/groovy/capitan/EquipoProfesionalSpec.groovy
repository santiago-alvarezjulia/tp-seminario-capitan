package capitan

import domain.Coordenadas
import domain.Profesional
import spock.lang.Specification

import java.time.LocalDateTime

class EquipoProfesionalSpec extends Specification {

    void "el porcentajePago vale 100 para EquipoProfesional"() {
        expect:"porcentajePago es 100"
        Profesional equipoProfesional = new Profesional()
        equipoProfesional.porcentajePago() == 100
    }
}
