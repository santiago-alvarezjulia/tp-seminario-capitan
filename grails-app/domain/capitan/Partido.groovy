package capitan

import exceptions.EquipoDebePagarTodosSusPartidos
import exceptions.EquipoNoParticipoDelPartido
import exceptions.FechaDeCreacionPosteriorAInicio
import exceptions.JugadorNoEsParteDelEquipo
import exceptions.NoPuedenJugarMismaJerarquia

import java.time.LocalDateTime

class Partido {
    LocalDateTime inicioPartido
    Equipo equipoLocal
    Equipo equipoVisitante
    Cancha cancha
    String estado

    static hasMany = [pagoPartidos: PagoPartido, golesJugadoresPartido: GolesJugadorPartido]
    static belongsTo = [torneo: Torneo]

    private static String ESTADO_HABILITADO_PARA_JUGAR = "HABILITADO_PARA_JUGAR"
    private static String ESTADO_SUSPENDIDO_POR_CLIMA_ADVERSO = "SUSPENDIDO_POR_CLIMA_ADVERSO"
    private static Float UMBRAL_PERMITIDO_PORCENTAJE_LLUVIA = 50F

    Partido(LocalDateTime inicioPartido, Equipo equipoLocal, Equipo equipoVisitante, Cancha cancha) {
        if (equipoLocal.tieneLaMismaJerarquia(equipoVisitante))
            throw new NoPuedenJugarMismaJerarquia()
        if (equipoLocal.noHaPagadoTodosSusPartidos() || equipoVisitante.noHaPagadoTodosSusPartidos())
            throw new EquipoDebePagarTodosSusPartidos()
        if (fechaDeInicioEsPosteriorALaDeCreacion(inicioPartido))
            throw new FechaDeCreacionPosteriorAInicio()
        this.inicioPartido = inicioPartido
        this.equipoLocal = equipoLocal
        this.equipoVisitante = equipoVisitante
        this.cancha = cancha
        this.estado = ESTADO_HABILITADO_PARA_JUGAR
    }

    private static Boolean fechaDeInicioEsPosteriorALaDeCreacion(LocalDateTime inicioPartido) {
        LocalDateTime.now().isAfter(inicioPartido)
    }

    Boolean esEntreEstosEquipos(Equipo equipoLocal, Equipo equipoVisitante) {
        (this.equipoLocal.id == equipoLocal.id && this.equipoVisitante.id == equipoVisitante.id) ||
                (this.equipoLocal.id == equipoVisitante.id && this.equipoVisitante.id == equipoLocal.id)
    }

    void actualizarEstadoSegunClima(Float porcentajeLluvia) {
        if (hayQueSuspenderPartidoPorLluvia(porcentajeLluvia))
            setEstado ESTADO_SUSPENDIDO_POR_CLIMA_ADVERSO
        else
            setEstado ESTADO_HABILITADO_PARA_JUGAR
    }

    private Boolean hayQueSuspenderPartidoPorLluvia(Float porcentajeLluvia) {
        porcentajeLluvia > UMBRAL_PERMITIDO_PORCENTAJE_LLUVIA
    }

    GolesJugadorPartido crearGolesJugadorPartido(Equipo equipo, Jugador jugador, Integer cantidadGoles) {
        if (equipoNoParticipo(equipo))
            throw new EquipoNoParticipoDelPartido()
        if (equipo.jugadorNoEsParte(jugador))
            throw new JugadorNoEsParteDelEquipo()
        torneo.sumarPuntos(equipo, cantidadGoles)
        new GolesJugadorPartido(cantidad: cantidadGoles)
    }

    private Boolean equipoNoParticipo(Equipo equipo) {
        equipoLocal.id != equipo.id && equipoVisitante.id == equipo.id
    }
}
