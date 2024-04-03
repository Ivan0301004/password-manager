package com.ivan.passwordmanager.mappers;

import com.ivan.passwordmanager.dto.SiteDto;
import com.ivan.passwordmanager.model.Site;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface SiteMapper {

    SiteDto toSiteDto(Site site);

    Site siteDtoToEntity(SiteDto siteDto);

    List<SiteDto> toSitesDtoList(List<Site> sites);

    default Page<SiteDto> toSitesDto(Page<Site> site) {
        return new PageImpl<>(site.getContent().stream()
                .map(this::toSiteDto)
                .collect(Collectors.toList()), site.getPageable(), site.getTotalElements());
    }

}
