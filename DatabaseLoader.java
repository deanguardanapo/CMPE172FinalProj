package com.registernotes.notes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

	private final EmployeeRepository employees;
	private final ManagerRepository managers;

	@Autowired
	public DatabaseLoader(EmployeeRepository employeeRepository,
						  ManagerRepository managerRepository) {

		this.employees = employeeRepository;
		this.managers = managerRepository;
	}

	@Override
	public void run(String... strings) throws Exception {

		Manager dean = this.managers.save(new Manager("dean", "guardanapo",
							"ROLE_MANAGER"));
		Manager emilio = this.managers.save(new Manager("emilio", "chavoya",
							"ROLE_MANAGER"));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("dean", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("10/01/21", "Study Maven", "Maven is a build automation tool used primarily for Java projects. "
				+ "Maven can also be used to build and manage projects written in C#, Ruby, Scala, and other languages.", dean));
		this.employees.save(new Employee("10/02/21", "Study Springboot", "Spring Boot makes it easy to create stand-alone,"
				+ " production-grade Spring based Applications that you can \"just run\".", dean));
		this.employees.save(new Employee("10/03/21", "Study Docker", "Docker is a set of platform as a service products that "
				+ "use OS-level virtualization to deliver software in packages called containers.", dean));

		SecurityContextHolder.getContext().setAuthentication(
			new UsernamePasswordAuthenticationToken("emilio", "doesn't matter",
				AuthorityUtils.createAuthorityList("ROLE_MANAGER")));

		this.employees.save(new Employee("11/01/21", "Study AWS", "Amazon Web Services, Inc. is a subsidiary of Amazon "
				+ "providing on-demand cloud computing platforms and APIs to individuals", emilio));
		this.employees.save(new Employee("11/02/21", "Study React", "React is a free and open-source front-end "
				+ "JavaScript library for building user interfaces based on UI components", emilio));
		this.employees.save(new Employee("11/03/21", "Study RDS", "Amazon Relational Database Service is a distributed "
				+ "relational database service by Amazon Web Services. It is a web service running \"in the cloud\" "
				+ "designed to simplify the setup, operation, and scaling of a relational database for use in applications.", emilio));

		SecurityContextHolder.clearContext();
	}
}
