package capitan.interfaces

import capitan.Partido

interface IPartidoService {
    Partido crear(Long fechaInicio)
}