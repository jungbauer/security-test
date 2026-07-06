package com.jungbauer.securitytest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }

    @GetMapping("/test2")
    @PreAuthorize("isAuthenticated()")
    public String test2Page() {
        return "test2";
    }

    @GetMapping("/user")
    @PreAuthorize("isAuthenticated()")
    public String userPage() {
        return "user";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_OWNER')")
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/owner")
    @PreAuthorize("hasRole('OWNER')")
    public String ownerPage() {
        return "owner";
    }
}
