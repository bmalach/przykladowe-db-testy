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

	@Autowired
	ItemRepository itemRepository;

	@Test
	void contextLoads() {
	}


	//	@Disabled
	@Test
	public void testSingleWrite() {
		Person p = new Person("Jan","Kowalski",22);
		personRepository.save(p);
		List<Person> people = personRepository.findAll();
		System.out.println("Rozmiar: " + people.size());
		assertTrue(people.size() > 0);
	}
	@Test
	public void testRead() {
		List<Person> people = personRepository.findAll();
		people.forEach(p -> {
			String s = p.getFirstName() + " " + p.getLastName();
		});
		System.out.println("Liczba rekordów: " + people.size());
		assertTrue(people.size() > 0);
	}

	@Test
	public void testWrite() {
		for(int i=0; i < 1000; i++) {
			Person p = new Person(
					RandomStringUtils.random(new Random().nextInt(5) + 5),
					RandomStringUtils.random(new Random().nextInt(5) + 10),
					new Random().nextInt(20) + 10);
			int numberOfItems = new Random().nextInt(4) + 2;
			for(int n=0; n < numberOfItems; n++) {
				String randomItemName = RandomStringUtils.random(new Random().nextInt(5) + 5, true, false);
				Item item = new Item(randomItemName);
				itemRepository.save(item);
				p.getItems().add(item);
			}
			personRepository.save(p);
		}
	}

}
