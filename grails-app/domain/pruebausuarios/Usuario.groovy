package pruebausuarios

class Usuario extends SecUser{

  String nombre
  Date fecha
  String role

    static constraints = {
      nombre(nullable:true)
      fecha()
      role(inList:['ROLE_USER','ROLE_ADMIN'])
    }
}
