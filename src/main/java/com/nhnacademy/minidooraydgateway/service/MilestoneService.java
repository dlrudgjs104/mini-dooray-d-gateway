package com.nhnacademy.minidooraydgateway.service;
import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Milestone;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MilestoneService {

    private final ProjectServiceClient projectServiceClient;

    public List<Milestone> getMilestonesByProjectId(Long projectId) {
        return projectServiceClient.getProjectMilestones(projectId).getBody();
    }

    public Milestone getMilestoneById(Long projectId, Long milestoneId) {
        return projectServiceClient.getMilestoneById(projectId, milestoneId);
    }

    public void createMilestone(Long projectId, Milestone milestone) {
        milestone.setProjectId(projectId);
        projectServiceClient.createMilestone(projectId, milestone);
    }

    public void updateMilestone(Long projectId, Long milestoneId, Milestone milestone) {
        projectServiceClient.updateMilestone(projectId, milestoneId, milestone);
    }

    public void deleteMilestone(Long projectId, Long milestoneId) {
        projectServiceClient.deleteMilestone(projectId, milestoneId);
    }

//    public void setTaskMilestone(Long projectId, Long taskId, Long milestoneId) {
//        projectServiceClient.setTaskMilestone(projectId, taskId, milestoneId);
//    }
//
//    public void deleteTaskMilestone(Long projectId, Long taskId) {
//        projectServiceClient.deleteTaskMilestone(projectId, taskId);
//    }

}