package com.ivan.passwordmanager.service.impl;

import com.ivan.passwordmanager.dto.SiteDto;
import com.ivan.passwordmanager.exeptions.NotFound;
import com.ivan.passwordmanager.mappers.SiteMapper;
import com.ivan.passwordmanager.model.Site;
import com.ivan.passwordmanager.model.User;
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
    private final UserRepository userRepository;
    private final SiteMapper siteMapper;

    public SiteServiceImpl(SiteRepository siteRepository, UserRepository userRepository, SiteMapper siteMapper) {
        this.siteRepository = siteRepository;
        this.userRepository = userRepository;
        this.siteMapper = siteMapper;
    }

    @Override
    @Transactional
    public SiteDto createSiteToUserById(Site site, Long userId) {
        User userToAddSite = this.userRepository.findById(userId)
                .orElseThrow(() -> new NotFound("No user found", HttpStatus.NOT_FOUND));

        this.siteRepository.save(site);
        site.setUser(userToAddSite);
        userToAddSite.getSitesList().add(site);
        this.userRepository.save(userToAddSite);

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
    public SiteDto removeSiteById(Long id) {
        Site siteToRemove = this.siteRepository.findById(id)
                .orElseThrow(() -> new NotFound("Site with id " + id + " was not found!", HttpStatus.NOT_FOUND));

        SiteDto siteDto = this.siteMapper.toSiteDto(siteToRemove);
        this.siteRepository.delete(siteToRemove);

        return siteDto;
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
