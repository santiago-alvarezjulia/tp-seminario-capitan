package data

import io.micronaut.http.annotation.Get
import io.micronaut.http.client.annotation.Client

@Client("https://api.openweathermap.org/")
interface ClimaClient {
    @Get("/data/2.5/forecast?lat={latitud}&lon={longitud}&appid={apiKey}")
    ClimaResponse climaProximosCincoDias(Float latitud, Float longitud, String apiKey)
}