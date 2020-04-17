package pl.edu.zut.kisi.dyplom.mongo;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.zut.kisi.dyplom.mongo.Person;
import pl.edu.zut.kisi.dyplom.mongo.PersonRepository;

@SpringBootTest
class TestyWydajnosciMongoApplicationTests {
	
	@Autowired
	PersonRepository personRepository;

	@Test
	void contextLoads() {
	}

	@Disabled
	@Test
//	@Transactional
	public void testRead() {
		Person p = new Person("Jan","Kowalski",22);
		personRepository.save(p);
		List<Person> people = personRepository.findAll();
		System.out.println(people.size());
		assertTrue(people.size() > 0);
	}
	
	@Test
	public void writeMillion() {
		for(int i=0; i < 1000000; i++) {
			Person p = new Person(
					RandomStringUtils.random(new Random().nextInt(5) + 5),
					RandomStringUtils.random(new Random().nextInt(5) + 10),
					new Random().nextInt(20) + 10);
			personRepository.save(p);
		}
	}

}
