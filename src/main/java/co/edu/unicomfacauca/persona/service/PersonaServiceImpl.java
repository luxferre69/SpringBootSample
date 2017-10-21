package co.edu.unicomfacauca.persona.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.unicomfacauca.persona.model.Persona;
import co.edu.unicomfacauca.persona.model.Rol;
import co.edu.unicomfacauca.persona.repository.PersonaRepository;
import co.edu.unicomfacauca.persona.repository.RolRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

	// Dependency Injection

	@Autowired
	private PersonaRepository personaRepository;
	@Autowired
	private RolRepository rolRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Iterable<Persona> ListarTodo() {
		return personaRepository.findAll();
	}

	@Override
	public List<Persona> buscarPorNombre(String name) {
		return personaRepository.findByNombre(name);
	}

	@Override
	public List<Persona> buscarPorApellido(String lastname) {
		return personaRepository.findByApellido(lastname);
	}

	@Override
	public List<Persona> buscarPorOcupacion(String ocupacion) {
		return personaRepository.findByOcupacion(ocupacion);
	}

	@Override
	public List<Persona> buscarPorEdad(int edad) {
		return personaRepository.findByEdad(edad);
	}

	@Override
	public void guardarPersona(Persona persona) {

		// encrypt password
		persona.setPassword(bCryptPasswordEncoder.encode(persona.getPassword()));
		Rol rol = rolRepository.findByRole("ADMIN");

		persona.setRols(new ArrayList<Rol>(Arrays.asList(rol)));
		personaRepository.save(persona);	
	}
	
	@Override
	public void guardarRol(Rol rol) {
		rolRepository.save(rol);
	}

	@Override
	public List<Persona> buscarPorNombreLike(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> buscarPorApellidoLike(String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Persona> buscarPorApellido(String lastname, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> buscarPorApellido(String lastname, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> BuscarPrimeras10PorApellido(String apellido, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> buscarPorNombreAndApellido(String name, String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> buscarPorApellidoOrderByNombreAsc(String apellido) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Persona> buscarPorApellidoOrderByNombreDesc(String apellido) {
		// TODO Auto-generated method stub
		return null;
	}

}
