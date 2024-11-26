package net.fullstack7.mooc.repository;

import net.fullstack7.mooc.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findByAdminIdAndPassword(String adminId, String password);
}
