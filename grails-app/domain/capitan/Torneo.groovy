package capitan

class Torneo {
    Integer cupoEquipos
    Integer puntosPorGolAmateur
    Integer puntosPorGolProfesional

    static hasMany = [partidos: Partido, equipos: Equipo]
}
