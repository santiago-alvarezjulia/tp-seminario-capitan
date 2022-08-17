package spring

import capitan.CanchaService
import capitan.EquipoService
import capitan.JugadorService
import capitan.PagoPartidoService
import capitan.PartidoService
import capitan.TorneoService
import repositories.ClimaRepository
import repositories.PartidoRepository

// Place your Spring DSL code here
beans = {
    equipoService(EquipoService)
    partidoService(PartidoService) {
        partidoRepository = ref("partidoRepository")
        climaRepository = ref("climaRepository")
    }
    pagoPartidoService(PagoPartidoService)
    torneoService(TorneoService)
    canchaService(CanchaService)
    partidoRepository(PartidoRepository)
    climaRepository(ClimaRepository)
    jugadorService(JugadorService)
}
