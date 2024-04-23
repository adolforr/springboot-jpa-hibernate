package com.springboot.springjpahibernate.springbootjpahibernate.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.springjpahibernate.springbootjpahibernate.entities.Person;

public interface PersonDao extends JpaRepository<Person, Long> {

}
