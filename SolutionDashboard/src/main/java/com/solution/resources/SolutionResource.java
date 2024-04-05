package com.solution.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.solution.model.LoginRequest;
import com.solution.service.SolutionService;

@RestController
@RequestMapping("/solution/solution")
public class SolutionResource {
@Autowired
SolutionService solutionService;
@PostMapping("/approvecustomer")
public ResponseEntity<?> approveCustomer(@RequestParam String customerId) {

	return new ResponseEntity<String>(this.solutionService.approveCustomer(customerId), HttpStatus.OK);
}

@PostMapping("/rejectcustomer")
public ResponseEntity<?> rejectCustomer(@RequestParam String customerId) {

	return new ResponseEntity<String>(this.solutionService.rejectCustomer(customerId), HttpStatus.OK);
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	return this.solutionService.loginAuthentication(request.getSearchInput(), request.getPassword());
}

}
