package capitan

import capitan.interfaces.ITorneoService
import grails.gorm.transactions.Transactional

@Transactional
class TorneoService implements ITorneoService {

    @Override
    Torneo crear(Integer cupoEquipos) {
        Torneo torneo = new Torneo(cupoEquipos)
        torneo.save(failOnError:true)
        torneo
    }
}
