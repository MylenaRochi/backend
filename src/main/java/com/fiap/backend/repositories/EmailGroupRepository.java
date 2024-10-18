package com.fiap.backend.repositories;

import com.fiap.backend.entities.EmailGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RepositoryRestResource
public interface EmailGroupRepository extends JpaRepository<EmailGroup, UUID> {
    List<EmailGroup> findByUserId(UUID userId);
}
