package co.edu.unicomfacauca.persona.control;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.unicomfacauca.persona.model.Persona;
import co.edu.unicomfacauca.persona.model.Rol;
import co.edu.unicomfacauca.persona.service.PersonaService;

@Controller
public class PersonaController {

	// Inyección de servicio
	@Autowired
	private PersonaService personaService;

	private Iterable<Persona> listResult;

	@PostConstruct
	public void load() {

		// personaService.guardarPersona(new Persona("Luis", "BM", "lmbm6@mail.com", 27,
		// "Programmer", "1234"));
		// personaService.guardarPersona(new Persona("Francisco ", "Chang",
		// "fran@mail.com", 55, "Chief", "francisco1"));
		//
		// personaService.guardarRol(new Rol("ADMIN"));

		listResult = personaService.ListarTodo();
		System.out.println("Loading...");
		for (Persona persona : listResult) {
			System.out.println(persona);
		}

	}

	/**
	 * Método de Busqueda de personas por parámetros
	 * 
	 * @param name
	 * @param lastname
	 * @param edad
	 * @param ocupacion
	 * @param model
	 * @return una lista con resultados de busqueda
	 */
	@RequestMapping({ "/", "/index" })
	public String main(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "lastname", required = false) String lastname,
			@RequestParam(value = "age", required = false) Integer edad,
			@RequestParam(value = "ocup", required = false) String ocupacion, Model model) {

		if (!isNull(name) && !name.isEmpty()) {

			listResult = personaService.buscarPorNombre(name);

		} else if (!isNull(lastname) && !lastname.isEmpty()) {
			listResult = personaService.buscarPorApellido(lastname);

		} else if (!isNull(edad) && edad != 0) {
			listResult = personaService.buscarPorEdad(edad);

		} else if (!isNull(ocupacion) && !ocupacion.isEmpty()) {
			listResult = personaService.buscarPorOcupacion(ocupacion);

		} else {
			listResult = personaService.ListarTodo();
		}

		model.addAttribute("listResult", listResult);
		return "index";
	}

	/**
	 * Registro de objeto persona y de vista "hello" en el ModelAndView por medio
	 * del método GET
	 * 
	 * @return modelAndView
	 */

	@GetMapping(value = "/hello")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		Persona user = new Persona();
		modelAndView.addObject("persona", user);
		modelAndView.setViewName("hello");
		return modelAndView;
	}

	/**
	 * 
	 * 
	 * @param persona
	 * @param bindingResult
	 * @return
	 */
	@PostMapping(value = "/hello")
	public ModelAndView create(@Valid Persona persona, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		boolean aux = false;

		if (persona != null) {
			personaService.guardarPersona(persona);
			modelAndView.addObject("mensajeExito", "El usuario ha sido registrado exitosamente");
			modelAndView.addObject("persona", new Persona());
			aux = true;
			modelAndView.addObject(aux);
			modelAndView.setViewName("hello");
		}

		return modelAndView;

	}

	private boolean isNull(Object obj) {
		return obj == null;
		// && obj.toString().isEmpty();
	}

}
