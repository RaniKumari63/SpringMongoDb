package com.solution.service;

import org.springframework.http.ResponseEntity;

public interface SolutionService {
	public String approveCustomer(String customerId);
    public String rejectCustomer(String customerId);
	public ResponseEntity<?> loginAuthentication(String searchInput, String password);
}
