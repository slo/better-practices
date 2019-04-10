package sl.testapp;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
class MyController {

	@Autowired
	private GraDAO graDAO;

	@GetMapping("/hello")
	String hello() {
		return "hellno";
	}

	@RequestMapping("/hello2")
	@ResponseBody
	Resulcik hello2() {
		final Gra g = new Gra();
		g.setId(1L);
		g.setName("Quake");
		g.setCreated(Calendar.getInstance());
		graDAO.save(g);

		final Gra g2 = new Gra();
		g2.setId(2L);
		g2.setName("The Elder Scrolls: Morrowind");
		g2.setCreated(Calendar.getInstance());
		graDAO.save(g2);

		final Gra g3 = new Gra();
		g3.setId(3L);
		g3.setName("DOOM");
		g3.setCreated(Calendar.getInstance());
		graDAO.save(g3);


		final Resulcik r = new Resulcik();
		r.setMsg("dfasdfa2s");
		r.setCode(234234);
		return r;
	}

	@RequestMapping("/list")
	ModelAndView hello3() {
		return new ModelAndView("hellno", "games", graDAO.findAll());
	}

}
