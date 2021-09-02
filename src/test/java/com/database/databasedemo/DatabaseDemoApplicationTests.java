package com.database.databasedemo;

import com.database.databasedemo.entity.Course;
import com.database.databasedemo.entity.Person;
import com.database.databasedemo.repository.CourseRepository;
import com.database.databasedemo.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepository;

	@Autowired
	StudentRepository studentRepository;


	@Autowired
	EntityManager em;

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
	@DirtiesContext // This reset the data of DB to its initial state after the test ends.
	public void deleteCourseById(){
		courseRepository.deleteById(2L);
		assertNull(courseRepository.findById(2L));
	}

	@Test
	@Transactional // The session is kill only at the end of the test, when you want to perform more than one transaction you should use transaction.
	// Adding this notation, you create a session or persistent context that manages all changes performed on database.
	public void retrieveStudentAndPassportDetails(){ // Eager, always one to one is eager
		Person person = em.find(Person.class, 10001);
		logger.info("person -> {}", person);
		logger.info("passport -> {}", person.getPassport()); // When you request details on lazy test hibernate executes another query to retrieve that information
	}


}
