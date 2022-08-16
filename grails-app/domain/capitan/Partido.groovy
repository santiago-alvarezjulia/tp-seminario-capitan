package capitan

import exceptions.EquipoDebePagarTodosSusPartidos
import exceptions.FechaDeCreacionPosteriorAInicio
import exceptions.NoPuedenJugarMismaJerarquia

import java.time.LocalDateTime

class Partido {
    LocalDateTime inicioPartido
    Equipo equipoLocal
    Equipo equipoVisitante

    static hasMany = [pagoPartidos: PagoPartido]
    static belongsTo = [torneo: Torneo]

    Partido(LocalDateTime inicioPartido, Equipo equipoLocal, Equipo equipoVisitante) {
        if (equipoLocal.tieneLaMismaJerarquia(equipoVisitante))
            throw new NoPuedenJugarMismaJerarquia()
        if (equipoLocal.noHaPagadoTodosSusPartidos() || equipoVisitante.noHaPagadoTodosSusPartidos())
            throw new EquipoDebePagarTodosSusPartidos()
        if (fechaDeInicioEsPosteriorALaDeCreacion(inicioPartido))
            throw new FechaDeCreacionPosteriorAInicio()
        this.inicioPartido = inicioPartido
        this.equipoLocal = equipoLocal
        this.equipoVisitante = equipoVisitante
    }

    private static Boolean fechaDeInicioEsPosteriorALaDeCreacion(LocalDateTime inicioPartido) {
        LocalDateTime.now().isAfter(inicioPartido)
    }

    Boolean esEntreEstosEquipos(Equipo equipoLocal, Equipo equipoVisitante) {
        (this.equipoLocal.id == equipoLocal.id && this.equipoVisitante.id == equipoVisitante.id) ||
                (this.equipoLocal.id == equipoVisitante.id && this.equipoVisitante.id == equipoLocal.id)
    }
}
