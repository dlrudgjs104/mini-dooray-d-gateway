//package com.nhnacademy.minidooraydgateway.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.nhnacademy.minidooraydgateway.domain.Project;
//import com.nhnacademy.minidooraydgateway.domain.User;
//import com.nhnacademy.minidooraydgateway.dto.ProjectCreateRequest;
//import com.nhnacademy.minidooraydgateway.service.ProjectService;
//import com.nhnacademy.minidooraydgateway.service.UserService;
//import com.nhnacademy.minidooraydgateway.util.SecurityContextUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.ArgumentMatchers.*;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
//
//@ExtendWith(SpringExtension.class)
//@WebMvcTest(ProjectController.class)
//public class ProjectControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private ProjectService projectService;
//
//    @MockBean
//    private UserService userService;
//
//    @MockBean
//    private SecurityContextUtil securityContextUtil;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private User mockUser;
//    private Project mockProject;
//
//    @BeforeEach
//    public void setUp() {
//        mockUser = new User();
//        mockUser.setId(1L);
//        mockUser.setEmail("test@example.com");
//        mockUser.setRole(User.Role.MEMBER);
//
//        mockProject = new Project();
//        mockProject.setId(1L);
//        mockProject.setName("Test Project");
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com", roles = {"MEMBER"})
//    public void testGetProjectsPage_withValidUser() throws Exception {
//        when(securityContextUtil.getCurrentUserId()).thenReturn(Optional.of(1L));
//
//        List<Project> projectList = List.of(mockProject);
//        Page<Project> projectPage = new PageImpl<>(projectList);
//        when(projectService.getAllProjectsByUserId(anyLong(), any(Pageable.class))).thenReturn(projectPage);
//
//        mockMvc.perform(get("/projects")
//                        .param("page", "0")
//                        .param("size", "10"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("projects"))
//                .andExpect(view().name("project/projectList"));
//    }
//
//    @Test
//    public void testGetProjectsPage_withInvalidUser() throws Exception {
//        when(securityContextUtil.getCurrentUserId()).thenReturn(Optional.empty());
//
//        mockMvc.perform(get("/projects")
//                        .param("page", "0")
//                        .param("size", "10"))
//                .andExpect(status().is3xxRedirection());
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com", roles = {"MEMBER"})
//    public void testGetNewProjectPage() throws Exception {
//        mockMvc.perform(get("/projects/new"))
//                .andExpect(status().isOk())
//                .andExpect(model().attributeExists("project"))
//                .andExpect(view().name("project/newProject"));
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com", roles = {"MEMBER"})
//    public void testHandleCreateProject_withValidUser() throws Exception {
//        when(securityContextUtil.getCurrentUserId()).thenReturn(Optional.of(1L));
//        when(securityContextUtil.hasAuthority(anyString())).thenReturn(true);
//        when(userService.getUserIdsByEmails(anyList())).thenReturn(List.of(2L, 3L));
//
//        ProjectCreateRequest request = ProjectCreateRequest.builder()
//                .name("New Project")
//                .status(Project.Status.TODO)
//                .memberEmails(new LinkedList<>())
//                .build();
//
//        mockMvc.perform(post("/projects")
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/projects"));
//    }
//
//    @Test
//    public void testHandleCreateProject_withInvalidUser() throws Exception {
//        when(securityContextUtil.getCurrentUserId()).thenReturn(Optional.empty());
//
//        ProjectCreateRequest request = ProjectCreateRequest.builder()
//                .name("New Project")
//                .status(Project.Status.TODO)
//                .memberEmails(new LinkedList<>())
//                .build();
//
//        mockMvc.perform(post("/projects")
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().is3xxRedirection());
//    }
//
//    @Test
//    @WithMockUser(username = "test@example.com", roles = {"MEMBER"})
//    public void testHandleCreateProject_withNonAdminUser() throws Exception {
//        when(securityContextUtil.getCurrentUserId()).thenReturn(Optional.of(1L));
//        when(securityContextUtil.hasAuthority(User.Role.PROJECT_ADMIN.name())).thenReturn(false);  // Mock non-admin authority
//        when(securityContextUtil.getCurrentUserEmail()).thenReturn(Optional.of("test@example.com")); // Mock user email
//
//        doNothing().when(userService).updateUserRole(anyList(), eq("PROJECT_ADMIN"));
//        doNothing().when(userService).updateUserRole(anyList(), eq("PROJECT_MEMBER"));
//
//        ProjectCreateRequest request = ProjectCreateRequest.builder()
//                .name("New Project")
//                .status(Project.Status.TODO)
//                .memberEmails(new LinkedList<>())
//                .build();
//
//        mockMvc.perform(post("/projects")
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(request)))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(redirectedUrl("/projects"));
//
//        // Verify that updateUserRole was called with the expected arguments
//        verify(userService).updateUserRole(anyList(), eq("PROJECT_ADMIN"));
//        verify(userService).updateUserRole(anyList(), eq("PROJECT_MEMBER"));
//    }
//
//
//}
