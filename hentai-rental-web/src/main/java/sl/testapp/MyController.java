package sl.testapp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

	@RequestMapping("/hello")
	public String hello() {
		return "Hell NO!!!";
	}

	@RequestMapping("/hello2")
	public Resulcik hello2() {
		final Resulcik r = new Resulcik();
		r.setMsg("dfasdfas");
		r.setCode(234234);
		return r;
	}
}
