package capitan

import capitan.interfaces.ITorneoService
import grails.gorm.transactions.Transactional

@Transactional
class TorneoService implements ITorneoService {

    @Override
    Torneo crear(Integer cupoEquipos, Integer puntosPorGolAmateur, Integer puntosPorGolProfesional) {
        Torneo torneo = new Torneo(
                cupoEquipos: cupoEquipos,
                puntosPorGolAmateur: puntosPorGolAmateur,
                puntosPorGolProfesional: puntosPorGolProfesional
        )
        torneo.save(failOnError:true)
        torneo
    }
}
