package net.fullstack7.mooc.service;

import net.fullstack7.mooc.dto.AdminLoginDTO;

public interface AdminServiceIf {
    String login(AdminLoginDTO adminLoginDTO);
}
