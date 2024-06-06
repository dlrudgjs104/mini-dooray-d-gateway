package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ProjectServiceClient projectServiceClient;

    public Page<Tag> getTagsByProjectId(Long projectId, Pageable pageable) {
        return projectServiceClient.getTagsByProjectId(projectId, pageable);
    }

    public Tag getTagById(Long projectId, Long tagId) {
        return projectServiceClient.getTagById(projectId, tagId);
    }

    public void createTag(Long projectId, Tag tag) {
        tag.setProjectId(projectId);
        projectServiceClient.createTag(projectId, tag);
    }

    public void updateTag(Long projectId, Long tagId, Tag tag) {
        projectServiceClient.updateTag(projectId, tagId, tag);
    }

    public void deleteTag(Long projectId, Long tagId) {
        projectServiceClient.deleteTag(projectId, tagId);
    }
}