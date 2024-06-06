package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectServiceClient projectServiceClient;

    public void addMemberToProject(Long projectId, Long userId) {
        projectServiceClient.addMemberToProject(projectId, userId);
    }

    public void removeMemberFromProject(Long projectId, Long memberId) {
        projectServiceClient.removeMemberFromProject(projectId, memberId);
    }

}
