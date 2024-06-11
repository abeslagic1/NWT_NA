package com.etf.SystemEvents;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SystemEventsRepository extends JpaRepository<SystemEvents, Integer>{
    
}
