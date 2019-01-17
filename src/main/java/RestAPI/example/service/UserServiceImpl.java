package RestAPI.example.service;

import RestAPI.example.model.User;
import RestAPI.example.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UsersRepo usersRepo;

	@Override
	public User getById(Long id) {
		return usersRepo.findById(id).orElse(null);
	}

	@Override
	public void create(User user) {
		usersRepo.save(user);
	}

	@Override
	public List<User> getAll() {
		return usersRepo.findAll();
	}
	@Override
	public void delete(Long id) {
		usersRepo.deleteById(id);
	}

}
