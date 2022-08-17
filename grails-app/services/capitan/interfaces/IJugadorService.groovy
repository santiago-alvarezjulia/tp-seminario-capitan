package capitan.interfaces

import capitan.Jugador

interface IJugadorService {
    Jugador crear(String nombre, Integer idEquipo)
    Jugador transferir(Integer idJugador, Integer idEquipoActual, Integer idEquipoNuevo)
}