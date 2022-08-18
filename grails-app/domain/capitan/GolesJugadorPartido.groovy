package capitan

import exceptions.CantidadDeGolesNoEsMayorQueCero

class GolesJugadorPartido {
    Integer cantidad
    static belongsTo = [jugador: Jugador, partido: Partido]

    GolesJugadorPartido(Integer cantidad) {
        if (cantidad <= 0)
            throw new CantidadDeGolesNoEsMayorQueCero()
        this.cantidad = cantidad
    }
}
