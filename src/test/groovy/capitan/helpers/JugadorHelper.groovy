package capitan.helpers

import capitan.Jugador

class JugadorHelper {
    static Jugador crear() {
        Jugador jugador = new Jugador(nombre: "Messi")
        jugador.golesJugadorPartidos = []
        jugador
    }
}
