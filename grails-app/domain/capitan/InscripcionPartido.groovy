package capitan

class InscripcionPartido {
    String hola = "HOLA"
    static belongsTo = [equipo: Equipo, partido: Partido]

    Boolean perteneceAlEquipo(Equipo equipo) {
        this.equipo.id == equipo.id
    }
}
