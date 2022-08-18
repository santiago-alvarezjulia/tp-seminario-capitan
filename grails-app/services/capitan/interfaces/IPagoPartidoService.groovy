package capitan.interfaces

import capitan.PagoPartido

interface IPagoPartidoService {
    PagoPartido crearPagoDePartidoPorParteDeEquipo(Integer idEquipo, Integer idPartido)
}