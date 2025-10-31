package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
//Ao passar uma entidade e um Long, já temos uma implementação default para a nossa interface e não precisaremos
//implementar explicitamente
public interface UserRepository extends JpaRepository<User, Long> {

}
