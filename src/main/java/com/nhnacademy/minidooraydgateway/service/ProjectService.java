package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Project;
import com.nhnacademy.minidooraydgateway.dto.ProjectCreateDto;
import com.nhnacademy.minidooraydgateway.dto.ProjectGetByUserIdRequestDto;
import com.nhnacademy.minidooraydgateway.dto.ProjectGetDto;
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

//    public Page<Project> getAllProjectsByUserId(Pageable pageable, Long userId) {
//        ResponseEntity<Page<Project>> response = projectServiceClient.getAllProjectsByUserId(
//                userId,
//                pageable.getPageNumber(),
//                pageable.getPageSize(),
//                PaginationUtil.createSortParam(pageable)
//        );
//        return response.getBody();
//    }

    public ProjectGetDto getProjectById(Long projectId) {
        ResponseEntity<ProjectGetDto> response = projectServiceClient.getProjectById(projectId);
        return response.getBody();
    }

    public void createProject(ProjectCreateDto projectCreateDto) {
        projectServiceClient.createProject(projectCreateDto);
    }


    public Page<ProjectGetDto> getAllProjectsByUserId(Pageable pageable, long userId) {
        ResponseEntity<Page<ProjectGetDto>> response = projectServiceClient.getAllProjectsByUserId(ProjectGetByUserIdRequestDto.builder().userId(userId).build(),
                pageable.getPageNumber(),
                pageable.getPageSize(),
                PaginationUtil.createSortParam(pageable));

        return response.getBody();
    }
}
