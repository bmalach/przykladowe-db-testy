package pl.edu.zut.kisi.dyplom.mysql;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

//@RunWith(SpringRunner.class)
@SpringBootTest
class TestyWydajnosciMysqlApplicationTests {

	@Autowired
	PersonRepository personRepository;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void testSingleWrite() {
		Person p = new Person("Jan","Kowalski",22);
		personRepository.save(p);
		List<Person> people = personRepository.findAll();
		System.out.println(people.size());
		assertTrue(people.size() > 0);
	}

	@Test
	public void testRead() {
		List<Person> people = personRepository.findAll();
		System.out.println("Liczba rekordów" + people.size());
		assertTrue(people.size() > 0);
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testWriteTransactional() {
		for(int i=0; i < 1000000; i++) {
			Person p = new Person(
					RandomStringUtils.random(new Random().nextInt(5) + 5, true, false),
					RandomStringUtils.random(new Random().nextInt(5) + 10, true, false),
					new Random().nextInt(20) + 10);
			personRepository.save(p);
		}
	}

	@Test
	public void testWrite() {
		for(int i=0; i < 1000; i++) {
			Person p = new Person(
				RandomStringUtils.random(new Random().nextInt(5) + 5, true, false),
				RandomStringUtils.random(new Random().nextInt(5) + 10, true, false),
				new Random().nextInt(20) + 10);
			int numberOfItems = new Random().nextInt(4) + 2;
			for(int n=0; n < numberOfItems; n++) {
				String randomItemName = RandomStringUtils.random(new Random().nextInt(5) + 5, true, false);
				p.getItems().add(new Item(randomItemName));
			}
			personRepository.save(p);
		}
	}

}
