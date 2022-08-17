package capitan.interfaces

import capitan.Cancha

interface ICanchaService {
    Cancha crear(Float latitud, Float longitud)
}