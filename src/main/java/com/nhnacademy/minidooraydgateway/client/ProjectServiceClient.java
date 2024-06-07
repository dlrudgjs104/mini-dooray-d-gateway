package com.nhnacademy.minidooraydgateway.client;

import com.nhnacademy.minidooraydgateway.domain.*;
import com.nhnacademy.minidooraydgateway.dto.ProjectDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "projectService", url = "${api.project-service.base-url}")
public interface ProjectServiceClient {

    // 특정 유저의 프로젝트 목록 조회
    @GetMapping("/users/{userId}/projects")
    ResponseEntity<Page<Project>> getAllProjectsByUserId(@PathVariable long userId,
                                                         @RequestParam("page") int page,
                                                         @RequestParam("size") int size,
                                                         @RequestParam(required = false) String sort);


    // Project
    @GetMapping("/projects/{projectId}")
    ResponseEntity<ProjectDto> getProjectById(@PathVariable("projectId") Long projectId);

    @PostMapping("/projects")
    ResponseEntity<Project> createProject(@RequestBody ProjectDto projectDto);

    @PostMapping("/projects/{projectId}/members")
    ResponseEntity<Void> addMemberToProject(@PathVariable("projectId") long projectId,
                                            @RequestBody long userId);

    @GetMapping("/projects/{projectId}/members")
    ResponseEntity<Page<User>> getProjectMembers(@PathVariable("projectId") Long projectId,
                                                 @RequestParam("page") int page,
                                                 @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/members/{memberId}")
    ResponseEntity<Void> removeMemberFromProject(@PathVariable("projectId") long projectId,
                                                 @PathVariable("memberId") long memberId);


    // Task
    @GetMapping("/projects/{projectId}/tasks")
    ResponseEntity<Page<Task>> getAllTasks(@PathVariable("projectId") Long projectId,
                                           @RequestParam("page") int page,
                                           @RequestParam("size") int size,
                                           @RequestParam(required = false) String sort);

    @GetMapping("/projects/{projectId}/tasks/{taskId}")
    ResponseEntity<Task> getTaskByProjectId(@PathVariable("projectId") Long projectId,
                                            @PathVariable("taskId") Long taskId);

    @PostMapping("/projects/{projectId}/tasks")
    ResponseEntity<Task> createTask(@PathVariable("projectId") Long projectId,
                                    @RequestBody Task request);

    @PutMapping("/projects/{projectId}/tasks/{taskId}")
    ResponseEntity<Task> updateTask(@PathVariable("projectId") Long projectId,
                                    @PathVariable("taskId") Long taskId,
                                    @RequestBody Task request);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    ResponseEntity<Void> deleteTask(@PathVariable("projectId") Long projectId,
                                    @PathVariable("taskId") Long taskId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/comments")
    ResponseEntity<Comment> createComment(@PathVariable("projectId") Long projectId,
                                          @PathVariable("taskId") Long taskId,
                                          @RequestBody Comment request);

    @GetMapping("/projects/{projectId}/tasks/{taskId}/comments")
    ResponseEntity<Page<Comment>> getTaskComments(@PathVariable("projectId") Long projectId,
                                                  @PathVariable("taskId") Long taskId,
                                                  @RequestParam("page") int page,
                                                  @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}/comments/{commentId}")
    ResponseEntity<Void> deleteComment(@PathVariable("projectId") Long projectId,
                                       @PathVariable("taskId") Long taskId,
                                       @PathVariable("commentId") String commentId);

    @GetMapping("/projects/{projectId}/tags")
    ResponseEntity<Page<Tag>> getProjectTags(@PathVariable("projectId") Long projectId,
                                             @RequestParam("page") int page,
                                             @RequestParam("size") int size);

    @DeleteMapping("/projects/{projectId}/tags/{tagId}")
    ResponseEntity<Void> deleteTag(@PathVariable("projectId") Long projectId,
                                   @PathVariable("tagId") String tagId);

    @GetMapping("/projects/{projectId}/milestones")
    ResponseEntity<Page<Milestone>> getProjectMilestones(@PathVariable("projectId") Long projectId,
                                                         @RequestParam("page") int page,
                                                         @RequestParam("size") int size,
                                                         @RequestParam(required = false) String sort);

    @DeleteMapping("/projects/{projectId}/milestones/{milestoneId}")
    ResponseEntity<Void> deleteMilestone(@PathVariable("projectId") Long projectId,
                                         @PathVariable("milestoneId") String milestoneId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/tags/{tagId}")
    ResponseEntity<Void> addTagToTask(@PathVariable("projectId") Long projectId,
                                      @PathVariable("taskId") String taskId,
                                      @PathVariable("tagId") String tagId);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}/tags/{tagId}")
    ResponseEntity<Void> removeTagFromTask(@PathVariable("projectId") Long projectId,
                                           @PathVariable("taskId") String taskId,
                                           @PathVariable("tagId") String tagId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/milestones/{milestoneId}")
    ResponseEntity<Void> addMilestoneToTask(@PathVariable("projectId") Long projectId,
                                            @PathVariable("taskId") String taskId,
                                            @PathVariable("milestoneId") String milestoneId);

    @DeleteMapping("/projects/{projectId}/tasks/{taskId}/milestones/{milestoneId}")
    ResponseEntity<Void> removeMilestoneFromTask(@PathVariable("projectId") Long projectId,
                                                 @PathVariable("taskId") String taskId,
                                                 @PathVariable("milestoneId") String milestoneId);



    // ProjectMember
    @GetMapping("/project-members/roles")
    List<String> getUserRolesInProject(@RequestParam("projectId") Long projectId,
                                       @RequestParam("userId") Long userId);

    @GetMapping("/project-members/users")
    List<Long> getUserIdsInProject(@RequestParam("projectId") Long projectId);


    // 마일스톤
    // Milestone for Project
    @PostMapping("/projects/{projectId}/milestones")
    ResponseEntity<Milestone> createMilestone(@PathVariable("projectId") Long projectId,
                                              @RequestBody Milestone request);

    @GetMapping("/projects/{projectId}/milestones")
    ResponseEntity<Page<Milestone>> getProjectMilestones(@PathVariable("projectId") Long projectId,
                                                         @RequestParam(required = false) String sort);

    @GetMapping("/projects/{projectId}/milestones/{milestoneId}")
    Milestone getMilestoneById(@PathVariable("projectId") Long projectId,
                               @PathVariable("milestoneId") Long milestoneId);

    @PutMapping("/projects/{projectId}/milestones/{milestoneId}")
    void updateMilestone(@PathVariable("projectId") Long projectId,
                         @PathVariable("milestoneId") Long milestoneId,
                         @RequestBody Milestone milestone);

    @DeleteMapping("/projects/{projectId}/milestones/{milestoneId}")
    void deleteMilestone(@PathVariable("projectId") Long projectId,
                         @PathVariable("milestoneId") Long milestoneId);

    // Milestone for Task
    @PostMapping("/projects/{projectId}/tasks/{taskId}/milestones")
    void setTaskMilestone(@PathVariable("projectId") Long projectId,
                          @PathVariable("taskId") Long taskId,
                          @RequestBody Long milestoneId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/milestones")
    void deleteTaskMilestone(@PathVariable("projectId") Long projectId,
                             @PathVariable("taskId") Long taskId);


    // 태그
    // Tag for Project
    @PostMapping("/projects/{projectId}/tags")
    ResponseEntity<Tag> createTag(@PathVariable("projectId") Long projectId,
                                  @RequestBody Tag request);

    @GetMapping("/projects/{projectId}/tags")
    Page<Tag> getTagsByProjectId(@PathVariable("projectId") Long projectId, Pageable pageable);

    @GetMapping("/projects/{projectId}/tags/{tagId}")
    Tag getTagById(@PathVariable("projectId") Long projectId,
                   @PathVariable("tagId") Long tagId);

    @PutMapping("/projects/{projectId}/tags/{tagId}")
    void updateTag(@PathVariable("projectId") Long projectId,
                   @PathVariable("tagId") Long tagId,
                   @RequestBody Tag tag);

    @DeleteMapping("/projects/{projectId}/tags/{tagId}")
    void deleteTag(@PathVariable("projectId") Long projectId,
                   @PathVariable("tagId") Long tagId);


    // Tag for Task
    @PostMapping("/projects/{projectId}/tasks/{taskId}/tags")
    void setTaskTag(@PathVariable("projectId") Long projectId,
                    @PathVariable("taskId") Long taskId,
                    @RequestBody Long tagId);

    @PostMapping("/projects/{projectId}/tasks/{taskId}/tags")
    void deleteTaskTag(@PathVariable("projectId") Long projectId,
                       @PathVariable("taskId") Long taskId);


    // ProjectMember
    // Member for Project
    @PostMapping("/projects/{projectId}/members")
    ResponseEntity<Milestone> createProjectMember(@PathVariable("projectId") Long projectId,
                                                  @RequestBody List<Long> memberIds);


    @DeleteMapping("/projects/{projectId}/members/{memberId}")
    void deleteProjectMember(@PathVariable("projectId") Long projectId,
                             @PathVariable("memberId") String memberId);


}