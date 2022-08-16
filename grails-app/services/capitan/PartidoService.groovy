package capitan

import capitan.interfaces.IPartidoService
import grails.gorm.transactions.Transactional

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Transactional
class PartidoService implements IPartidoService {

    @Override
    Partido crear(Long fechaInicio, Integer idEquipoLocal, Integer idEquipoVisitante, Integer idTorneo) {
        LocalDateTime inicioPartido = inicioPartidoConEpochMillis(fechaInicio)
        Equipo equipoLocal = Equipo.get(idEquipoLocal)
        Equipo equipoVisitante = Equipo.get(idEquipoVisitante)
        Torneo torneo = Torneo.get(idTorneo)
        Partido partido = new Partido(inicioPartido, equipoLocal, equipoVisitante)
        torneo.agregarPartido(partido)
        partido.save()
        partido
    }

    private LocalDateTime inicioPartidoConEpochMillis(Long fechaInicio) {
        Instant instant = Instant.ofEpochMilli(fechaInicio)
        LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }
}
