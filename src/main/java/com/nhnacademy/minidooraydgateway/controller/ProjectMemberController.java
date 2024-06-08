package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.domain.User;
import com.nhnacademy.minidooraydgateway.service.ProjectMemberService;
import com.nhnacademy.minidooraydgateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;
    private final UserService userService;

    @PostMapping
    public String handleAddProjectMember(@PathVariable Long projectId, @RequestParam List<String> memberEmails) {
        List<Long> ids = userService.getUserIdsByEmails(memberEmails);
        projectMemberService.addMemberToProject(projectId, ids);
        userService.updateUserRole(memberEmails, User.Role.PROJECT_MEMBER.name());
        return "redirect:/projects/{id}/members";
    }
}
