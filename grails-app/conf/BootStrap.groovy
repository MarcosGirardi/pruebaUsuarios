class BootStrap {

  def initService

    def init = { servletContext ->
      log.println("asdasd")
      initService.init()
    }
    def destroy = {
    }
}
