package com.placement.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.placement.model.LoginRequest;
import com.placement.service.PlacementService;


@RestController
@RequestMapping("/placement/placement")
public class PlacementResource {
@Autowired
PlacementService placementservice;

@PostMapping("/approvecandidate")
public ResponseEntity<?> approveCandidate(@RequestParam String candidateId) {

	return new ResponseEntity<String>(this.placementservice.approveCandidate(candidateId), HttpStatus.OK);
}
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest request) {
	return this.placementservice.loginAuthentication(request.getSearchInput(), request.getPassword());
}

}
