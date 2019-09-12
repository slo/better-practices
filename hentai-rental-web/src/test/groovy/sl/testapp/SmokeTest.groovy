package sl.testapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = WebCfg)
class SmokeTest extends Specification{

	@Autowired
	MyController controller

	def "some req" () {

		controller != null
	}
}



