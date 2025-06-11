/**
 * @author Valabhu Lohith Ram
 * created  on 11-Jun-2025
 */
package com.lohith.tasklyserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lohith.tasklyserver.beans.User;
import com.lohith.tasklyserver.common.ErrorCode;
import com.lohith.tasklyserver.common.ResponseWrapper;
import com.lohith.tasklyserver.services.UserService;

/**
 * Controller Class for User
 */

@RestController
@RequestMapping("/user")
public class UserControler {
	
	private static final Logger logger = LoggerFactory.getLogger(UserControler.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseWrapper<?>> createUser(@RequestBody User user){
		try {
			User createdUser = userService.createUser(user);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(ErrorCode.SUCCESS, createdUser,HttpStatus.OK));
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(ErrorCode.INTERNAL_SERVER_ERROR, "An Error Occured While Creating User",HttpStatus.OK));
		}
	}
}
