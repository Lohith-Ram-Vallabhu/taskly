/**
 * @author Valabhu Lohith Ram
 * created  on 11-Jun-2025
 */
package com.lohith.tasklyserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lohith.tasklyserver.beans.User;
import com.lohith.tasklyserver.repos.UserRepo;

/**
 * Service for User
 */

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
}
