package repositories

import domain.Coordenadas

import java.time.LocalDateTime

interface IClimaRepository {
    Float porcentajeLluvia(LocalDateTime fecha, Coordenadas coordenadas)
}