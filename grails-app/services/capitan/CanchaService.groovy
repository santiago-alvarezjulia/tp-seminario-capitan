package capitan

import capitan.interfaces.ICanchaService
import domain.Coordenadas
import grails.gorm.transactions.Transactional

@Transactional
class CanchaService implements ICanchaService {
    @Override
    Cancha crear(Float latitud, Float longitud) {
        Coordenadas coordenadas = new Coordenadas(latitud: latitud, longitud: longitud)
        Cancha cancha = new Cancha(coordenadas: coordenadas)
        cancha.save()
        cancha
    }
}
