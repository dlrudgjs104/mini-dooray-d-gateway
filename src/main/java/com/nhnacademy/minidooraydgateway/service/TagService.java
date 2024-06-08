package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.ProjectServiceClient;
import com.nhnacademy.minidooraydgateway.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final ProjectServiceClient projectServiceClient;

    public List<Tag> getTagsByProjectId(Long projectId) {
        return projectServiceClient.getTagsByProjectId(projectId);
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