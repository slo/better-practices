package sl.testapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

@RestController
public class MyController {

	@Autowired
	private GraDAO graDAO;

	@RequestMapping("/hello")
	public String hello() {
		return "Hell NO!!!";
	}

	@RequestMapping("/hello2")
	public Resulcik hello2() {
		final Gra g = new Gra();
		g.setId(1L);
		g.setName("Quake");
		graDAO.save(g);

		final Gra g2 = new Gra();
		g2.setId(2L);
		g2.setName("The Elder Scrolls: Morrowind");
		graDAO.save(g2);

		final Gra g3 = new Gra();
		g3.setId(3L);
		g3.setName("DOOM");
		graDAO.save(g3);


		final Resulcik r = new Resulcik();
		r.setMsg("dfasdfa2s");
		r.setCode(234234);
		return r;
	}

	@RequestMapping("/hello3")
	public List<Gra> hello3() {
		return Lists.newArrayList(graDAO.findAll());
	}

}
