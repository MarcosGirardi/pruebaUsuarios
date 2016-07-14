package pruebausuarios

class Datos {

  String nombre
  Date fecha

    static constraints = {
      nombre(nullable:true)
      fecha(nullable:true)
    }
}
