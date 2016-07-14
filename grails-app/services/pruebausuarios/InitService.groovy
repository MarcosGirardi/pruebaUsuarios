package pruebausuarios

import grails.transaction.Transactional

@Transactional
class InitService {

  def springSecurityService

    def init() {

      log.println("32213213")

      def userRole = SecRole.findByAuthority('ROLE_USER') ?: new SecRole(authority: 'ROLE_USER').save(failOnError: true)
      def adminRole = SecRole.findByAuthority('ROLE_ADMIN') ?: new SecRole(authority: 'ROLE_ADMIN').save(failOnError: true)

      def adminUser = SecUser.findByUsername('admin') ?: new SecUser(
                username: 'admin',
                password: 'admin',     //springSecurityService.encodePassword('admin'),
                enabled: true).save(failOnError: true)

      if (!adminUser.authorities.contains(adminRole)) {
        SecUserSecRole.create adminUser, adminRole
      }

    }
}
