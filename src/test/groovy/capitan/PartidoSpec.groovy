package capitan

import capitan.helpers.CanchaHelper
import capitan.helpers.EquipoHelper
import capitan.helpers.PartidoHelper
import capitan.helpers.TorneoHelper
import exceptions.FechaDeCreacionPosteriorAInicio
import exceptions.NoPuedenJugarMismaJerarquia
import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.time.LocalDateTime

@Integration
@Rollback
class PartidoSpec extends Specification {

    @Autowired InscripcionTorneoService inscripcionTorneoService
    @Autowired PagoPartidoService pagoPartidoService

    void "fecha de creacion de partido posterior a la de inicio"() {
        expect:"lanza excepcion FechaDeCreacionPosteriorAInicio"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Torneo torneo = TorneoHelper.crearTorneo(2)
            torneo.save(failOnError:true)
            inscripcionTorneoService.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
            Equipo otroEquipo = EquipoHelper.crear("profesional")
            otroEquipo.save(failOnError:true)
            inscripcionTorneoService.inscribirEquipoEnTorneo(otroEquipo.id as Integer, torneo.id as Integer)
            Cancha cancha = CanchaHelper.crear()
            cancha.save(failOnError:true)
            PartidoHelper.crear(LocalDateTime.now().minusDays(1), equipo, otroEquipo, cancha)
        then:
            thrown FechaDeCreacionPosteriorAInicio
    }

    void "no pueden jugar entre si equipos de la misma jerarquia"() {
        expect:"lanza excepcion NoPuedenJugarMismaJerarquia"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Torneo torneo = TorneoHelper.crearTorneo(4)
            torneo.save(failOnError:true)
            inscripcionTorneoService.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
            Equipo otroEquipo = EquipoHelper.crear()
            otroEquipo.save(failOnError:true)
            inscripcionTorneoService.inscribirEquipoEnTorneo(otroEquipo.id as Integer, torneo.id as Integer)
            Cancha cancha = CanchaHelper.crear()
            cancha.save(failOnError:true)
            PartidoHelper.crear(LocalDateTime.now().plusDays(1), equipo, otroEquipo, cancha)
        then:
            thrown NoPuedenJugarMismaJerarquia
    }
}
