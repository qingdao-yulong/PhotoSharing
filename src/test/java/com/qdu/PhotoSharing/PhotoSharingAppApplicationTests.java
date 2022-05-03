package com.qdu.PhotoSharing;

import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PhotoSharingAppApplicationTests {

	private UserService userService;

	@Autowired
	public PhotoSharingAppApplicationTests(UserService userService) {
		this.userService = userService;
	}

	@Test
	void contextLoads() {

	}

	@Test
	void testDirectorCrud() {
		User user =  userService.getUserById(1);
		System.out.println(user);
	}

}
