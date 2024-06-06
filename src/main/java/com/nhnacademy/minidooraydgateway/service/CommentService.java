package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ProjectServiceClient projectServiceClient;


    public void createComment(Long projectId, Long taskId, Comment comment) {
        projectServiceClient.createComment(projectId, taskId, comment);
    }

    public Page<Comment> getTaskComments(Long projectId, Long taskId, Pageable pageable) {
        ResponseEntity<Page<Comment>> response = projectServiceClient.getTaskComments(projectId, taskId, pageable.getPageNumber(), pageable.getPageSize());
        return response.getBody();
    }

}
