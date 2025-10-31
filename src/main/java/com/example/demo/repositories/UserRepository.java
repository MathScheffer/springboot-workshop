package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository // Não é obrigatório por que já está herdando a notação do JpaRepository
public interface UserRepository extends JpaRepository<User, Long> {//Ao passar uma entidade e um Long, já temos uma implementação default para a nossa interface e não precisaremos implementar explicitamente


}
