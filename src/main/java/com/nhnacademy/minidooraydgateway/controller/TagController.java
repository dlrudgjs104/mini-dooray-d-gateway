package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.domain.Tag;
import com.nhnacademy.minidooraydgateway.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/projects/{projectId}/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    // 특정 프로젝트의 Tag 목록 페이지
    @GetMapping
    public String getTagsPage(@PathVariable Long projectId, @PageableDefault(size = 10) Pageable pageable, Model model) {
        List<Tag> tags = tagService.getTagsByProjectId(projectId);
        model.addAttribute("tags", tags);
        return "project/tag";
    }

    // Tag 생성 및 관리 페이지
    @GetMapping("/new")
    public String getTagManagementPage(@PathVariable Long projectId, Model model) {
        model.addAttribute("tag", new Tag());
        model.addAttribute("projectId", projectId);
        return "project/newTag";
    }

    // Tag 생성 처리
    @PostMapping
    public String handleCreateTag(@PathVariable Long projectId, @ModelAttribute Tag tag) {
        tagService.createTag(projectId, tag);
        return "redirect:/projects/" + projectId + "/tags";
    }

    // Tag 수정 페이지
    @GetMapping("/{tagId}/edit")
    public String getEditTagPage(@PathVariable Long projectId, @PathVariable Long tagId, Model model) {
        Tag tag = tagService.getTagById(projectId, tagId);
        model.addAttribute("tag", tag);
        model.addAttribute("projectId", projectId);
        return "project/tagProperty";
    }

    // Tag 수정 처리
    @PostMapping("/{tagId}/edit")
    public String handleEditTag(@PathVariable Long projectId, @PathVariable Long tagId, @ModelAttribute Tag tag) {
        tagService.updateTag(projectId, tagId, tag);
        return "redirect:/projects/" + projectId + "/tags";
    }

    // Tag 삭제 처리
    @PostMapping("/{tagId}/delete")
    public String handleDeleteTag(@PathVariable Long projectId, @PathVariable Long tagId) {
        tagService.deleteTag(projectId, tagId);
        return "redirect:/projects/" + projectId + "/tags/management";
    }
}
