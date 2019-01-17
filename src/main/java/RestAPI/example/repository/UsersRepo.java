package RestAPI.example.repository;

import RestAPI.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Long> {

}
