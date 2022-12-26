package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;


@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    
    
    @Autowired 
    private UserDetailsService userDetailsService;
    
    @Bean
    public PasswordEncoder encoder() {
        return new StandardPasswordEncoder("53cr3t");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .authorizeRequests()
            .antMatchers("/design", "/orders")
                .access("hasRole('ROLE_USER')")
            .antMatchers("/", "/**").access("permitAll")
        .and()
            .formLogin()
                .loginPage("/login")
        .and()
            .logout()
                .logoutSuccessUrl("/")
        ;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder());
      
    }
    
//
//  IN MEMORY AUTHENTICATION
//
    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
        .inMemoryAuthentication()
        .withUser("buzz")
        .password("{noop}infinity") // Password Storage Format {id}encodedPassword: this id delegate to NoOpPasswordEncoder
        .authorities("ROLE_USER")
        .and()
        .withUser("woody")
        .password("{sha256}97cde38028ad898ebc02e690819fa220e88c62e0"
                + "699403e94fff291cfffaf8410849f27605abcbc0") // "password" encoded with sha256
        .authorities("ROLE_USER");
    }
    */

//
//  JDBC AUTHENTICATION
//
/*    @Autowired
    DataSource dataSource;
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
            throws Exception {
        auth
        .jdbcAuthentication()
        .dataSource(dataSource);
    }
*/
}
