package com.example.demo.repositories;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository repository;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Should get User successfuly")
    void findUserById(){
        UserDTO u = new UserDTO( "Astolfo", "999999999","t3st3" );
        User created = createUser(u);

        Optional<User> user= this.repository.findById(created.getId());

        assertTrue(user.isPresent(), "O usuário deve ser encontrado no repositório.");
        assertEquals("Astolfo", user.get().getName(), "O nome do usuário deve ser 'Astolfo'.");
    }

    private User createUser(UserDTO user){
        User newUser = new User(user);

        return this.repository.save(newUser);
    }
}