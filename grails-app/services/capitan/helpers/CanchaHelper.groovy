package capitan.helpers

import capitan.Cancha
import domain.Coordenadas

class CanchaHelper {
    static Cancha crear() {
        new Cancha(coordenadas: new Coordenadas(latitud: -50, longitud: -50))
    }
}
