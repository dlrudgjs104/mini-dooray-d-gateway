package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Project;
import com.nhnacademy.minidooraydgateway.dto.ProjectDto;
import com.nhnacademy.minidooraydgateway.util.PaginationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectServiceClient projectServiceClient;

    public Page<Project> getAllProjectsByUserId(Pageable pageable, Long userId) {
        ResponseEntity<Page<Project>> response = projectServiceClient.getAllProjectsByUserId(
                userId,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                PaginationUtil.createSortParam(pageable)
        );
        return response.getBody();
    }

    public ProjectDto getProjectById(Long projectId) {
        ResponseEntity<ProjectDto> response = projectServiceClient.getProjectById(projectId);
        return response.getBody();
    }

    public void createProject(ProjectDto projectDto) {
        projectServiceClient.createProject(projectDto);
    }


    public Page<Project> getAllProjectsByUserId(long userId, Pageable pageable) {
        ResponseEntity<Page<Project>> response = projectServiceClient.getAllProjectsByUserId(userId,
                pageable.getPageNumber(),
                pageable.getPageSize(),
                PaginationUtil.createSortParam(pageable));

        return null;
    }
}
