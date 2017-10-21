package co.edu.unicomfacauca.persona.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import co.edu.unicomfacauca.persona.model.Persona;
import co.edu.unicomfacauca.persona.model.Rol;

public interface PersonaService {
	
	Iterable<Persona> ListarTodo();
	
	List<Persona> buscarPorNombre(String name);

	List<Persona> buscarPorApellido(String lastname);

	List<Persona> buscarPorNombreLike(String name);

	List<Persona> buscarPorApellidoLike(String lastname);

	List<Persona> buscarPorNombreAndApellido(String name, String lastname);

	List<Persona> buscarPorApellidoOrderByNombreAsc(String apellido);

	List<Persona> buscarPorApellidoOrderByNombreDesc(String apellido);

	List<Persona> buscarPorOcupacion(String ocupacion);

	List<Persona> buscarPorEdad(int edad);

	Page<Persona> buscarPorApellido(String lastname, Pageable pageable);

	List<Persona> buscarPorApellido(String lastname, Sort sort);

	List<Persona> BuscarPrimeras10PorApellido(String apellido, Sort sort);

	public void guardarPersona(Persona persona);

	public void guardarRol(Rol rol);

}
