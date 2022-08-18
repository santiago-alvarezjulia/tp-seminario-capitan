package capitan

import capitan.helpers.EquipoHelper
import capitan.helpers.JugadorHelper
import exceptions.JugadorNoEsParteDelEquipo
import exceptions.SaldoInsuficiente
import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class JugadorServiceSpec extends Specification {

    @Autowired JugadorService jugadorService

    void "transferir un jugador desde un equipo con saldo menor a 1000"() {
        expect:"lanza excepcion SaldoInsuficiente"
        when:
            Equipo equipo = EquipoHelper.crear("amateur", 0)
            equipo.save(failOnError:true)
            Jugador jugador = JugadorHelper.crear()
            jugador.save(failOnError: true)
            equipo.addToJugadores(jugador)
            Equipo equipo2 = EquipoHelper.crear()
            equipo2.save(failOnError:true)
            jugadorService.transferir(jugador.id as Integer, equipo.id as Integer, equipo2.id as Integer)
        then:
            thrown SaldoInsuficiente
    }

    void "transferir un jugador hacia un equipo con saldo menor a 1000"() {
        expect:"lanza excepcion SaldoInsuficiente"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Jugador jugador = JugadorHelper.crear()
            jugador.save(failOnError: true)
            equipo.addToJugadores(jugador)
            Equipo equipo2 = EquipoHelper.crear("amateur", 0)
            equipo2.save(failOnError:true)
            jugadorService.transferir(jugador.id as Integer, equipo.id as Integer, equipo2.id as Integer)
        then:
            thrown SaldoInsuficiente
    }

    void "transferir un jugador desde un equipo del cual no es parte"() {
        expect:"lanza excepcion JugadorNoEsParteDelEquipo"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Jugador jugador = JugadorHelper.crear()
            jugador.save(failOnError: true)
            Equipo equipo2 = EquipoHelper.crear()
            equipo2.save(failOnError:true)
            jugadorService.transferir(jugador.id as Integer, equipo.id as Integer, equipo2.id as Integer)
        then:
            thrown JugadorNoEsParteDelEquipo
    }
}
