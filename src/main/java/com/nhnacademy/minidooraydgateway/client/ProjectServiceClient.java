package com.nhnacademy.minidooraydgateway.client;

import com.nhnacademy.minidooraydgateway.domain.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "projectService", url = "${api.project-service.base-url}")
public interface ProjectServiceClient {

    @GetMapping("/projects")
    Page<Project> getAllProjects(@RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("/projects/{projectId}")
    Project getProjectById(@PathVariable("projectId") String projectId);

    @PostMapping("/projects")
    Project createProject(@RequestBody Project request);

    @PutMapping("/projects/{projectId}")
    Project updateProject(@PathVariable("projectId") String projectId, @RequestBody Project request);

    @DeleteMapping("/projects/{projectId}")
    void deleteProject(@PathVariable("projectId") String projectId);

    @PostMapping("/projects/{projectId}/members")
    void addMemberToProject(@PathVariable("projectId") String projectId, @RequestBody User user);

    @GetMapping("/projects/{projectId}/members")
    Page<User> getProjectMembers(@PathVariable("projectId") String projectId, @RequestParam("page") int page, @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/members/{memberId}")
    void removeMemberFromProject(@PathVariable("projectId") String projectId, @PathVariable("memberId") String memberId);

    @GetMapping("/projects/{projectId}/tasks")
    Page<Task> getAllTasks(@PathVariable("projectId") String projectId, @RequestParam("page") int page, @RequestParam("size") int size);

    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    Task getTaskById(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId);

    @PostMapping("/projects/{projectId}/tasks")
    Task createTask(@PathVariable("projectId") String projectId, @RequestBody Task request);

    @PutMapping("/projects/{projectId}/tasks/{taskId}")
    Task updateTask(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @RequestBody Task request);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    void deleteTask(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/comments")
    Comment createComment(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @RequestBody Comment request);

    @GetMapping("/projects/{projectId}/tasks/{taskId}/comments")
    Page<Comment> getTaskComments(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @RequestParam("page") int page, @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}/comments/{commentId}")
    void deleteComment(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @PathVariable("commentId") String commentId);

    @PostMapping("/projects/{projectId}/tags")
    Tag createTag(@PathVariable("projectId") String projectId, @RequestBody Tag request);

    @GetMapping("/projects/{projectId}/tags")
    Page<Tag> getProjectTags(@PathVariable("projectId") String projectId, @RequestParam("page") int page, @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/tags/{tagId}")
    void deleteTag(@PathVariable("projectId") String projectId, @PathVariable("tagId") String tagId);

    @PostMapping("/projects/{projectId}/milestones")
    Milestone createMilestone(@PathVariable("projectId") String projectId, @RequestBody Milestone request);

    @GetMapping("/projects/{projectId}/milestones")
    Page<Milestone> getProjectMilestones(@PathVariable("projectId") String projectId, @RequestParam("page") int page, @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/milestones/{milestoneId}")
    void deleteMilestone(@PathVariable("projectId") String projectId, @PathVariable("milestoneId") String milestoneId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/tags/{tagId}")
    void addTagToTask(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @PathVariable("tagId") String tagId);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}/tags/{tagId}")
    void removeTagFromTask(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @PathVariable("tagId") String tagId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/milestones/{milestoneId}")
    void addMilestoneToTask(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @PathVariable("milestoneId") String milestoneId);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}/milestones/{milestoneId}")
    void removeMilestoneFromTask(@PathVariable("projectId") String projectId, @PathVariable("taskId") String taskId, @PathVariable("milestoneId") String milestoneId);
}
