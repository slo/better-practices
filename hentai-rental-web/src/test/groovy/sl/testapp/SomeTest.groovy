package sl.testapp

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity

import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

import spock.lang.Specification

@WebAppConfiguration
@ContextConfiguration(classes = WebCfg)
class SomeTest extends Specification{

	@Autowired
	protected WebApplicationContext webApplicationContext

	MockMvc mockMvc

	@Before
	void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(springSecurity())
				.build()
	}


	def "exception thrown" () {
		when: "nothing is initialized"
		def obj = 3;
		then: "exception is thrown"
		obj == 3
	}

	@WithMockUser(roles = "USER")
	def "some req" () {
		when:
		ResultActions results = mockMvc.perform(MockMvcRequestBuilders.get("/hello"))
		then:
		results.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.content().string("hellno"))
	}
}
