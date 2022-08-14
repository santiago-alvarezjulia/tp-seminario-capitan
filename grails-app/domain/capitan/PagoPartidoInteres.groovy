package capitan

import java.time.LocalDateTime

abstract class PagoPartidoInteres {

    private int CIEN_PORCIENTO = 100
    private static int LIMITE_HORAS_MAX_CERO_INTERES = 24
    private static int LIMITE_HORAS_MAX_CINCO_INTERES = 72

    static PagoPartidoInteres crearInteresPago(Partido partido) {
        LocalDateTime inicioPartido = partido.getInicioPartido()
        LocalDateTime now = LocalDateTime.now()
        if (now <= inicioPartido.plusHours(LIMITE_HORAS_MAX_CERO_INTERES)) {
            new PagoPartidoCeroInteres()
        } else if (now < inicioPartido.plusHours(LIMITE_HORAS_MAX_CINCO_INTERES)) {
            new PagoPartidoCincoInteres()
        } else {
            new PagoPartidoDiezInteres()
        }
    }

    BigDecimal interes(BigDecimal montoFijoSinInteres) {
        (montoFijoSinInteres * porcentajeInteres()) / CIEN_PORCIENTO
    }

    abstract int porcentajeInteres()
}

class PagoPartidoCeroInteres extends PagoPartidoInteres {

    @Override
    int porcentajeInteres() {
        0
    }
}
class PagoPartidoCincoInteres extends PagoPartidoInteres {

    @Override
    int porcentajeInteres() {
        5
    }
}

class PagoPartidoDiezInteres extends PagoPartidoInteres {

    @Override
    int porcentajeInteres() {
        10
    }
}
