import com.google.inject.AbstractModule
import com.google.inject.AbstractModule
import com.google.inject.name.Names
import com.typesafe.config.Config
import db.mysql.MySQLDatabaseService
import play.api.Configuration
import play.api.Environment



class Module extends AbstractModule {

  override def configure(environment: Environment, configuration: Configuration) = {
    val config = configuration.underlying.getConfig("")
    val mySqlDatabaseService = new MySQLDatabaseService(config)

    /*
    bind(classOf[Hello])
      .annotatedWith(Names.named("en"))
      .to(classOf[EnglishHello])
    */


  }
}
