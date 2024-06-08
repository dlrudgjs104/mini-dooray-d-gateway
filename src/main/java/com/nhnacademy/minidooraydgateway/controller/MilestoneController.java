//package com.nhnacademy.minidooraydgateway.controller;
//
//import com.nhnacademy.minidooraydgateway.domain.Milestone;
//import com.nhnacademy.minidooraydgateway.service.MilestoneService;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import lombok.RequiredArgsConstructor;
//
//import java.util.List;
//
//
//
//@Controller
//@RequestMapping("/projects/{projectId}/milestones")
//@RequiredArgsConstructor
//public class MilestoneController {
//
//    private final MilestoneService milestoneService;
//
//    // Milestone 목록 페이지
//    @GetMapping
//    public String getMilestonesPage(@PathVariable Long projectId,
//                                    @RequestParam(required = false) String sort,
//                                    Model model) {
////        Page<Milestone> milestones = milestoneService.getMilestonesByProjectId(projectId, sort);
//        model.addAttribute("milestones", milestones);
//        model.addAttribute("projectId", projectId);
//        return "milestone/milestoneList";
//    }
//
//    // Milestone 생성 페이지
//    @GetMapping("/new")
//    public String getNewMilestonePage(@PathVariable Long projectId, Model model) {
//        model.addAttribute("milestone", new Milestone());
//        model.addAttribute("projectId", projectId);
//        return "milestone/newMilestone";
//    }
//
//    // Milestone 생성 처리
//    @PostMapping
//    public String handleCreateMilestone(@PathVariable Long projectId, @ModelAttribute Milestone milestone) {
//        milestoneService.createMilestone(projectId, milestone);
//        return "redirect:/projects/" + projectId + "/milestones";
//    }
//
//    // Milestone 수정 페이지
//    @GetMapping("/{milestoneId}/edit")
//    public String getEditMilestonePage(@PathVariable Long projectId, @PathVariable Long milestoneId, Model model) {
//        Milestone milestone = milestoneService.getMilestoneById(projectId, milestoneId);
//        model.addAttribute("milestone", milestone);
//        model.addAttribute("projectId", projectId);
//        return "milestone/editMilestone";
//    }
//
//    // Milestone 수정 처리
//    @PostMapping("/{milestoneId}/edit")
//    public String handleEditMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId, @ModelAttribute Milestone milestone) {
//        milestoneService.updateMilestone(projectId, milestoneId, milestone);
//        return "redirect:/projects/" + projectId + "/milestones";
//    }
//
//    // Milestone 삭제 처리
//    @PostMapping("/{milestoneId}/delete")
//    public String handleDeleteMilestone(@PathVariable Long projectId, @PathVariable Long milestoneId) {
//        milestoneService.deleteMilestone(projectId, milestoneId);
//        return "redirect:/projects/" + projectId + "/milestones";
//    }
//}