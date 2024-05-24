package com.example.gticslab620212607.config;

import com.example.gticslab620212607.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig {

    final DataSource dataSource;
    final UsuarioRepository usuarioRepository;
    public WebSecurityConfig(DataSource dataSource, UsuarioRepository usuarioRepository) {
        this.dataSource = dataSource;
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String sql1 = "SELECT correo,password,status FROM usuario WHERE correo = ?";
        String sql2 = "SELECT u.correo,r.nombre FROM usuario u "
                + "INNER JOIN rol r ON (u.rol_id = r.id) "
                + "WHERE u.correo = ?";

        users.setUsersByUsernameQuery(sql1);
        users.setAuthoritiesByUsernameQuery(sql2);
        return users;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/openLoginWindow")
                        .loginProcessingUrl("/submitLoginForm")


                        .successHandler((request, response, authentication) -> {

                            DefaultSavedRequest defaultSavedRequest =
                                    (DefaultSavedRequest) request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");

                            HttpSession session = request.getSession();
                            session.setAttribute("usuario", usuarioRepository.findByCorreo(authentication.getName()));


                            //si vengo por url -> defaultSR existe
                            if (defaultSavedRequest != null) {

                                String targetURl = defaultSavedRequest.getRequestURL();

                                new DefaultRedirectStrategy().sendRedirect(request, response, targetURl);

                            } else { //estoy viniendo del botón de login
                                String rol = "";
                                for (GrantedAuthority role : authentication.getAuthorities()) {
                                    rol = role.getAuthority();
                                    break;
                                }

                                if (rol.equals("CLIENTE")) {
                                    response.sendRedirect("/mesas/list");
                                } else if (rol.equals("GERENTE")) {
                                    response.sendRedirect("/mesas/list");
                                } else if (rol.equals("ADMIN")){
                                    response.sendRedirect("/mesas/list");
                                }
                                else{
                                    response.sendRedirect("/mesas/list");
                                }

                            }
                        }));


        http.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/mesas/list").hasAnyAuthority("CLIENTE", "GERENTE","ADMIN")
                .requestMatchers("/personaje/new").hasAnyAuthority("CLIENTE", "GERENTE")
                .requestMatchers("/personaje/edit").hasAnyAuthority("CLIENTE", "GERENTE")
                .requestMatchers("/personaje/delete").hasAnyAuthority("CLIENTE")
                .anyRequest().permitAll()
        )
        ;

        http.logout(out -> out
                .logoutSuccessUrl("/mesas/list")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true));


        return http.build();
    }
}
