package capitan

import domain.Jerarquia
import exceptions.EquipoDebePagarTodosSusPartidos
import exceptions.JugadorNoEsParteDelEquipo
import exceptions.PartidoYaPagado
import exceptions.SaldoInsuficiente

class Equipo {

    String nombre
    BigDecimal saldo
    String jerarquia

    private static Integer MONTO_FIJO_TRANSFERENCIA = 1000

    static hasMany = [
            partidos: Partido,
            pagoPartidos: PagoPartido,
            inscripcionTorneos: InscripcionTorneo,
            jugadores: Jugador
    ]

    Boolean haPagadoElPartido(Partido partido) {
        pagoPartidos.any { pagoPartido -> pagoPartido.partido.id == partido.id }
    }

    Boolean noHaPagadoTodosSusPartidos() {
        partidos.size() != pagoPartidos.size()
    }

    PagoPartido pagarPartido(Partido partido) {
        BigDecimal montoAPagar = PagoPartido.montoAPagar(partido, this)
        if (montoAPagar > saldo)
            throw new SaldoInsuficiente()
        if (haPagadoElPartido(partido))
            throw new PartidoYaPagado()
        saldo -= montoAPagar
        new PagoPartido(monto: montoAPagar)
    }

    InscripcionTorneo inscribirEnTorneo(Torneo torneo) {
        if (noHaPagadoTodosSusPartidos())
            throw new EquipoDebePagarTodosSusPartidos()
        torneo.inscribirEquipo(this)
    }

    BigDecimal porcentajePago() {
        Jerarquia.crear(jerarquia).porcentajePago()
    }

    void nuevoSaldo(BigDecimal nuevoSaldo) {
        setSaldo nuevoSaldo
    }

    Boolean tieneLaMismaJerarquia(Equipo equipo) {
        jerarquia == equipo.jerarquia
    }

    Boolean jugadorNoEsParte(Jugador jugador) {
        jugadores.every { it.id != jugador.id }
    }

    Integer puntosPorGolesQueSeSuman(Torneo torneo, Integer cantidadGoles) {
        Jerarquia.crear(jerarquia).puntosPorGolesQueSeSuman(torneo, cantidadGoles)
    }

    Boolean noPuedeTransferirJugador() {
        saldo < MONTO_FIJO_TRANSFERENCIA
    }

    void transferirJugadorAOtroEquipo(Jugador jugador) {
        if (jugadorNoEsParte(jugador))
            throw new JugadorNoEsParteDelEquipo()
        setSaldo(saldo -= MONTO_FIJO_TRANSFERENCIA)
        removeFromJugadores(jugador)
    }

    void transferirJugadorAEsteEquipo(Jugador jugador) {
        setSaldo(saldo -= MONTO_FIJO_TRANSFERENCIA)
        addToJugadores(jugador)
    }
}
