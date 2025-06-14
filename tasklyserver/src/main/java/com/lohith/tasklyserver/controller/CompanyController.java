/**
 * @author Valabhu Lohith Ram
 * created  on 15-Jun-2025
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

import com.lohith.tasklyserver.beans.Company;
import com.lohith.tasklyserver.common.ErrorCode;
import com.lohith.tasklyserver.common.ResponseWrapper;
import com.lohith.tasklyserver.services.CompanyService;

/**
 * 
 */

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping
	@ResponseBody
	public ResponseEntity<ResponseWrapper<?>> createCompany(@RequestBody Company company){
		try {
			Company createdCompany = companyService.createCompany(company);
			logger.info("Creating Company Success");
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(ErrorCode.SUCCESS, createdCompany,HttpStatus.OK));
		}catch(Exception e) {
			logger.error("Creating Company Failed",e);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper<>(ErrorCode.INTERNAL_SERVER_ERROR, "An Error Occured While Creating Company",HttpStatus.OK));
		}
	}
}
