package sl.testapp;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
class MyController {

	@Autowired
	private GraRepository graRepository;

	@GetMapping("/hello")
	@ResponseBody
	String hello() {
		return "hellno";
	}

	@GetMapping("/fill")
	@ResponseBody
	ModelAndView hello2() {
		final Gra g = new Gra();
		g.setName("Doom");
		graRepository.save(g);
		final Gra g1 = new Gra();
		g1.setName("Quake 2");
		graRepository.save(g1);
		final Gra g2 = new Gra();
		g2.setName("Need For Speed");
		graRepository.save(g2);
		final Gra g3 = new Gra();
		g3.setName("Race On");
		graRepository.save(g3);

		//		final Gra g = new Gra();
		//		g.setId(1L);
		//		g.setName("Quake");
		//		g.setCreated(Calendar.getInstance());
		//		graDAO.save(g);
		//
		//		final Gra g2 = new Gra();
		//		g2.setId(2L);
		//		g2.setName("The Elder Scrolls: Morrowind");
		//		g2.setCreated(Calendar.getInstance());
		//		graDAO.save(g2);
		//
		//		final Gra g3 = new Gra();
		//		g3.setId(3L);
		//		g3.setName("DOOM");
		//		g3.setCreated(Calendar.getInstance());
		//		graDAO.save(g3);


		return new ModelAndView("redirect:/list");
	}

	@GetMapping("{id}")
	ModelAndView view(@PathVariable("id") Long id) {
		final Optional<Gra> graResult = graRepository.findById(id);
		if (graResult.isPresent()) {
			return new ModelAndView("view", "gra", graResult.get());
		}
		return new ModelAndView("redirect:/list");

	}

	@GetMapping("/list")
	ModelAndView list() {
		return new ModelAndView("list", "games", graRepository.findAll());
	}

	@GetMapping(params = "form")
	String createForm(@ModelAttribute Gra gra) {
		return "create";
	}

	@PostMapping
	ModelAndView create(@Valid Gra gra, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return new ModelAndView("form");
		}
		gra = graRepository.save(gra);
		redirect.addFlashAttribute("globalMessage", "Succesfully added a new game");
		return new ModelAndView("redirect:/{gra.id}", "gra.id", gra.getId());
	}

	@GetMapping("/filter/{str}")
	@ResponseBody
	List<Gra> find(@PathVariable("str") String finder) {
		return graRepository.getGamesNamedLike(finder);
	}

}
