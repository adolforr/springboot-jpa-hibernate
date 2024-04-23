package com.springboot.springjpahibernate.springbootjpahibernate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springboot.springjpahibernate.springbootjpahibernate.data.PersonDao;
import com.springboot.springjpahibernate.springbootjpahibernate.entities.Person;

@SpringBootApplication
public class SpringbootJpaHibernateApplication implements CommandLineRunner {

	@Autowired
	private PersonDao personDao;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Person> persons = personDao.findAll();
		persons.stream().forEach(person -> System.out.println(person));
	}

}
