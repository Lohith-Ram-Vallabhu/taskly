/**
 * @author Valabhu Lohith Ram
 * created  on 14-Jun-2025
 */
package com.lohith.tasklyserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lohith.tasklyserver.beans.Company;

/**
 * 
 */
public interface CompanyRepo extends JpaRepository<Company, Long>{

}
