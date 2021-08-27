package com.database.databasedemo;

import com.database.databasedemo.entity.Course;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.repository.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@SpringBootTest
class DatabaseDemoApplicationTests {

	@Autowired
	CourseRepository courseRepository;

	@Test
	void contextLoads() {
		Person person = new Person(123456, "Name", "description", new Date());
		int actual = person.getId();
		assertEquals(123456, actual);
		assertTrue(true);
		assertFalse(false);
		assertNotNull(true);
		assertNull(null);
	}

	@Test
	@DisplayName("Test find course by id")
	public void findCourseById(){
		Course course = courseRepository.findById(1L);
		assertEquals("Java", course.getName());
	}

	@Test
	public void deleteCourseById(){
		courseRepository.deleteById(2L);
		assertNull(courseRepository.findById(2L));
	}

}
