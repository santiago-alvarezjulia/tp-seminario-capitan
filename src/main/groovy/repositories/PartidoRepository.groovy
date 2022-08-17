package repositories

import capitan.Partido

import java.time.LocalDateTime

class PartidoRepository implements IPartidoRepository {
    private static Long UMBRAL_DIAS_SUPERIOR = 5

    @Override
    List<Partido> partidosEnLosProximosCincoDias() {
        LocalDateTime ahora = LocalDateTime.now()
        LocalDateTime dentroDeCincoDias = ahora.plusDays(UMBRAL_DIAS_SUPERIOR)
        Partido.getAll().findAll {
            it.inicioPartido > ahora && it.inicioPartido < dentroDeCincoDias
        }
    }
}
