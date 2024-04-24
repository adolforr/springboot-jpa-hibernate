package com.springboot.springjpahibernate.springbootjpahibernate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.springjpahibernate.springbootjpahibernate.dto.PersonDto;
import com.springboot.springjpahibernate.springbootjpahibernate.entities.Person;
import com.springboot.springjpahibernate.springbootjpahibernate.repository.PersonRepository;



@SpringBootApplication
public class SpringbootJpaHibernateApplication implements CommandLineRunner {

	@Autowired
	private PersonRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaHibernateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//list();
		//findOne();
		//create();
		//update();
		// deleteById();
		//delete();
		//customQueries();
		//customQueries2();
		customQueriesDistinct();
	}

	@Transactional(readOnly = true)
	public void findOne(){

		// Optional<Person> person = repository.findById(8L);

		// System.out.println(person);

		//repository.findById(1L).ifPresent(person -> System.out.println(person));
		// repository.findById(1L).ifPresent(System.out::println);
		// repository.findOne(1L).ifPresent(System.out::println);
		// repository.findOneName("Maria").ifPresent(System.out::println);
		// repository.findOneLikeName("pe").ifPresent(System.out::println);
		repository.findByNameContaining("pe").ifPresent(System.out::println);



	}

	@Transactional(readOnly = true)
	public void list(){

		//List<Person> persons = repository.findAll();
		// List<Person> persons = repository.findByProgrammingLanguage("Java");
		// List<Person> persons = repository.buscarByProgrammingLanguage("Java","Andres");
		List<Person> persons = repository.findByProgrammingLanguageAndName("Java","Andres");

		persons.stream().forEach(person -> System.out.println(person));

		List<Object[]> personsData = repository.obtenerPersonData("Java","Maria");
		personsData.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
		});

		List<Object[]> personsData2 = repository.obtenerPersonDataByProgrammingLanguage("Java");
		personsData2.stream().forEach(person -> {
			System.out.println(person[0] + " es experto en " + person[1]);
		});

	}

	@Transactional
	public void create(){
		Person person = new Person(null,"Lalo","Thor","Python");

		Person personNew = repository.save(person);

		System.out.println(personNew);
	}

	@Transactional
	public void update(){

		Optional<Person> optionalPerson = repository.findById(7L);
		if(optionalPerson.isPresent()){
			Person person = new Person(7L,"Eduardo","Thorum","Python");
			Person personDb = repository.save(person);
			System.out.println(personDb);
		}

	}

	@Transactional
	public void deleteById(){
		Optional<Person> optionalPerson = repository.findById(6L);
		if(optionalPerson.isPresent()){
			repository.findAll().forEach(System.out::println);
			repository.deleteById(6L);
			repository.findAll().forEach(System.out::println);
		}
	}

	@Transactional
	public void delete(){
		Optional<Person> optionalPerson = repository.findById(7L);
		optionalPerson.ifPresentOrElse(person -> repository.delete(person),
										() -> System.out.println("No existe la persona"));
		repository.findAll().forEach(System.out::println);
	}

	@Transactional(readOnly = true)
	public void customQueries(){
		System.out.println("=============Consuta del name por el id=========================");
		String name = repository.getNameById(3L);
		System.out.println(name);
		
		System.out.println("=============Consuta del name y lastname contatenado por el id=========================");
		String fullName = repository.getFullNameById(3L);
		System.out.println(fullName);

		System.out.println("===== Consulta por campos personalizados por el id =====");
		Optional<Object> optionalReg = repository.obtenerPersonDataById(4L);
		if (optionalReg.isPresent()) {
			Object[] personReg = (Object[]) optionalReg.orElseThrow();
			System.out.println("id="+ personReg[0] + ", nombre=" + personReg[1] + ", apellido=" + personReg[2]+ ", lenguaje="+personReg[3]);
		}

		System.out.println("===== Consulta por campos personalizados lista ======");
		List<Object[]> regs = repository.obtenerPersonDataList();
		regs.forEach(reg -> System.out.println("id="+ reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2]+ ", lenguaje="+reg[3]));
	}

	@Transactional(readOnly = true)
	public void customQueries2(){

		System.out.println("================== Consulta por objeto persona y lenguaje de programacion ==================");
		List<Object[]> personsRegs = repository.findAllMixPerson();

		personsRegs.forEach(reg -> {
			System.out.println("programmingLanguage=" + reg[1] + ", person=" + reg[0]);
		});

		System.out.println("==== Consulta que puebla y devuelve objeto entity de una instancia personalizada ====");
		List<Person> persons = repository.findAllObjectPersonPersonalized();
		persons.forEach(System.out::println);

		System.out.println("==== Consulta que puebla y devuelve objeto dto de una clase personalizada ====");
		List<PersonDto> personsDto = repository.findAllPersonDto();
		personsDto.forEach(System.out::println);

	}

	@Transactional(readOnly = true)
	public void customQueriesDistinct(){
		System.out.println("================== Consultas con nombres de personas ==================");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("==================  Consultas con nombres unicos de personas ==================");
		names = repository.findAllNamesDistinct();
		names.forEach(System.out::println);

		System.out.println("================== Consulta con lenguaje de programacion unicas ==================");
		List<String> languages = repository.findAllProgrammingLanguageDistinct();
		languages.forEach(System.out::println);

		System.out.println("================== Consulta con total de lenguajes de programacion unicas ==================");
		Long totalLanguage = repository.findAllProgrammingLanguageDistinctCount();
		System.out.println("total de lenguajes de programacion: " + totalLanguage);
	}

	@Transactional(readOnly = true)
	public void customQueriesConcatUpperAndLowerCase(){
		System.out.println("================== Consulta Concat nombres y apellidos de personas ==================");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("================== consulta nombres y apellidos mayuscula ==================");
		names = repository.findAllFullNameConcatUpper();
		names.forEach(System.out::println);

		System.out.println("================== consulta nombres y apellidos minuscula ==================");
		names = repository.findAllFullNameConcatLower();
		names.forEach(System.out::println);
		System.out.println("================== consulta personalizada persona upper y lower case ==================");
		List<Object[]> regs = repository.findAllPersonDataListCase();
		regs.forEach(reg -> System.out.println("id="+ reg[0] + ", nombre=" + reg[1] + ", apellido=" + reg[2]+ ", lenguaje="+reg[3]));
	}

	@Transactional(readOnly = true)
	public void customQueriesBeetween(){
		System.out.println("================== Consultas por rangos (between and Order by) ==================");

		// List<Person> persons = repository.findAllBetweenId(2L, 5L);
		// persons.forEach(System.out::println);

		// List<Person> persons = repository.findAllBetweenName("J", "P");
		// persons.forEach(System.out::println);
		
		List<Person> persons = repository.findByIdBetweenOrderByNameAsc(2L, 5L);
		persons.forEach(System.out::println);
		
		persons = repository.findByNameBetweenOrderByNameDescLastnameDesc("J", "Q");
		persons.forEach(System.out::println);

		persons = repository.getAllOrdered();
		persons.forEach(System.out::println);

		persons = repository.findAllByOrderByNameAscLastnameDesc();
		persons.forEach(System.out::println);
	}


	@Transactional(readOnly = true)
	public void queriesFunctionAggregation() {
		
		System.out.println("================== Consulta con el total de registros de la tabla persona ==================");
		Long count = repository.getTotalPerson();
		System.out.println(count);
		
		System.out.println("================== Consulta con el valor minimo del id ==================");
		Long min = repository.getMinId();
		System.out.println(min);
		
		System.out.println("================== Consulta con el valor maximo del id");
		Long max = repository.getMaxId();
		System.out.println(max);
		
		System.out.println("================== Consulta con el nombre y su largo ==================");
		List<Object[]> regs = repository.getPersonNameLength();
		regs.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);
		});
		
		System.out.println("================== Consulta con el nombre mas corto ==================");
		Integer minLengthName = repository.getMinLengthName();
		System.out.println(minLengthName);
		
		System.out.println("================== Consulta con el nombre mas largo ==================");
		Integer maxLengthName = repository.getMaxLengthName();
		System.out.println(maxLengthName);

		System.out.println("================== Consultas resumen de funciones de agregacion min, max, sum, avg, count ==================");
		Object[] resumeReg = (Object[]) repository.getResumeAggregationFunction();
		System.out.println(
			    "min=" + resumeReg[0] +
				", max=" + resumeReg[1] +
				", sum=" + resumeReg[2] +
				", avg=" + resumeReg[3] +
		        ", count=" + resumeReg[4]);

	}

	@Transactional(readOnly = true)
	public void subQueries() {
		System.out.println("================== consulta por el nombre mas corto y su largo ==================");
		List<Object[]> registers = repository.getShorterName();
		registers.forEach(reg -> {
			String name = (String) reg[0];
			Integer length = (Integer) reg[1];
			System.out.println("name=" + name + ", length=" + length);	
		});

		System.out.println("================== consulta pra obtener el ultimo registro de persona ==================");
		Optional<Person> optionalPerson = repository.getLastRegistration();
		optionalPerson.ifPresent(System.out::println);
	}

	@Transactional(readOnly = true)
	public void whereIn() {
		System.out.println("================== consulta where in ==================");
		List<Person> persons = repository.getPersonsByIds(Arrays.asList(1L, 2L, 5L, 7L));
		persons.forEach(System.out::println);
	}

}
