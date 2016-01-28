package com.codeaim.urlcheck.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codeaim.urlcheck.api.model.MonitorEvent;

@Repository
public interface MonitorEventRepository extends JpaRepository<MonitorEvent, Long>
{
}

