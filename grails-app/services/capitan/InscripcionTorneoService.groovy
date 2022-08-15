package capitan

import capitan.interfaces.IInscripcionTorneoService
import grails.gorm.transactions.Transactional

@Transactional
class InscripcionTorneoService implements IInscripcionTorneoService {

    @Override
    InscripcionTorneo crear(Integer idEquipo, Integer idTorneo) {
        Torneo torneo = Torneo.get(idTorneo)
        Equipo equipo = Equipo.get(idEquipo)
        InscripcionTorneo inscripcionTorneo = equipo.inscribirEnTorneo(torneo)
        equipo.addToInscripcionTorneos(inscripcionTorneo)
        torneo.addToInscripcionTorneos(inscripcionTorneo)
        inscripcionTorneo
    }
}
