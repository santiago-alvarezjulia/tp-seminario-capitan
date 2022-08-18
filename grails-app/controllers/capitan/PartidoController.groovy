package capitan

import capitan.interfaces.IPartidoService
import grails.converters.JSON

class PartidoController {
    private static String FECHA_INICIO_KEY = "fecha_inicio"
    private static String ID_TORNEO_KEY = "id_torneo"
    private static String ID_EQUIPO_LOCAL_KEY = "id_equipo_local"
    private static String ID_EQUIPO_VISITANTE_KEY = "id_equipo_visitante"
    private static String ID_CANCHA_KEY = "id_cancha"

    IPartidoService partidoService

    def crear() {
        Long fechaInicio = request.JSON.get(FECHA_INICIO_KEY)
        Integer idEquipoLocal = request.JSON.get(ID_EQUIPO_LOCAL_KEY)
        Integer idEquipoVisitante = request.JSON.get(ID_EQUIPO_VISITANTE_KEY)
        Integer idTorneo = request.JSON.get(ID_TORNEO_KEY)
        Integer idCancha = request.JSON.get(ID_CANCHA_KEY)
        Partido partido = partidoService.crear(
                fechaInicio,
                idEquipoLocal,
                idEquipoVisitante,
                idTorneo,
                idCancha
        )
        render partido as JSON
    }

    def clima() {
        List<Partido> partidos = partidoService.actualizarPartidosAunNoJugadosSegunClima()
        render partidos as JSON
    }

    def partidos() {
        render Partido.getAll() as JSON
    }
}
