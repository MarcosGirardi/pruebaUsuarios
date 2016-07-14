package pruebausuarios



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DatosController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Datos.list(params), model:[datosInstanceCount: Datos.count()]
    }

    def show(Datos datosInstance) {
        respond datosInstance
    }

    def create() {
        respond new Datos(params)
    }

    @Transactional
    def save(Datos datosInstance) {
        if (datosInstance == null) {
            notFound()
            return
        }

        if (datosInstance.hasErrors()) {
            respond datosInstance.errors, view:'create'
            return
        }

        datosInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'datos.label', default: 'Datos'), datosInstance.id])
                redirect datosInstance
            }
            '*' { respond datosInstance, [status: CREATED] }
        }
    }

    def edit(Datos datosInstance) {
        respond datosInstance
    }

    @Transactional
    def update(Datos datosInstance) {
        if (datosInstance == null) {
            notFound()
            return
        }

        if (datosInstance.hasErrors()) {
            respond datosInstance.errors, view:'edit'
            return
        }

        datosInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Datos.label', default: 'Datos'), datosInstance.id])
                redirect datosInstance
            }
            '*'{ respond datosInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Datos datosInstance) {

        if (datosInstance == null) {
            notFound()
            return
        }

        datosInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Datos.label', default: 'Datos'), datosInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'datos.label', default: 'Datos'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
