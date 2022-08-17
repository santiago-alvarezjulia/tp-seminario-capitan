package domain

import domain.Jerarquia

class Profesional extends Jerarquia {

    @Override
    BigDecimal porcentajePago() {
        100
    }

}
