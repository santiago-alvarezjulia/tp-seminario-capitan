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
        "/api/v1/partido"(controller:"partido") {
            action = [POST: 'crear']
        }
        "/api/v1/pagoPartido"(controller:"pagoPartido") {
            action = [POST: 'crear']
        }
    }
}
