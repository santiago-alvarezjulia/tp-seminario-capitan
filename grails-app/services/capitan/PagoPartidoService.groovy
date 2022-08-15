package capitan

import grails.gorm.transactions.Transactional

@Transactional
class PagoPartidoService {

    PagoPartido crear(Integer idEquipo, Integer idPartido) {
        Equipo equipo = Equipo.get(idEquipo)
        Partido partido = Partido.get(idPartido)
        PagoPartido pagoPartido = equipo.pagarPartido(partido)
        equipo.addToPagoPartidos(pagoPartido)
        partido.addToPagoPartidos(pagoPartido)
        pagoPartido
    }
}
