package rltw.awards.auth.repository;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import rltw.awards.auth.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);

    public Optional<User> getByUsername(String username);
}
