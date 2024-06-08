package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.domain.Milestone;
import com.nhnacademy.minidooraydgateway.domain.Tag;
import com.nhnacademy.minidooraydgateway.domain.Task;
import com.nhnacademy.minidooraydgateway.dto.ProjectGetDto;
import com.nhnacademy.minidooraydgateway.dto.TaskCreateRequest;
import com.nhnacademy.minidooraydgateway.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final ProjectService projectService;
    private final ProjectMemberService projectMemberService;
    private final TagService tagService;
    private final MilestoneService milestoneService;
    private final UserService userService;

    // 특정 프로젝트의 Task 목록 페이지
    @GetMapping
    public String getTasksPage(@PathVariable Long projectId,
                               Pageable pageable,
                               Model model) {
        ProjectGetDto project = projectService.getProjectById(projectId);
        Page<Task> tasks = taskService.getAllTasksByProjectId(projectId, pageable);
        List<Tag> tags = tagService.getTagsByProjectId(projectId);
        List<Milestone> milestones = milestoneService.getMilestonesByProjectId(projectId);
        List<Long> memberIds = projectMemberService.getAllProjectMembers(projectId);

        model.addAttribute("project", project);
        model.addAttribute("tasks", tasks);
        model.addAttribute("tags", tags);
        model.addAttribute("milestones", milestones);
        model.addAttribute("members", memberIds);
        model.addAttribute("projectId", projectId);
        return "project/taskList";
    }

    // Task 상세 정보 페이지
    @GetMapping("/{taskId}")
    public String getTaskDetailsPage(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        Task task = taskService.getTaskByProjectId(projectId, taskId);
        model.addAttribute("task", task);
        model.addAttribute("projectId", projectId);
        return "task/taskDetail";
    }

    // Task 생성 페이지
    @GetMapping("/new")
    public String getNewTaskPage(@PathVariable Long projectId, Model model) {
        model.addAttribute("task", TaskCreateRequest.builder().build());
        return "task/newTask";
    }

    // Task 생성 처리
    @PostMapping
    public String handleCreateTask(@PathVariable Long projectId, @ModelAttribute TaskCreateRequest taskCreateRequest) {
        taskService.createTask(projectId, taskCreateRequest);
        return "redirect:/projects/" + projectId + "/tasks";
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