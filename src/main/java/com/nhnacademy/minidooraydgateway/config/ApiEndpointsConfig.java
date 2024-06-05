package com.nhnacademy.minidooraydgateway.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApiEndpointsConfig {

    @Value("${api.account-service.base-url}")
    private String accountServiceBaseUrl;

    @Value("${api.project-service.base-url}")
    private String projectServiceBaseUrl;

    public static final String USERS_ENDPOINT = "/users";
    public static final String USER_BY_ID_ENDPOINT = "/users/{userId}";
    public static final String PROJECTS_ENDPOINT = "/projects";
    public static final String PROJECT_BY_ID_ENDPOINT = "/projects/{projectId}";
    public static final String PROJECT_MEMBERS_ENDPOINT = "/projects/{projectId}/members";
    public static final String PROJECT_MEMBER_BY_ID_ENDPOINT = "/projects/{projectId}/members/{memberId}";
    public static final String TASKS_ENDPOINT = "/projects/{projectId}/tasks";
    public static final String TASK_BY_ID_ENDPOINT = "/projects/{projectId}/tasks/{taskId}";
    public static final String COMMENTS_ENDPOINT = "/projects/{projectId}/tasks/{taskId}/comments";
    public static final String COMMENT_BY_ID_ENDPOINT = "/projects/{projectId}/tasks/{taskId}/comments/{commentId}";
    public static final String TAGS_ENDPOINT = "/projects/{projectId}/tags";
    public static final String TAG_BY_ID_ENDPOINT = "/projects/{projectId}/tags/{tagId}";
    public static final String MILESTONES_ENDPOINT = "/projects/{projectId}/milestones";
    public static final String MILESTONE_BY_ID_ENDPOINT = "/projects/{projectId}/milestones/{milestoneId}";
    public static final String TASK_TAGS_ENDPOINT = "/projects/{projectId}/tasks/{taskId}/tags/{tagId}";
    public static final String TASK_MILESTONE_ENDPOINT = "/projects/{projectId}/tasks/{taskId}/milestones/{milestoneId}";

    @PostConstruct
    public void validateConfig() {
        if (accountServiceBaseUrl == null || accountServiceBaseUrl.isEmpty()) {
            throw new IllegalArgumentException("Account Service Base URL is not configured");
        }
        if (projectServiceBaseUrl == null || projectServiceBaseUrl.isEmpty()) {
            throw new IllegalArgumentException("Project Service Base URL is not configured");
        }
    }

    // Account service endpoints
    public String getAccountServiceUsersEndpoint() {
        return accountServiceBaseUrl + USERS_ENDPOINT;
    }

    public String getAccountServiceUserByIdEndpoint(String userId) {
        return accountServiceBaseUrl + USER_BY_ID_ENDPOINT.replace("{userId}", userId);
    }

    // Project service endpoints
    public String getProjectServiceProjectsEndpoint() {
        return projectServiceBaseUrl + PROJECTS_ENDPOINT;
    }

    public String getProjectServiceProjectByIdEndpoint(String projectId) {
        return projectServiceBaseUrl + PROJECT_BY_ID_ENDPOINT.replace("{projectId}", projectId);
    }

    public String getProjectServiceProjectMembersEndpoint(String projectId) {
        return projectServiceBaseUrl + PROJECT_MEMBERS_ENDPOINT.replace("{projectId}", projectId);
    }

    public String getProjectServiceProjectMemberByIdEndpoint(String projectId, String memberId) {
        return projectServiceBaseUrl + PROJECT_MEMBER_BY_ID_ENDPOINT.replace("{projectId}", projectId).replace("{memberId}", memberId);
    }

    public String getProjectServiceTasksEndpoint(String projectId) {
        return projectServiceBaseUrl + TASKS_ENDPOINT.replace("{projectId}", projectId);
    }

    public String getProjectServiceTaskByIdEndpoint(String projectId, String taskId) {
        return projectServiceBaseUrl + TASK_BY_ID_ENDPOINT.replace("{projectId}", projectId).replace("{taskId}", taskId);
    }

    public String getProjectServiceCommentsEndpoint(String projectId, String taskId) {
        return projectServiceBaseUrl + COMMENTS_ENDPOINT.replace("{projectId}", projectId).replace("{taskId}", taskId);
    }

    public String getProjectServiceCommentByIdEndpoint(String projectId, String taskId, String commentId) {
        return projectServiceBaseUrl + COMMENT_BY_ID_ENDPOINT.replace("{projectId}", projectId).replace("{taskId}", taskId).replace("{commentId}", commentId);
    }

    public String getProjectServiceTagsEndpoint(String projectId) {
        return projectServiceBaseUrl + TAGS_ENDPOINT.replace("{projectId}", projectId);
    }

    public String getProjectServiceTagByIdEndpoint(String projectId, String tagId) {
        return projectServiceBaseUrl + TAG_BY_ID_ENDPOINT.replace("{projectId}", projectId).replace("{tagId}", tagId);
    }

    public String getProjectServiceMilestonesEndpoint(String projectId) {
        return projectServiceBaseUrl + MILESTONES_ENDPOINT.replace("{projectId}", projectId);
    }

    public String getProjectServiceMilestoneByIdEndpoint(String projectId, String milestoneId) {
        return projectServiceBaseUrl + MILESTONE_BY_ID_ENDPOINT.replace("{projectId}", projectId).replace("{milestoneId}", milestoneId);
    }

    public String getProjectServiceTaskTagsEndpoint(String projectId, String taskId, String tagId) {
        return projectServiceBaseUrl + TASK_TAGS_ENDPOINT.replace("{projectId}", projectId).replace("{taskId}", taskId).replace("{tagId}", tagId);
    }

    public String getProjectServiceTaskMilestoneEndpoint(String projectId, String taskId, String milestoneId) {
        return projectServiceBaseUrl + TASK_MILESTONE_ENDPOINT.replace("{projectId}", projectId).replace("{taskId}", taskId).replace("{milestoneId}", milestoneId);
    }
}