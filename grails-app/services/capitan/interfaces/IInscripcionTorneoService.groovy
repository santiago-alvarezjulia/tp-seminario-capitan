package capitan.interfaces

import capitan.InscripcionTorneo

interface IInscripcionTorneoService {
    InscripcionTorneo inscribirEquipoEnTorneo(Integer idEquipo, Integer idTorneo)
}