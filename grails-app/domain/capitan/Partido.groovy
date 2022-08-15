package capitan

import java.time.LocalDateTime

class Partido {
    LocalDateTime inicioPartido
    Equipo equipoLocal
    Equipo equipoVisitante

    static hasMany = [pagoPartidos: PagoPartido]
    static belongsTo = [torneo: Torneo]
}
