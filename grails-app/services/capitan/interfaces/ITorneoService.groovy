package capitan.interfaces

import capitan.Torneo

interface ITorneoService {
    Torneo crear(Integer cupoEquipos, Integer puntosPorGolAmateur, Integer puntosPorGolProfesional)
}