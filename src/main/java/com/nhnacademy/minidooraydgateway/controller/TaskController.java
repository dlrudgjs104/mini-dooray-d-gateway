package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.domain.Task;
import com.nhnacademy.minidooraydgateway.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    // 특정 프로젝트의 Task 목록 페이지
    @GetMapping
    public String getTasksPage(@PathVariable Long projectId,
                               Pageable pageable,
                               Model model) {
        Page<Task> tasks = taskService.getAllTasksByProjectId(projectId, pageable);
        model.addAttribute("tasks", tasks);
        model.addAttribute("projectId", projectId);
        return "task/taskList";
    }

    // Task 생성 페이지
    @GetMapping("/new")
    public String getNewTaskPage(@PathVariable Long projectId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("projectId", projectId);
        return "task/newTask";
    }

    // Task 생성 처리
    @PostMapping
    public String handleCreateTask(@PathVariable Long projectId, @ModelAttribute Task task) {
        taskService.createTask(projectId, task);
        return "redirect:/projects/" + projectId + "/tasks";
    }

    // Task 상세 정보 페이지
    @GetMapping("/{taskId}")
    public String getTaskDetailsPage(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        Task task = taskService.getTaskByProjectId(projectId, taskId);
        model.addAttribute("task", task);
        model.addAttribute("projectId", projectId);
        return "task/taskDetail";
    }

    // Task 수정 페이지
    @GetMapping("/{taskId}/edit")
    public String getEditTaskPage(@PathVariable Long projectId,
                                  @PathVariable Long taskId,
                                  @ModelAttribute Task taskForm,
                                  Model model) {
        model.addAttribute("task", new Task());
        return "task/editTask";
    }

    // Task 수정 처리
    @PostMapping("/{taskId}/edit")
    public String handleEditTask(@PathVariable Long projectId,
                                 @PathVariable Long taskId,
                                 @ModelAttribute Task taskForm) {
        taskService.updateTask(projectId, taskId, taskForm);
        return "redirect:/projects/" + projectId + "/tasks";
    }

    // Task 삭제 처리
    @PostMapping("/{taskId}/delete")
    public String handleDeleteTask(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTask(projectId, taskId);
        return "redirect:/projects/" + projectId + "/tasks";
    }

    // Task에 Milestone 설정
    @PostMapping("/{taskId}/milestones")
    public String setTaskMilestone(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Long milestoneId) {
        taskService.setTaskMilestone(projectId, taskId, milestoneId);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    // Task에 설정된 Milestone 삭제
    @DeleteMapping("/{taskId}/milestones")
    public String deleteTaskMilestone(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTaskMilestone(projectId, taskId);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    // Task에 Tag 설정
    @PostMapping("/{taskId}/tags")
    public String setTaskTag(@PathVariable Long projectId, @PathVariable Long taskId, @RequestBody Long tagId) {
        taskService.setTaskTag(projectId, taskId, tagId);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    // Task에 설정된 Tag 삭제
    @DeleteMapping("/{taskId}/tags")
    public String deleteTaskTag(@PathVariable Long projectId, @PathVariable Long taskId) {
        taskService.deleteTaskTag(projectId, taskId);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }
}