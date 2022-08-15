package capitan

import capitan.interfaces.IPartidoService
import grails.gorm.transactions.Transactional

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

@Transactional
class PartidoService implements IPartidoService {

    Partido crear(Long fechaInicio) {
        Instant instant = Instant.ofEpochMilli(fechaInicio)
        LocalDateTime inicioPartido = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        Partido partido = new Partido(inicioPartido: inicioPartido)
        partido.save()
        partido
    }
}
