package RestAPI.example.model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String firstName;
	@Column
	private String lastName;
	@Column
	private String email;
	@Column
	private Date birthdate;
	@Column
	private short age;
	@Column
	private float budget;
	@Column
	private boolean status;

	protected User() {
	}

	public User(String firstName, String lastName, String email, Date birthdate, short age, float budget, boolean status) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.birthdate=birthdate;
		this.age = age;
		this.budget = budget;
		this.status = status;
	}

	@Override
	public String toString() {
		return "Users{" + 
				"id=" + id + 
				", firstName='" + firstName + '\'' + 
				", lastName='" + lastName + '\'' + 
				", email='" + email + '\'' + 
				", birthdate='" + birthdate + '\'' + 
				", age='" + age + '\'' + 
				", budget='" + budget + '\'' + 
				", status=" + status + '}';
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public short getAge() {
		return age;
	}

	public void setAge(short age) {
		this.age = age;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
