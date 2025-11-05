package com.example.demo.service;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*@Component -> para o Autowired funcionar, a classe que será injetada precisa informar o Spring com esta anotação.
* */
@Service //Faz o mesmo que o @Component, mas deixa claro que a classe se trata de um Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public List<User> findAll(){
        return repository.findAll();
    }

    public User findById(Long id){
        /*return repository.findById(id); Se eu retornar no repository direto, o controller enviará um status code inde-
        * pendente do resultado.*/
        Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new ResourceNotFoundException(id));

    }

    public User insert(User user){
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);

    }

    public User update(User obj, Long id){
        try{
            //O findById usa o objeto do banco de dados. O reference já utilizada apenas um "monitoramento" dos dados.
            User user = repository.getReferenceById(id);
            updateObjetcData(user, obj);

            return repository.save(user);
        }catch (EntityNotFoundException e){
            throw  new ResourceNotFoundException(id);
        }
    }

    private void updateObjetcData(User user, User obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPhone(obj.getPhone());
    }
}
