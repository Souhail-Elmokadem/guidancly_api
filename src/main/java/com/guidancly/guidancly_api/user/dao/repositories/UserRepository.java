package com.guidancly.guidancly_api.user.dao.repositories;

import com.guidancly.guidancly_api.user.dao.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
