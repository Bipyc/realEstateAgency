package by.bsuir.realEstateAgency.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@EnableWebSecurity
@Order(101)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthenticationService();
    }

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider
                = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/panel/users/**").hasRole("ADMIN")
                .antMatchers("/panel/immobilities/**").hasAnyRole("ADMIN", "REALTOR", "CLIENT")
                .antMatchers("/panel/typeApplications/**").hasRole("ADMIN")
                .antMatchers("/panel/applications/**").hasAnyRole("ADMIN", "REALTOR")
                .antMatchers("/panel/deals/**").hasAnyRole("ADMIN", "REALTOR", "CLIENT")
                .antMatchers("/panel/deals/new").hasAnyRole("ADMIN", "REALTOR")
                .antMatchers("/panel/inspections/**").hasAnyRole("ADMIN", "REALTOR", "CLIENT")
                .antMatchers("/panel/inspections/new").hasAnyRole("ADMIN", "REALTOR")
                .antMatchers("/inspection/**").hasAnyRole("CLIENT")
                .antMatchers("/profile/**").hasAnyRole("ADMIN", "REALTOR", "CLIENT")
                .anyRequest().permitAll()
                .antMatchers("/registration").permitAll()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/")
                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(false)
                .and().csrf().disable();
    }
}
