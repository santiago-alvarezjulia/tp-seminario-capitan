package repositories

import capitan.Partido

interface IPartidoRepository {
    List<Partido> partidosEnLosProximosCincoDias()
}