package com.database.databasedemo;

import com.database.databasedemo.entity.Person;
import org.junit.jupiter.api.Test;
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

	@Test
	void contextLoads() {
		Person person = new Person(123456, "Name", "description", new Date());
		int actual = person.getId();
		assertEquals(123456, actual);
		assertTrue(true);
		assertFalse(true);
		assertNotNull(null);
		assertNull(null);
	}

}
