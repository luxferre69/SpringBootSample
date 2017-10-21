package co.edu.unicomfacauca.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicomfacauca.persona.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Integer> {
	Rol findByRole(String role);

}
