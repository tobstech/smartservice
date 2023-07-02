package org.smartix.smartservice.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.smartix.smartservice.entity.SmartService;

import java.util.List;

@ApplicationScoped
public class SmartServiceRepository implements PanacheRepository<SmartService> {
    public List<SmartService> findBySearch(String formedQuery) {
        return list(formedQuery);
    }
}
