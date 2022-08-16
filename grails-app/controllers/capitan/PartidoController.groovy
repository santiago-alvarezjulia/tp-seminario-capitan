package capitan

import capitan.interfaces.IPartidoService
import grails.converters.JSON

class PartidoController {
    private static String FECHA_INICIO_KEY = "fecha_inicio"
    private static String ID_TORNEO_KEY = "id_torneo"
    private static String ID_EQUIPO_LOCAL_KEY = "id_equipo_local"
    private static String ID_EQUIPO_VISITANTE_KEY = "id_equipo_visitante"

    IPartidoService partidoService

    def crear() {
        Long fechaInicio = request.JSON.get(FECHA_INICIO_KEY)
        Integer idEquipoLocal = request.JSON.get(ID_EQUIPO_LOCAL_KEY)
        Integer idEquipoVisitante = request.JSON.get(ID_EQUIPO_VISITANTE_KEY)
        Integer idTorneo = request.JSON.get(ID_TORNEO_KEY)
        Partido partido = partidoService.crear(fechaInicio, idEquipoLocal, idEquipoVisitante, idTorneo)
        render partido as JSON
    }
}
