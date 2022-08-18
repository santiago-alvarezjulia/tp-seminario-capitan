package capitan

import capitan.helpers.CanchaHelper
import capitan.helpers.EquipoHelper
import capitan.helpers.PartidoHelper
import capitan.helpers.TorneoHelper
import exceptions.CuposDisponiblesCompletos
import exceptions.EquipoDebePagarTodosSusPartidos
import exceptions.EquipoYaInscripto
import exceptions.NoHayCupoParaJerarquia
import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.*
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@Integration
@Rollback
class InscripcionTorneoServiceSpec extends Specification {

    @Autowired InscripcionTorneoService service

    void "inscripcion de 2 equipos para torneo con 1 cupo"() {
        expect:"lanza excepcion CuposDisponiblesCompletos"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Torneo torneo = TorneoHelper.crearTorneo(1)
            torneo.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
            Equipo equipo2 = EquipoHelper.crear()
            equipo2.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo2.id as Integer, torneo.id as Integer)
        then:
            thrown CuposDisponiblesCompletos
    }

    void "inscripcion de 2 equipos amateur para torneo con 2 cupos"() {
        expect:"lanza excepcion NoHayCupoParaJerarquia"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Torneo torneo = TorneoHelper.crearTorneo(2)
            torneo.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
            Equipo equipo2 = EquipoHelper.crear()
            equipo2.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo2.id as Integer, torneo.id as Integer)
        then:
            thrown NoHayCupoParaJerarquia
    }

    void "inscripcion del mismo equipo 2 veces en el mismo torneo"() {
        expect:"lanza excepcion EquipoYaInscripto"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Torneo torneo = TorneoHelper.crearTorneo(2)
            torneo.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
            service.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
        then:
            thrown EquipoYaInscripto
    }

    void "inscripcion de equipo a un torneo nuevo que no pago un partido anterior"() {
        expect:"lanza excepcion EquipoDebePagarTodosSusPartidos"
        when:
            Equipo equipo = EquipoHelper.crear()
            equipo.save(failOnError:true)
            Torneo torneo = TorneoHelper.crearTorneo(2)
            torneo.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo.id as Integer, torneo.id as Integer)
            Equipo otroEquipo = EquipoHelper.crear("profesional")
            otroEquipo.save(failOnError:true)
            service.inscribirEquipoEnTorneo(otroEquipo.id as Integer, torneo.id as Integer)
            Cancha cancha = CanchaHelper.crear()
            cancha.save(failOnError:true)
            Partido partido = PartidoHelper.crear(equipo, otroEquipo, cancha)
            torneo.agregarPartido(partido)
            equipo.addToPartidos(partido)
            otroEquipo.addToPartidos(partido)
            partido.save(failOnError:true)
            Torneo otroTorneo = TorneoHelper.crearTorneo(2)
            otroTorneo.save(failOnError:true)
            service.inscribirEquipoEnTorneo(equipo.id as Integer, otroTorneo.id as Integer)
        then:
            thrown EquipoDebePagarTodosSusPartidos
    }
}
