package br.com.teste.todolist.config;

import br.com.teste.todolist.model.Users;
import br.com.teste.todolist.repository.UserRepository;
import javassist.NotFoundException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @SneakyThrows
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = findByEmailOrLogin(username)
                    .orElseThrow(()-> new NotFoundException("Username " + username + " não encontrado"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getAuthoritiesString())
        );
    }

    @Transactional(readOnly = true)
    private Optional<Users> findByEmailOrLogin(String username) {
        String validaEmail = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+"
                + "(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

        Pattern pattern = Pattern.compile(validaEmail);
        Matcher userEmail = pattern.matcher(username);

        return userRepository.findByEmail(username);

    }
}
