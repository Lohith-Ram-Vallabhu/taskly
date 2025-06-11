/**
 * @author Valabhu Lohith Ram
 * created  on 11-Jun-2025
 */
package com.lohith.tasklyserver.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lohith.tasklyserver.beans.User;

/**
 * Repository For User Table
 */
public interface UserRepo extends JpaRepository<User, Long>{

}
