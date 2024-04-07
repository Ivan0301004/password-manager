package com.ivan.passwordmanager.service;

import com.ivan.passwordmanager.dto.SiteDto;
import com.ivan.passwordmanager.model.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SiteService {

    public SiteDto createSiteToGroupById(Site site, Long groupId);

    Page<SiteDto> getAllSites(Pageable pageable);

    SiteDto removeSiteByIdFromGroup(Long siteId, Long groupId);

    SiteDto updateSiteById(Long id, SiteDto siteDto);

    List<SiteDto> getSitesByNameCategory(String category);

    List<SiteDto> getSitesByEmail(String email);

}
