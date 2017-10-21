package co.edu.unicomfacauca.persona.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import co.edu.unicomfacauca.persona.model.Persona;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {

	List<Persona> findByNombre(String name);

	List<Persona> findByApellido(String lastname);

	List<Persona> findByNombreLike(String name);

	List<Persona> findByApellidoLike(String lastname);

	List<Persona> findByNombreAndApellido(String name, String lastname);

	List<Persona> findByApellidoOrderByNombreAsc(String apellido);

	List<Persona> findByApellidoOrderByNombreDesc(String apellido);

	List<Persona> findByOcupacion(String ocupacion);

	List<Persona> findByEdad(int edad);

	Page<Persona> findByApellido(String lastname, Pageable pageable);

	List<Persona> findByApellido(String lastname, Sort sort);

	List<Persona> findFirst10ByApellido(String apellido, Sort sort);

}
