package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.service.ProjectMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/projects/{projectId}/members")
public class ProjectMemberController {

    private final ProjectMemberService projectMemberService;

    // 프로젝트 멤버 추가 처리 - 리스트로 해야함
    @PostMapping
    public String handleAddProjectMember(@PathVariable Long projectId, @RequestParam Long memberId) {
        projectMemberService.addMemberToProject(projectId, memberId);
        return "redirect:/projects/{id}/members";
    }

    // 프로젝트 멤버 제거 처리
    @DeleteMapping("/{memberId}")
    public String handleRemoveProjectMember(@PathVariable Long projectId, @PathVariable Long memberId) {
        // ProjectApi 호출하여 멤버 제거
        projectMemberService.removeMemberFromProject(projectId, memberId);
        return "redirect:/projects/{id}/members";
    }
}
