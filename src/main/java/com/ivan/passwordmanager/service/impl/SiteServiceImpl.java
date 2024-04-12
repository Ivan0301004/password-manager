package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.SiteDto;
import com.ivan.passwordmanager.exeptions.NotFound;
import com.ivan.passwordmanager.mappers.SiteMapper;
import com.ivan.passwordmanager.model.Group;
import com.ivan.passwordmanager.model.Site;
import com.ivan.passwordmanager.model.User;
import com.ivan.passwordmanager.repository.GroupRepository;
import com.ivan.passwordmanager.repository.SiteRepository;
import com.ivan.passwordmanager.repository.UserRepository;
import com.ivan.passwordmanager.service.SiteService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    private final SiteRepository siteRepository;
    private final SiteMapper siteMapper;
    private final GroupRepository groupRepository;

    public SiteServiceImpl(SiteRepository siteRepository, SiteMapper siteMapper, GroupRepository groupRepository) {
        this.siteRepository = siteRepository;
        this.siteMapper = siteMapper;
        this.groupRepository = groupRepository;
    }


    @Override
    @Transactional
    public SiteDto createSiteToGroupById(Site site, Long groupId) {
        Group groupToAddSite = this.groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFound("No group found", HttpStatus.NOT_FOUND));

        site.setGroup(groupToAddSite);
        groupToAddSite.getSitesList().add(site);
        this.siteRepository.save(site);
        this.groupRepository.save(groupToAddSite);

        return this.siteMapper.toSiteDto(site);
    }

    @Override
    public Page<SiteDto> getAllSites(Pageable pageable) {
        Page<Site> sites = this.siteRepository.findAll(pageable);

        if (sites.isEmpty())
            throw new NotFound("No content", HttpStatus.NO_CONTENT);

        return this.siteMapper.toSitesDto(sites);
    }

    @Override
    public SiteDto removeSiteByIdFromGroup(Long siteId, Long groupId) {
        Site siteToRemove = this.siteRepository.findById(siteId)
                .orElseThrow(() -> new NotFound("Site with siteId " + siteId + " was not found!", HttpStatus.NOT_FOUND));
        Group group = this.groupRepository.findById(groupId)
                .orElseThrow(() -> new NotFound("User was not found", HttpStatus.NOT_FOUND));

        if (group.getSitesList().contains(siteToRemove)) {
            group.getSitesList().remove(siteToRemove);
            siteToRemove.setGroup(null);
            this.siteRepository.delete(siteToRemove);
            this.groupRepository.save(group);
        }

        return this.siteMapper.toSiteDto(siteToRemove);
    }

    @Override
    @Transactional
    public SiteDto updateSiteById(Long id, SiteDto siteDto) {
        Site siteToUpdate = this.siteRepository.findById(id)
                .map(site -> {
                    site.setName(siteDto.getName());
                    site.setEmail(siteDto.getEmail());
                    site.setPassword(site.getPassword());
                    return this.siteRepository.save(site);
                })
                .orElseThrow(() -> new NotFound("No site was found", HttpStatus.NOT_FOUND));
        return this.siteMapper.toSiteDto(siteToUpdate);
    }

    @Override
    public List<SiteDto> getSitesByNameCategory(String category) {
        List<Site> sitesToObtain = this.siteRepository.findSitesByNameCategory(category)
                .orElseThrow(
                        () -> new NotFound("Site with category " + category + " was not found", HttpStatus.NOT_FOUND));

        return this.siteMapper.toSitesDtoList(sitesToObtain);
    }

    @Override
    public List<SiteDto> getSitesByEmail(String email) {
        return this.siteMapper.toSitesDtoList(this.siteRepository.findSitesByEmail(email)
                .orElseThrow(() -> new NotFound("No sites where found", HttpStatus.NO_CONTENT)));
    }
}
