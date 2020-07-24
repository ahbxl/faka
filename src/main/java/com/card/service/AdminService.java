package com.card.service;

import com.card.entity.domain.Admin;

public interface AdminService {
    Admin findByUsernameAndPassword(String username, String password);

    Integer countByUsername(String username);

    Admin findAdminById(Long id);

    Admin findByUsername(String username);
}
