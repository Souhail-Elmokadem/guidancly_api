package com.guidancly.guidancly_api.stop.dao.repositories;

import com.guidancly.guidancly_api.stop.dao.entities.Stop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StopRepository extends JpaRepository<Stop,Long> {
}
