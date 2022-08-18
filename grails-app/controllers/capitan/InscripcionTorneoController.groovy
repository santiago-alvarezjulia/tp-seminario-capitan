package capitan

import capitan.interfaces.IInscripcionTorneoService
import grails.converters.JSON

class InscripcionTorneoController {
    private static String ID_EQUIPO_KEY = "id_equipo"
    private static String ID_TORNEO_KEY = "id_torneo"

    IInscripcionTorneoService inscripcionTorneoService

    def crear() {
        Integer idEquipo = request.JSON.get(ID_EQUIPO_KEY)
        Integer idTorneo = request.JSON.get(ID_TORNEO_KEY)
        InscripcionTorneo inscripcionTorneo = inscripcionTorneoService.inscribirEquipoEnTorneo(idEquipo, idTorneo)
        render inscripcionTorneo as JSON
    }

    def inscripciones() {
        render InscripcionTorneo.getAll() as JSON
    }
}
