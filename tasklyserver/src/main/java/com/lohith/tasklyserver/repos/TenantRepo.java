package com.lohith.tasklyserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lohith.tasklyserver.beans.Tenant;

public interface TenantRepo extends JpaRepository<Tenant, Long> {
    Tenant findByTenantId(String tenantId);

    Tenant findByCompanyId(Long companyId);
}