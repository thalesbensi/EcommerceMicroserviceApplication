package com.thalesbensi.auth_service.api.controllers.dtos;

import com.thalesbensi.auth_service.domain.models.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
