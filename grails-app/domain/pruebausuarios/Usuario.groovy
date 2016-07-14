package pruebausuarios

class Usuario extends SecUser{

  String nombre
  Date fecha

    static constraints = {
      nombre(nullable:true)
      fecha(nullable:true)
    }
}
