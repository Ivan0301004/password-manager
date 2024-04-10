package com.ivan.passwordmanager.controllers;

import com.ivan.passwordmanager.service.SiteService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteController {

    private final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;


    }
}
