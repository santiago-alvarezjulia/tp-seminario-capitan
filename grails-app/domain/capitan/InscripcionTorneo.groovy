package capitan

class InscripcionTorneo {
    String jerarquiaEquipoAlInscribirse
    Integer puntaje = 0
    static belongsTo = [equipo: Equipo, torneo: Torneo]

    void sumarPuntos(Integer puntosPorGolesQueSeSuman) {
        setPuntaje(puntaje + puntosPorGolesQueSeSuman)
    }
}
