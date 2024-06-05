package com.azqore.demo.controller;

import com.azqore.demo.entity.PriorityTask;
import com.azqore.demo.entity.Task;
import com.azqore.demo.service.TaskService;
import com.azqore.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller // Permet d'identifier un controlleur MVC
@RequestMapping("/tasks") // Permet d'associer la classe a une URL
public class TaskController {

    // Injection de dépendances automatique : Spring Boot fournit automatique la ressource préalablement scannée
    // Il fait l'instanciation automatiquement
    private final TaskService taskService;
    private final UserService userService;

    // Constructeur pour l'injection automatique du service @Service
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping(path = "/all") // Permet d'associer la methode a une URL en GET
    public String displayTasks(Model model){
        // Mettre des données dans la page
        // Données utilisables dans la vue avec Thymeleaf (   <p th:text="${firstnameKey}"></p> )
        model.addAttribute("firstnameKey", "Boris");

        // Recuperation des taches
        List<Task> tasks = taskService.fetchTaskList();
        // mettre les taches dans le model, en utilisant une clée
        model.addAttribute("tasklist",tasks);

        // Retourne la page HTML de notre choix au navigateur
        // La page doit être située dans le dossier resources/templates
        return "task-list";
    }

    @GetMapping("/add")
    public String displayCreateTaskForm(Model model){
        model.addAttribute("newTask", new Task());
        model.addAttribute("priorityList", PriorityTask.values());
        model.addAttribute("assignedUserList", userService.fetchUsers());
        return "task-add";
    }

    @PostMapping("/add")
    public String handleTaskSubmission(Task submittedTask){
          taskService.addTask(submittedTask);
        // Rediriger l'utilisateur vers la liste des taches
        return "redirect:/tasks/all";
    }

    @PostMapping("/delete/{idTask}")
    public String deleteTask(@PathVariable("idTask") Long id){
        taskService.deleteSpecificTask(id);
        return "redirect:/tasks/all";
    }

    @PostMapping("/status")
    public String toggleTaskStatus(Long idTask){
        taskService.toggleStatus(idTask);
        return "redirect:/tasks/all";
    }

}
