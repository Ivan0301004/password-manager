package com.ivan.passwordmanager.repository;

import com.ivan.passwordmanager.model.Site;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SiteRepository extends JpaRepository<Site, Long> {

    @Query("select c from Site c where c.category= :category")
    Optional<List<Site>> findSitesByNameCategory(@Param("category") String category);

    @Query("select c from Site c where c.email = :email")
    Optional<List<Site>> findSitesByEmail(@Param("email") String email);

}
