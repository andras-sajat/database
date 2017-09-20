package andras.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableJpaRepositories(basePackages = "andras.database.repository")
@SpringBootApplication
@RestController
public class DatabaseApplication extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
			.httpBasic()
		.and()
			.authorizeRequests()
				.antMatchers("/index.html", "/").permitAll()
			.anyRequest().authenticated().and().logout().logoutSuccessUrl("/");
		http.csrf().disable();
	}

	@RequestMapping("/greeting")
	public Greeting greeting() {
		return new Greeting("Hello");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}
}
