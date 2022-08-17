package data

import groovy.transform.CompileStatic

@CompileStatic
class ClimaResponse {
    List<Pronostico> list
}

@CompileStatic
class Pronostico {
    Long dt
    Float pop
}