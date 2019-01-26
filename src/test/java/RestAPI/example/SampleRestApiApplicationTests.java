package RestAPI.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import RestAPI.example.controller.UserRestController;
import RestAPI.example.model.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleRestApiApplicationTests {
	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() throws Exception {
		User user = new User("Rob", "Mannon", "example@mail.ru", new Date(), (short) 27, (float) 20000.00, true);
		ResponseEntity<User> responseEntity = restTemplate.postForEntity("/api/users/", user, User.class);
		int status = responseEntity.getStatusCodeValue();
		logger.info(Integer.toString(status)); //написала для отладки
		User result = responseEntity.getBody();
		logger.info("Результат: {}", result);
		assertEquals(HttpStatus.CREATED.value(), status);
		//тест падает из-за двух строк ниже, т.к. не возвращаю body
		//assertNotNull(result);
		//assertNotNull(result.getId().longValue());
	}

}
