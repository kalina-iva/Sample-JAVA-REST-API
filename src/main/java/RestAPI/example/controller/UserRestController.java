package RestAPI.example.controller;

import RestAPI.example.service.*;
import RestAPI.example.model.User;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/users/")
public class UserRestController {
	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	@Autowired
	private UserService userService;

	// get user
	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("id") Long userId) {
		logger.info("Получение инф. о пользователе с id:{}", userId);
		User user = userService.getById(userId);

		if (user == null) {
			logger.error("Пользователь с id:{} не найден.", userId);
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// get all users
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<List<User>> listAllUsers() {
		logger.info("Получение списка пользователей");
		List<User> users = userService.getAll();
		if (users.isEmpty()) {
			logger.info("Список не получен, т.к. он пуст");
			return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// create user
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
		logger.info("Добавление пользователя: {}", user);

		userService.create(user);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/users/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// update user
	@RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
		logger.info("Обновление информации о пользователе с id:{}", id);

		User currentUser = userService.getById(id);

		if (currentUser == null) {
			logger.error("Пользователь с id:{} не найден.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setEmail(user.getEmail());
		currentUser.setBirthdate(user.getBirthdate());
		currentUser.setAge(user.getAge());
		currentUser.setBudget(user.getBudget());
		currentUser.setStatus(user.getStatus());

		userService.create(currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// delete user
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		logger.info("Удаление пользователя с id:{}", id);
		User user = userService.getById(id);
		if (user == null) {
			logger.error("Удаление невозможно. Пользователь с id:{} не найден.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		userService.delete(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}
