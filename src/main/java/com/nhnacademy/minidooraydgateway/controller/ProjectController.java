package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.domain.Project;
import com.nhnacademy.minidooraydgateway.dto.ProjectDto;
import com.nhnacademy.minidooraydgateway.dto.ProjectForm;
import com.nhnacademy.minidooraydgateway.exception.LoginRequiredException;
import com.nhnacademy.minidooraydgateway.service.ProjectService;
import com.nhnacademy.minidooraydgateway.service.UserService;
import com.nhnacademy.minidooraydgateway.util.SecurityContextUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;
    private final SecurityContextUtil securityContextUtil;

    // 프로젝트 목록 페이지
    @GetMapping
    public String getProjectsPage(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Optional<Long> userIdOpt = securityContextUtil.getCurrentUserId();
        if (userIdOpt.isEmpty()) {
            throw new LoginRequiredException();
        }

        long userId = userIdOpt.get();
        Page<Project> projects = projectService.getAllProjectsByUserId(userId, pageable);
        model.addAttribute("projects", projects);
        return "project/projectList";
    }

    // 프로젝트 생성 페이지
    @GetMapping("/new")
    public String getNewProjectPage(Model model) {
        model.addAttribute("project", new ProjectForm("", null, "", null));
        return "project/newProject";
    }

    // 프로젝트 생성 처리
    @PostMapping
    public String handleCreateProject(@RequestBody ProjectForm projectForm) {
        Optional<Long> userIdOpt = securityContextUtil.getCurrentUserId();
        if (userIdOpt.isEmpty()) {
            throw new LoginRequiredException();
        }

        Long userId = userIdOpt.get();
        boolean isProjectAdmin = securityContextUtil.hasAuthority("PROJECT_ADMIN");
        if (!isProjectAdmin) {
            userService.updateUserRole(Collections.singletonList(userId), "PROJECT_ADMIN");
        }

        List<Long> projectMemberIds = userService.getUserIdsByEmails(projectForm.memberEmails());
        if (projectMemberIds != null && !projectMemberIds.isEmpty()) {
            userService.updateUserRole(projectMemberIds, "PROJECT_MEMBER");
        }

        ProjectDto projectDto = new ProjectDto(projectForm.name(), projectForm.status(), userId, projectMemberIds);
        projectService.createProject(projectDto);

        return "redirect:/projects";
    }
}
