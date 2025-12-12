package com.thalesbensi.auth_service.domain.repositories;

import com.thalesbensi.auth_service.domain.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}
