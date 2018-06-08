package com.mydevgeek;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/users")
public class UserController {

	private List<User> users;


	UserController() {
		this.users = buildUsers();
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<User> getUsers() {
		if (this.users.size() == 0) {
			return Collections.emptyList();
		} else {
			return this.users;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable("id") Long id) {
		return this.users.stream().filter(user -> user.getId() == id).findFirst().get();
	}

	@RequestMapping(method = RequestMethod.POST)
	public User saveUser(@RequestBody User user) {
		Long nextId = 0L;
		if (this.users.size() != 0) {
			User lastUser = this.users.stream().skip(this.users.size() - 1).findFirst().get();
			nextId = lastUser.getId() + 1;
		}

		user.setId(nextId);
		this.users.add(user);
		return user;

	}

	@RequestMapping(method = RequestMethod.PUT)
	public User updateUser(@RequestBody User user) {
		User modifiedUser = this.users.stream().filter(u -> u.getId() == user.getId()).findFirst().get();
		modifiedUser.setFirstName(user.getFirstName());
		modifiedUser.setLastName(user.getLastName());
		modifiedUser.setEmail(user.getEmail());
		modifiedUser.setCompany(user.getCompany());
		modifiedUser.setAddress(user.getAddress());
		return modifiedUser;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable Long id) {
		User deleteUser = this.users.stream().filter(user -> user.getId() == id).findFirst().get();
		if (deleteUser != null) {
			this.users.remove(deleteUser);
			return true;
		} else  {
			return false;
		}


	}

	List<User> buildUsers() {
		List<User> users = new ArrayList<>();

		User user1 = buildUser(1L, "Rajasekhar", "Gonnala", "rajasekhar.gonnala@gmail.com","LTI","Kurnool");
		User user2 = buildUser(2L, "Goverdan", "Sanga", "goverdan.sanga@gmail.com","LTI","Kurnool");
		User user3 = buildUser(3L, "Aarush", "Gupta", "aarush.gupta@gmail.com","Cisco","Banglore");
		User user4 = buildUser(4L, "Bharath", "G", "bharath.g@gmail.com","Cisco","Banglore");
		User user5 = buildUser(5L, "Parmesh", "A", "parmesh.a@gmail.com","Cisco","Banglore");
		User user6 = buildUser(6L, "Eeranna", "Kuruva", "eeranna.kuruva@gmail.com","LTI","Kurnool");
		User user7 = buildUser(7L, "Amrutha", "Chandra", "amrutha.chandra@gmail.com","Cisco","Banglore");
		User user8 = buildUser(8L, "Aparna", "George", "aparna.george@gmail.com","Cisco","Banglore");
		User user9 = buildUser(9L, "Tejas", "Kuruva", "tejas.kuruva@gmail.com","Cisco","Kurnool");

		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);
		users.add(user8);
		users.add(user9);

		return users;

	}

	User buildUser(Long id, String fname, String lname, String email, String company, String address) {
		User user = new User();
		user.setId(id);
		user.setFirstName(fname);
		user.setLastName(lname);
		user.setEmail(email);
		user.setCompany(company);
		user.setAddress(address);
		return user;
	}

}
