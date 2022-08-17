package capitan

import capitan.interfaces.IJugadorService
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
}
