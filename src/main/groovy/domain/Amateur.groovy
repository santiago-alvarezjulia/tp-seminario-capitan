package domain

import capitan.Torneo

class Amateur extends Jerarquia {
    private static int PORCENTAJE_PAGE = 50

    @Override
    BigDecimal porcentajePago() {
        PORCENTAJE_PAGE
    }

    @Override
    Integer puntosPorGolesQueSeSuman(Torneo torneo, Integer cantidadGoles) {
        torneo.puntosPorGolAmateur * cantidadGoles
    }
}
