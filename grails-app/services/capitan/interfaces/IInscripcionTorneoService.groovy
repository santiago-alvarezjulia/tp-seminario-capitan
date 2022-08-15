package capitan.interfaces

import capitan.InscripcionTorneo

interface IInscripcionTorneoService {
    InscripcionTorneo crear(Integer idEquipo, Integer idTorneo)
}