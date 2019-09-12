package sl.testapp;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = WebCfg.class)
public class Some2Test {

	@Autowired
	MyController controller;

	@Test
	public void tetuj() {
		String result = controller.hello();
		Assertions.assertThat(result).isEqualTo("hellno");
	}
}
