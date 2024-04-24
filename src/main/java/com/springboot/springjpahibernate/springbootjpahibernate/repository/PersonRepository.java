package com.springboot.springjpahibernate.springbootjpahibernate.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;

import com.springboot.springjpahibernate.springbootjpahibernate.dto.PersonDto;
import com.springboot.springjpahibernate.springbootjpahibernate.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Person> buscarByProgrammingLanguage(String programmingLanguage, String name);

    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1 and p.name=?2")
    List<Object[]> obtenerPersonData(String programmingLanguage, String name);

    @Query("select p.name, p.programmingLanguage from Person p where p.programmingLanguage=?1")
    List<Object[]> obtenerPersonDataByProgrammingLanguage(String programmingLanguage);

    @Query("select p from Person p Where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p Where p.name=?1")
    Optional<Person> findOneName(String name);

    @Query("select p from Person p Where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);
    
    Optional<Person> findByNameContaining(String name);

    /*JPA */
    @Query("select p.name from Person p where p.id = ?1")
    String getNameById(Long id);

    @Query("select concat(p.name,' ',p.lastname) from Person p where p.id = ?1")
    String getFullNameById(Long id);

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonDataList();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Optional<Object> obtenerPersonDataById(Long id);
    
    @Query("select p.name, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllObjectPersonPersonalized();

    @Query("select new com.springboot.springjpahibernate.springbootjpahibernate.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    @Query("select distinct(p.name) from Person p")
    List<String> findAllNamesDistinct();

    @Query("select distinct(p.programmingLanguage) from Person p")
    List<String> findAllProgrammingLanguageDistinct();

    @Query("select count(distinct(p.programmingLanguage)) from Person p")
    Long findAllProgrammingLanguageDistinctCount();

    // @Query("select CONCAT(p.name, ' ', p.lastname) from Person p")
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select p.id, upper(p.name), lower(p.lastname), upper(p.programmingLanguage) from Person p")
    List<Object[]> findAllPersonDataListCase();
    
    @Query("select upper(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameConcatUpper();
    
    @Query("select lower(concat(p.name, ' ', p.lastname)) from Person p")
    List<String> findAllFullNameConcatLower();

    @Query("select p from Person p where p.name between ?1 and ?2 order by p.name asc, p.lastname desc")// con JPQL
    List<Person> findAllBetweenName(String c1, String c2);

    @Query("select p from Person p where p.id between ?1 and ?2 order by p.name desc") // con JPQL
    List<Person> findAllBetweenId(Long id1, Long id2);

    List<Person> findByIdBetweenOrderByNameAsc(Long id1, Long id2); // Con HQL

    List<Person> findByNameBetweenOrderByNameDescLastnameDesc(String name1, String name2); //Con HQL
  

    @Query("select p from Person p order by p.name, p.lastname desc")// con JPQL
    List<Person> getAllOrdered();

    List<Person> findAllByOrderByNameAscLastnameDesc();// con HQL

    /* funciones JPQL */

    @Query("select max(p.id) from Person p")
    Long getMaxId();

    @Query("select min(p.id) from Person p")
    Long getMinId();

    @Query("select count(p) from Person p")
    Long getTotalPerson();

    @Query("select p.name, length(p.name) from Person p")
    public List<Object[]> getPersonNameLength();

    @Query("select max(length(p.name)) from Person p")
    public Integer getMaxLengthName();

    @Query("select min(length(p.name)) from Person p")
    public Integer getMinLengthName();

    @Query("select min(p.id), max(p.id), sum(p.id), avg(length(p.name)), count(p.id) from Person p")
    public Object getResumeAggregationFunction();

    @Query("select p from Person p where p.id=(select max(p.id) from Person p)")
    public Optional<Person> getLastRegistration();

    @Query("select p.name, length(p.name) from Person p where length(p.name)=(select min(length(p.name)) from Person p)")
    public List<Object[]> getShorterName();

    @Query("select p from Person p where p.id in ?1") // Equivalende en SQL WHERE p.id in (1,2,4,6)
    public List<Person> getPersonsByIds(List<Long> ids);

}
