package capitan

import capitan.helpers.CanchaHelper
import capitan.helpers.EquipoHelper
import capitan.helpers.PartidoHelper
import capitan.helpers.TorneoHelper
import exceptions.PartidoYaPagado
import grails.gorm.transactions.Rollback
import grails.testing.mixin.integration.Integration
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

import java.time.LocalDateTime

@Integration
@Rollback
class PagoPartidoSpec extends Specification {

    @Autowired InscripcionTorneoService inscripcionTorneoService
    @Autowired PagoPartidoService pagoPartidoService

    void "pago 2 veces el mismo partido con el mismo equipo"() {
        expect:"lanza excepcion PartidoYaPagado"
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
            Partido partido = PartidoHelper.crear(LocalDateTime.now().plusDays(1), equipo, otroEquipo, cancha)
            torneo.agregarPartido(partido)
            equipo.addToPartidos(partido)
            otroEquipo.addToPartidos(partido)
            partido.save(failOnError:true)
            pagoPartidoService.crearPagoDePartidoPorParteDeEquipo(equipo.id as Integer, partido.id as Integer)
            pagoPartidoService.crearPagoDePartidoPorParteDeEquipo(equipo.id as Integer, partido.id as Integer)
        then:
            thrown PartidoYaPagado
    }

    void "monto cuando t <= 24hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto del pago es 10_000 y el saldo del equipo pasa a ser 45_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        Equipo equipo = new Equipo(saldo: 50_000, jerarquia: "amateur")
        equipo.pagarPartido(partido).monto == 5_000
        equipo.saldo == 45_000
    }

    void "monto cuando 24hs < t < 72hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto del pago es 5_250 y el saldo del equipo pasa a ser 44_750"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        Equipo equipo = new Equipo(saldo: 50_000, jerarquia: "amateur")
        equipo.pagarPartido(partido).monto == 5_250
        equipo.saldo == 44_750
    }

    void "monto cuando t >= 72hs desde el inicio del partido para EquipoAmateur"() {
        expect:"monto del pago es 5_500 y el saldo del equipo pasa a ser 44_500"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        Equipo equipo = new Equipo(saldo: 50_000, jerarquia: "amateur")
        equipo.pagarPartido(partido).monto == 5_500
        equipo.saldo == 44_500
    }

    void "monto cuando t <= 24hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto del pago es 10_000 y el saldo del equipo pasa a ser 40_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(23))
        Equipo equipo = new Equipo(saldo: 50_000, jerarquia: "profesional")
        equipo.pagarPartido(partido).monto == 10_000
        equipo.saldo == 40_000
    }

    void "monto cuando 24hs < t < 72hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto del pago es 10_500 y el saldo del equipo pasa a ser 39_500"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(50))
        Equipo equipo = new Equipo(saldo: 50_000, jerarquia: "profesional")
        equipo.pagarPartido(partido).monto == 10_500
        equipo.saldo == 39_500
    }

    void "monto cuando t >= 72hs desde el inicio del partido para EquipoProfesional"() {
        expect:"monto del pago es 11_000 y el saldo del equipo pasa a ser 39_000"
        Partido partido = new Partido(inicioPartido: LocalDateTime.now().minusHours(73))
        Equipo equipo = new Equipo(saldo: 50_000, jerarquia: "profesional")
        equipo.pagarPartido(partido).monto == 11_000
        equipo.saldo == 39_000
    }
}