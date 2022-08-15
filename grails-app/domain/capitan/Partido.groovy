package capitan

import java.time.LocalDateTime

class Partido {
    LocalDateTime inicioPartido

    static hasMany = [pagoPartidos: PagoPartido]
    static belongsTo = [torneo: Torneo]
}
