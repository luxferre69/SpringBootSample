package co.edu.unicomfacauca.persona.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/index").permitAll().antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll().antMatchers("/hello").hasAuthority("ADMIN").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").usernameParameter("email")
				.passwordParameter("password").permitAll().and().logout()
				// .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and().exceptionHandling().accessDeniedPage("/access-denied");

	}

	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.usersByUsernameQuery(
						"select persona.email, persona.password, persona.per_id from persona where persona.email=?")
				.authoritiesByUsernameQuery(
						"SELECT persona.email, rol.role FROM persona INNER JOIN role ON persona.per_id=role.per_id "
								+ "INNER JOIN rol ON rol.rol_id=role.rol_id WHERE persona.email=?")
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

}
