package spring

import capitan.EquipoService
import capitan.PagoPartidoService
import capitan.PartidoService

// Place your Spring DSL code here
beans = {
    equipoService(EquipoService)
    partidoService(PartidoService)
    pagoPartidoService(PagoPartidoService)
}
