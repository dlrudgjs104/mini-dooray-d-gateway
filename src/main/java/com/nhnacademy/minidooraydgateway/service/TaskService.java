package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final ProjectServiceClient projectServiceClient;

    public Task getTasksByProjectId(Long projectId, Long taskId) {
        ResponseEntity<Task> response = projectServiceClient.getTaskByProjectId(projectId, taskId);
        return response.getBody();
    }

    public void createTask(Long projectId, Task task) {
        projectServiceClient.createTask(projectId, task);
    }

    public void updateTask(Long projectId, Long taskId, Task task) {
        projectServiceClient.updateTask(projectId, taskId, task);
    }

    public void deleteTask(Long projectId, Long taskId) {
        projectServiceClient.deleteTask(projectId, taskId);
    }

    // 태그 설정
    public void setTaskTag(Long projectId, Long taskId, Long tagId) {
        projectServiceClient.setTaskTag(projectId, taskId, tagId);
    }

    public void deleteTaskTag(Long projectId, Long taskId) {
        projectServiceClient.deleteTaskTag(projectId, taskId);
    }


    // 마일스톤 설정
    public void setTaskMilestone(Long projectId, Long taskId, Long milestoneId) {
        projectServiceClient.setTaskMilestone(projectId, taskId, milestoneId);
    }

    public void deleteTaskMilestone(Long projectId, Long taskId) {
        projectServiceClient.deleteTaskMilestone(projectId, taskId);
    }
}
