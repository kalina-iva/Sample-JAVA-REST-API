package RestAPI.example.service;

import RestAPI.example.model.User;
import java.util.List;

public interface UserService {
	User getById(Long id);

	void create(User user);

	//+update
	
	void delete(Long id);

	List<User> getAll();
	
}
