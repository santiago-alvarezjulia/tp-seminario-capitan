package capitan

import capitan.interfaces.IPartidoService
import grails.gorm.transactions.Transactional
import repositories.IClimaRepository
import repositories.IPartidoRepository

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Transactional
class PartidoService implements IPartidoService {

    IPartidoRepository partidoRepository
    IClimaRepository climaRepository

    @Override
    Partido crear(
            Long fechaInicio,
            Integer idEquipoLocal,
            Integer idEquipoVisitante,
            Integer idTorneo,
            Integer idCancha
    ) {
        LocalDateTime inicioPartido = inicioPartidoConEpochMillis(fechaInicio)
        Equipo equipoLocal = Equipo.get(idEquipoLocal)
        Equipo equipoVisitante = Equipo.get(idEquipoVisitante)
        Torneo torneo = Torneo.get(idTorneo)
        Cancha cancha = Cancha.get(idCancha)
        Partido partido = new Partido(inicioPartido, equipoLocal, equipoVisitante, cancha)
        torneo.agregarPartido(partido)
        partido.save()
        partido
    }

    private LocalDateTime inicioPartidoConEpochMillis(Long fechaInicio) {
        Instant instant = Instant.ofEpochMilli(fechaInicio)
        LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
    }

    @Override
    List<Partido> actualizarPartidosAunNoJugadosSegunClima() {
        List<Partido> partidosEnLosProximosCincoDias = partidoRepository.partidosEnLosProximosCincoDias()
        partidosEnLosProximosCincoDias.forEach {
            Float porcentajeLluvia = climaRepository.porcentajeLluvia(it.inicioPartido, it.cancha.coordenadas)
            it.actualizarEstadoSegunClima(porcentajeLluvia)
        }
        partidosEnLosProximosCincoDias
    }
}
