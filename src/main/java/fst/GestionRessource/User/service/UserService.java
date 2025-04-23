package fst.GestionRessource.User.service;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fst.GestionRessource.Authentication.RegisterRequest;
import fst.GestionRessource.User.model.Role;
import fst.GestionRessource.User.model.User;
import fst.GestionRessource.User.repository.UserRepository;
import fst.GestionRessource.Utils.IdGenerator;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    // @Autowired
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public List<User> getUsers(){
        return repository.findAll();
    }
    public void createUser(RegisterRequest request) {
        if (request.getRole().contains(Role.SUPER_ADMIN)) return;

        var ID = IdGenerator.generateId("U-");

        while (repository.existsById(ID)) {
            ID = IdGenerator.generateId("U-");
        }

        var user = User.builder()
                .id(ID)
                .userNumber(request.getUserNumber())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        System.out.println(user);
        repository.save(user);
    }

    public Object getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated()) {
            return auth.getPrincipal();
        }
        return null;
    }

    public User getUser(String id){
        User user = repository.findUserById(id);
        if(!repository.existsById(id))
                return null;

        return user;
    }

    public void  deleteUser(String id){
        repository.deleteById(id);
    }

    public User updateUser(String id , RegisterRequest user){
        User existUser = repository.findUserById(id);
        if(existUser != null){
            if (user.getFullName() != null)
                existUser.setFullName(user.getFullName());
            if (user.getUserNumber() != null)
                existUser.setUserNumber(user.getUserNumber());
            if (user.getRole() != null)
                existUser.setRole(user.getRole());
            if(user.getPassword() != null && !user.getPassword().isEmpty())
                existUser.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(existUser);
            return existUser;
        }
        return null;
    }
}
