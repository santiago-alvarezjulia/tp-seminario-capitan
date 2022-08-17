package capitan

import capitan.interfaces.IJugadorService

class JugadorService implements IJugadorService {

    @Override
    Jugador crear(String nombre, Integer idEquipo) {
        Equipo equipo = Equipo.get(idEquipo)
        Jugador jugador = new Jugador(nombre: nombre)
        jugador.save()
        equipo.addToJugadores(jugador)
        jugador
    }
}
