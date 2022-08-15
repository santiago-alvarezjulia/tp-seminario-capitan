package capitan

class InscripcionTorneo {
    String jerarquiaEquipoAlInscribirse
    static belongsTo = [equipo: Equipo, torneo: Torneo]
}
