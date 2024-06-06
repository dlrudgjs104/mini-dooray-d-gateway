package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.domain.Comment;
import com.nhnacademy.minidooraydgateway.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/projects/{projectId}/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // Comment 생성 페이지
    @GetMapping("/new")
    public String getNewCommentPage(@PathVariable Long projectId, @PathVariable Long taskId, Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("projectId", projectId);
        model.addAttribute("taskId", taskId);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    // Comment 생성 처리
    @PostMapping
    public String handleCreateComment(@PathVariable Long projectId, @PathVariable Long taskId, @ModelAttribute Comment comment) {
        commentService.createComment(taskId, comment);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    // Comment 수정 처리
    @PostMapping("/{commentId}/edit")
    public String handleEditComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId, @ModelAttribute Comment comment) {
//        commentService.updateComment(commentId, comment);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }

    // Comment 삭제 처리
    @PostMapping("/{commentId}/delete")
    public String handleDeleteComment(@PathVariable Long projectId, @PathVariable Long taskId, @PathVariable Long commentId) {
//        commentService.deleteComment(commentId);
        return "redirect:/projects/" + projectId + "/tasks/" + taskId;
    }
}