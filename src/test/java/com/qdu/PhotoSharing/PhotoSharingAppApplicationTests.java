package com.qdu.PhotoSharing;

import com.qdu.PhotoSharing.entity.Photo;
import com.qdu.PhotoSharing.entity.User;
import com.qdu.PhotoSharing.helper.PhotoLikeHelper;
import com.qdu.PhotoSharing.service.PhotoService;
import com.qdu.PhotoSharing.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PhotoSharingAppApplicationTests {

	private UserService userService;
	private PhotoLikeHelper photoLikeHelper;
	private PhotoService photoService;

	@Autowired
	public PhotoSharingAppApplicationTests(UserService userService, PhotoLikeHelper photoLikeHelper, PhotoService photoService) {
		this.userService = userService;
		this.photoLikeHelper = photoLikeHelper;
		this.photoService = photoService;
	}

	@Test
	void contextLoads() {

	}

	@Test
	void testUserCrud() {
		User user =  userService.getUserById(1);
		System.out.println(user);
	}

	@Test
	void testPhotoLikeCalculatorHelper() {
		List<Photo> photoList = photoService.getPhotosByUserId(1);
		photoLikeHelper.calculatePhotoLikesForList(photoList);
		for (Photo p: photoList) {
			System.out.println(p.getLikes());
		}
	}

}
