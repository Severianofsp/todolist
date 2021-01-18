package br.com.teste.todolist.config;

import br.com.teste.todolist.enuns.TypeProfileEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String GESTOR = TypeProfileEnum.GESTOR.getAuthority();
    private static final String ADM = TypeProfileEnum.ADM.getAuthority();
    private static final String USUARIO = TypeProfileEnum.ADM.getAuthority();

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Autowired
    public void configure(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
            .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/", "/console/**","/users","/users/**").permitAll()
                .antMatchers(HttpMethod.POST,"/users").permitAll()
                .antMatchers(HttpMethod.POST,"/lista").hasAnyAuthority(USUARIO,ADM,GESTOR)
            .and()
                .exceptionHandling()
                .accessDeniedPage("/acesso-negado")
            .and()
                .formLogin()
                .defaultSuccessUrl("/",true)
                .failureUrl("/login-error")
                .permitAll()
        .   and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .permitAll()
        .and()
                .rememberMe();
    }
}
