package food.ma.foodstore.security;

import food.ma.foodstore.dao.entities.Customer;
import food.ma.foodstore.dao.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/addtocart").authenticated()
                                .requestMatchers("/index", "/shop").permitAll()
                                .anyRequest().permitAll()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")  // Custom login page
                                .defaultSuccessUrl("/index", true)  // Redirect to /index after successful login
                                .failureUrl("/login?error=true")  // Redirect to /login with error parameter on failure
                                .permitAll()
                )
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")  // Default logout URL
                                .logoutSuccessUrl("/login?logout=true")  // Redirect to /login after successful logout
                                .permitAll()
                );


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(loadCustomers());
    }

    private UserDetails[] loadCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> User.withUsername(customer.getUsername())
                        .password(passwordEncoder.encode(customer.getPassword()))  // Encode password
                        .roles("USER")
                        .build())
                .toArray(UserDetails[]::new);
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            System.out.println("User " + authentication.getName() + " has logged in successfully.");
            response.sendRedirect("/index");
        };
    }
}
