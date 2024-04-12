package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.SiteDto;
import com.ivan.passwordmanager.model.Site;
import com.ivan.passwordmanager.service.SiteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/users/group/{groupId}/site")
    public ResponseEntity<SiteDto> createSiteToGroupById(@RequestBody Site site, @PathVariable long groupId) {
        return ResponseEntity.ok(this.siteService.createSiteToGroupById(site, groupId));
    }

    @GetMapping("/users/sites")
    public ResponseEntity<Page<SiteDto>> getAllSites(Pageable pageable) {
        return ResponseEntity.ok(this.siteService.getAllSites(pageable));
    }



}
