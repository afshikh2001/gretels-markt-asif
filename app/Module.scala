import com.google.inject.AbstractModule
import db.mysql.{DatabaseService, MySQLDatabaseService}
import play.api.{Configuration, Environment}

class Module(environment: Environment, configuration: Configuration) extends AbstractModule {

  @Override
  protected def configure() {
    // bindings are here

  }

}
