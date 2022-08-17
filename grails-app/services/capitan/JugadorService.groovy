package capitan

import capitan.interfaces.IJugadorService
import exceptions.SaldoInsuficiente
import grails.gorm.transactions.Transactional

@Transactional
class JugadorService implements IJugadorService {

    @Override
    Jugador crear(String nombre, Integer idEquipo) {
        Equipo equipo = Equipo.get(idEquipo)
        Jugador jugador = new Jugador(nombre: nombre)
        equipo.addToJugadores(jugador)
        jugador
    }

    @Override
    Jugador transferir(Integer idJugador, Integer idEquipoActual, Integer idEquipoNuevo) {
        Jugador jugador = Jugador.get(idJugador)
        Equipo equipoActual = Equipo.get(idEquipoActual)
        Equipo equipoNuevo = Equipo.get(idEquipoNuevo)
        if (equipoActual.noPuedeTransferirJugador() || equipoNuevo.noPuedeTransferirJugador())
            throw new SaldoInsuficiente()
        equipoActual.transferirJugadorAOtroEquipo(jugador)
        equipoNuevo.transferirJugadorAEsteEquipo(jugador)
        jugador
    }
}
