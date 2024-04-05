package com.placement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.placement.model.LoginRequest;

public interface PlacementService {
public String approveCandidate(String request);

public ResponseEntity<?> loginAuthentication(String searchInput, String password);
}
