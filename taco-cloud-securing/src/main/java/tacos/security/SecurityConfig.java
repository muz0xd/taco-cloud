package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
        .inMemoryAuthentication()
        .withUser("buzz")
        .password("{noop}infinity")
        .authorities("ROLE_USER")
        .and()
        .withUser("woody")
        .password("{sha256}97cde38028ad898ebc02e690819fa220e88c62e0"
                + "699403e94fff291cfffaf8410849f27605abcbc0") // "password" encoded with sha256
        .authorities("ROLE_USER");
    }
}
