package repositories

import data.Pronostico
import data.ClimaClient
import data.ClimaResponse
import domain.Coordenadas
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime
import java.time.ZoneId

class ClimaRepository implements IClimaRepository {

    @Autowired
    ClimaClient weatherClient

    private static int PROBABILIDAD_A_PORCENTAJE = 100
    private static String WEATHER_API_KEY = "WEATHER_API_KEY"

    @Override
    Float porcentajeLluvia(LocalDateTime fechaInicio, Coordenadas coordenadas) {
        String apiKey = System.getenv(WEATHER_API_KEY)
        ClimaResponse weatherResult = weatherClient.climaProximosCincoDias(
                coordenadas.latitud,
                coordenadas.longitud,
                apiKey
        )
        Pronostico pronosticoMasCercanoAFechaInicio = weatherResult.list.findAll {
            it.dt <= fechaInicio.atZone(ZoneId.systemDefault()).toEpochSecond()
        }.max {
            it.dt
        }
        return pronosticoMasCercanoAFechaInicio.pop * PROBABILIDAD_A_PORCENTAJE
    }
}
