package edu.eci.cvds.TaskManager.repository;

import edu.eci.cvds.TaskManager.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findOneByEmail(String email);
}
