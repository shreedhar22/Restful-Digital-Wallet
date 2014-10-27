package wallet

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.authentication._
import com.mongodb.MongoClient
//remove if not needed
import scala.collection.JavaConversions._

@Configuration
class SpringMongoConfig {

  @Bean
  def mongoTemplate(): MongoTemplate = {
    val mongoTemplate = new MongoTemplate(new MongoClient("ds047950.mongolab.com:47950"), "digital_wallet", new UserCredentials("shreedhar_sjsu","root"));
    mongoTemplate
  }
}
