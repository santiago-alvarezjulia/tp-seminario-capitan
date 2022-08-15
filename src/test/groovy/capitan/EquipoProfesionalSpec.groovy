package capitan


import spock.lang.Specification

class EquipoProfesionalSpec extends Specification {

    def setup() {}

    def cleanup() {}

    void "el porcentajePago vale 100 para EquipoProfesional"() {
        expect:"porcentajePago es 100"
        Profesional equipoProfesional = new Profesional()
        equipoProfesional.porcentajePago() == 100
    }
}
