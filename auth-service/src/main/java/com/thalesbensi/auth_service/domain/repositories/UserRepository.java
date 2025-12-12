package com.thalesbensi.auth_service.domain.repositories;

import com.thalesbensi.auth_service.domain.models.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserDetails findByLogin(String login);
}
