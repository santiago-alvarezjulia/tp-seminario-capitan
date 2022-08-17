package domain


import exceptions.JerarquiaDesconocida

abstract class Jerarquia {
    private static String AMATEUR = "amateur"
    private static String PROFESIONAL = "profesional"

    abstract BigDecimal porcentajePago()

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
