package elxsi.assessment.config;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).and().httpBasic().and()
				.authorizeRequests().anyRequest().authenticated();
	}

	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return (request, response, authException) -> {
			response.addHeader("WWW-Authenticate", "Basic");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			PrintWriter writer = response.getWriter();
			writer.println("HTTP Status 401 - " + authException.getMessage());
		};
	}
}