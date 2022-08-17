package capitan

class InscripcionPartido {
    List<Jugador> jugadoresDisponiblesParaJugar
    static belongsTo = [equipo: Equipo, partido: Partido]

    Boolean perteneceAlEquipo(Equipo equipo) {
        this.equipo.id == equipo.id
    }
}
