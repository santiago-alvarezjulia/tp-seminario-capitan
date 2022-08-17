package capitan.interfaces

import capitan.Jugador

interface IJugadorService {
    Jugador crear(String nombre, Integer idEquipo)
}