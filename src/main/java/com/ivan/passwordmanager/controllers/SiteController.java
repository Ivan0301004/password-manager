package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.dto.SiteDto;
import com.ivan.passwordmanager.model.Site;
import com.ivan.passwordmanager.service.SiteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @PostMapping("/users/group/{groupId}/site")
    public ResponseEntity<SiteDto> createSiteToGroupById(@RequestBody Site site, @PathVariable long groupId) {
        SiteDto createdSite = this.siteService.createSiteToGroupById(site, groupId);

        HttpHeaders httpHeaders = new HttpHeaders();
        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(site.getId())
                .toUri();

        httpHeaders.setLocation(newUri);
        return new ResponseEntity<>(createdSite, httpHeaders, HttpStatus.CREATED);
    }

    @GetMapping("/users/sites")
    public ResponseEntity<Page<SiteDto>> getAllSites(Pageable pageable) {
        return ResponseEntity.ok(this.siteService.getAllSites(pageable));
    }

    @DeleteMapping("")
    public ResponseEntity<SiteDto> removeSiteFromGroup(@PathVariable long siteId, @PathVariable long groupId) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(this.siteService.removeSiteByIdFromGroup(siteId, groupId));
    }

}
