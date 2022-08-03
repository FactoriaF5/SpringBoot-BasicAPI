package com.factoria.coders.auth.configuration;
;



import com.factoria.coders.models.Role;
import com.factoria.coders.models.User;
import com.factoria.coders.repositories.IUserRepository;
import com.factoria.coders.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.beans.Encoder;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoleRepositoryInitializer {

    private RoleRepository roleRepository;
    private IUserRepository userRepository;
    private PasswordEncoder encoder;

    public RoleRepositoryInitializer(RoleRepository roleRepository, IUserRepository userRepository, PasswordEncoder encoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    @PostConstruct
    public void addAvailableRoles(){
        if (!roleRepository.findAll().isEmpty()) {
            return;
        }

        List<Role> roles = List.of(
                new Role(1, Role.RoleName.ROLE_ADMIN),

                new Role(2, Role.RoleName.ROLE_USER)
        );

        roleRepository.saveAll(roles);

        if (!userRepository.findAll().isEmpty()) {
            return;
        }

        var userAdmin = new User();
        userAdmin.setRoles(roleRepository.findAll().stream().collect(Collectors.toSet()));
        userAdmin.setEmail("admin@admin.com");
        userAdmin.setUsername("Admin");
        userAdmin.setPassword(encoder.encode("12345678"));

        var userUser = new User();
        userUser.setRoles(Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).get()));
        userUser.setEmail("user@user.com");
        userUser.setUsername("User");
        userUser.setPassword(encoder.encode("12345678"));

        var users = List.of(userAdmin,userUser);

        userRepository.saveAll(users);
    }
}
