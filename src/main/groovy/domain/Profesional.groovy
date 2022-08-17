package domain

import capitan.Torneo

class Profesional extends Jerarquia {
    private static int PORCENTAJE_PAGE = 100

    @Override
    BigDecimal porcentajePago() {
        PORCENTAJE_PAGE
    }

    @Override
    Integer puntosPorGolesQueSeSuman(Torneo torneo, Integer cantidadGoles) {
        torneo.puntosPorGolProfesional * cantidadGoles
    }
}
