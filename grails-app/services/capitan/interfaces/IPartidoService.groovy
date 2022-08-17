package capitan.interfaces

import capitan.GolesJugadorPartido
import capitan.Partido

interface IPartidoService {
    Partido crear(
            Long fechaInicio,
            Integer idEquipoLocal,
            Integer idEquipoVisitante,
            Integer idTorneo,
            Integer idCancha
    )
    List<Partido> actualizarPartidosAunNoJugadosSegunClima()
    GolesJugadorPartido crearGolesJugadorPartido(
            Integer idPartido,
            Integer idEquipo,
            Integer idJugador,
            Integer cantidadGoles
    )
}