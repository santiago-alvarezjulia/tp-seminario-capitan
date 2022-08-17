package domain

import capitan.Torneo
import exceptions.JerarquiaDesconocida

abstract class Jerarquia {
    private static String AMATEUR = "amateur"
    private static String PROFESIONAL = "profesional"

    abstract BigDecimal porcentajePago()
    abstract Integer puntosPorGolesQueSeSuman(Torneo torneo, Integer cantidadGoles)

    static Jerarquia crear(String jerarquia) {
        switch (jerarquia) {
            case AMATEUR:
                new Amateur()
                break
            case PROFESIONAL:
                new Profesional()
                break
            default:
                throw new JerarquiaDesconocida()
        }
    }
}
