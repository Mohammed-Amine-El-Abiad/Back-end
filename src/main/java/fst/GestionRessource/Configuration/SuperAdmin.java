package fst.GestionRessource.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import fst.GestionRessource.User.model.Role;
import fst.GestionRessource.User.model.User;
import fst.GestionRessource.User.repository.UserRepository;

import java.util.Collections;

@Component
public class SuperAdmin implements ApplicationListener<ContextRefreshedEvent> {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    var exist = userRepository.findByUserNumber("00000").orElse(null);

    if (exist == null) {
      var user = User.builder()
                     .id("U-00000000000000000000000000000001")
                     .fullName("super admin")
                     .userNumber("00000")
                     .password(passwordEncoder.encode("0"))
                     .role(Collections.singletonList(Role.SUPER_ADMIN))
                     .build();
      userRepository.save(user);
    }
  }
}
