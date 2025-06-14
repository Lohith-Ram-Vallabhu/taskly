/**
 * @author Valabhu Lohith Ram
 * created  on 14-Jun-2025
 */
package com.lohith.tasklyserver.services;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.lohith.tasklyserver.beans.Company;
import com.lohith.tasklyserver.beans.Tenant;
import com.lohith.tasklyserver.beans.User;
import com.lohith.tasklyserver.repos.CompanyRepo;
import com.lohith.tasklyserver.repos.TenantRepo;
import com.lohith.tasklyserver.repos.UserRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

/**
 * @Service for Company
 */
@Service
public class CompanyService {

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private TenantRepo tenantRepo;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public Company createCompany(Company company) {
		try {
			// Initialize version for new company
			company.setVersion(0L);

			// ✅ Step 1: Save company first → get ID
			company = companyRepo.saveAndFlush(company);

			// ✅ Step 2: Now you have ID, create user
			User newUser = new User();
			newUser.setUserMail(company.getCompanyEmail());
			newUser.setUserPassword(generateStrongPassword(company.getCompanyName()));
			userRepo.saveAndFlush(newUser);

			// ✅ Step 3: Create schema name using company ID
			String schemaName = "tenant_" + company.getId();

			// ✅ Step 4: Create tenant record
			Tenant tenant = new Tenant();
			tenant.setTenantId(UUID.randomUUID().toString());
			tenant.setSchemaName(schemaName);
			tenant.setIsActive(true);
			tenant.setCompanyId(company.getId());
			tenantRepo.saveAndFlush(tenant);

			// ✅ Step 5: Create schema & tables in a new transaction
			createSchemaInNewTransaction(schemaName);

			// Refresh the company entity to ensure it's in sync with the database
			entityManager.refresh(company);

			return company;
		} catch (Exception e) {
			throw new RuntimeException("Failed to create company: " + e.getMessage(), e);
		}
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	private void createSchemaInNewTransaction(String schemaName) {
		try {
			// Create schema if it doesn't exist
			jdbcTemplate.execute("CREATE SCHEMA IF NOT EXISTS " + schemaName);

			// Set the schema for the current session
			jdbcTemplate.execute("SET SCHEMA " + schemaName);

			// Create tables in the new schema using Hibernate
			entityManager.createNativeQuery("SET SCHEMA " + schemaName).executeUpdate();

			// Force Hibernate to create tables in the new schema
			entityManager.getMetamodel().getEntities().forEach(entityType -> {
				String tableName = entityType.getName();
				entityManager.createNativeQuery(
						"CREATE TABLE IF NOT EXISTS " + tableName + " LIKE " +
								entityManager.getMetamodel().getEntities().stream()
										.filter(e -> e.getName().equals(tableName))
										.findFirst()
										.map(e -> e.getJavaType().getSimpleName().toLowerCase())
										.orElse(tableName))
						.executeUpdate();
			});
		} catch (Exception e) {
			throw new RuntimeException("Failed to create schema: " + e.getMessage(), e);
		}
	}

	public static String generateStrongPassword(String companyName) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			sb.append(chars.charAt(random.nextInt(chars.length())));
		}
		return companyName + "_" + sb.toString();
	}
}
