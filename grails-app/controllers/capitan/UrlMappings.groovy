package capitan

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
        "/api/v1/equipo"(controller:"equipo") {
            action = [POST: 'crear']
        }
        "/api/v1/equipo/$id?"(controller:"equipo") {
            action = [PATCH: 'editar']
        }
        "/api/v1/partido"(controller:"partido") {
            action = [POST: 'crear']
        }
        "/api/v1/partido/pago"(controller:"pagoPartido") {
            action = [POST: 'crear']
        }
        "/api/v1/partido/clima"(controller:"partido") {
            action = [PATCH: 'clima']
        }
        "/api/v1/goles_jugador"(controller:"golesJugador") {
            action = [POST: 'crear']
        }
        "/api/v1/torneo"(controller:"torneo") {
            action = [POST: 'crear']
        }
        "/api/v1/torneo/inscripcion"(controller:"inscripcionTorneo") {
            action = [POST: 'crear']
        }
        "/api/v1/cancha"(controller:"cancha") {
            action = [POST: 'crear']
        }
        "/api/v1/jugador"(controller:"jugador") {
            action = [POST: 'crear']
        }
        "/api/v1/jugador/transferir"(controller:"jugador") {
            action = [POST: 'transferir']
        }
    }
}
