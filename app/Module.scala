import com.google.inject.AbstractModule
import db.mysql.MySQLDatabaseService
import play.api.Configuration
import play.api.Environment



class Module extends AbstractModule {

   def configure(environment: Environment, configuration: Configuration) = {
    val config = configuration.underlying.getConfig("")
    val mySqlDatabaseService = new MySQLDatabaseService(config)

    /*
    bind(classOf[Hello])
      .annotatedWith(Names.named("en"))
      .to(classOf[EnglishHello])
    */


  }

  override def configure(): Unit = ???
}
