package com.azqore.demo.app;

import com.azqore.demo.entity.PriorityTask;
import com.azqore.demo.entity.Task;
import com.azqore.demo.repository.TaskRepository;
import com.azqore.demo.entity.User;
import com.azqore.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;

// Annotation unique au projet. Permet de scanner tous les
// composants de Spring Boot (@Controller, @Service, @Repository, @Component) afin de les rendre utilisables
@SpringBootApplication(scanBasePackages = {"com.azqore.demo.*"})
@EnableJpaRepositories(basePackages = "com.azqore.demo.repository")
@EntityScan(basePackages = "com.azqore.demo.entity")
public class DemoApplication implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public DemoApplication(TaskRepository taskRepository, UserRepository userRepository) {
		this.taskRepository = taskRepository;
		this.userRepository = userRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User( "Boris", "Sauvage", "sauvageboris.pro@gmail.com");
		User user2 = new User("Jane", "Doe", "jane.doe@email.fr");
		User user3 = new User("John", "Doe", "john.doe@email.fr");

		userRepository.saveAll(List.of(user1, user2, user3));

		Task task1 = new Task("Faire l'émargement", PriorityTask.LOW, LocalDate.of(2024,5,17), user1);
		task1.setDone(true);
		Task task2 = new Task("Apprendre à utiliser le clavier QWERTZ", PriorityTask.MEDIUM, LocalDate.now(), user2);
		Task task3 = new Task("Faire du Java", PriorityTask.HIGH, LocalDate.of(2024,5,17), user3);

		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
	}
}
