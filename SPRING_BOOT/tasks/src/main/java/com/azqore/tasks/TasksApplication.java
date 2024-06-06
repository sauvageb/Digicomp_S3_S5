package com.azqore.tasks;

import com.azqore.tasks.repository.CommentRepository;
import com.azqore.tasks.repository.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@RequiredArgsConstructor
public class TasksApplication implements CommandLineRunner {

	private final CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Comment c1 = Comment.builder()
				.message("Commentaire 1")
				.createdAt(LocalDateTime.now())
				.taskId(1L)
				.build();

		Comment c2 = Comment.builder()
				.message("Commentaire 2")
				.createdAt(LocalDateTime.now().minusDays(10))
				.taskId(1L)
				.build();

		Comment c3 = Comment.builder()
				.message("Commentaire 3")
				.createdAt(LocalDateTime.now().minusDays(10).minusMonths(2))
				.taskId(1L)
				.build();

		commentRepository.save(c1);
		commentRepository.save(c2);
		commentRepository.save(c3);
	}
}
