package capitan.interfaces

import capitan.PagoPartido

interface IPagoPartidoService {
    PagoPartido crear(Integer idEquipo, Integer idPartido)
}