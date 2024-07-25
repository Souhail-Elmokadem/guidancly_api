package com.guidancly.guidancly_api.location.dao.repositories;

import com.guidancly.guidancly_api.location.dao.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
