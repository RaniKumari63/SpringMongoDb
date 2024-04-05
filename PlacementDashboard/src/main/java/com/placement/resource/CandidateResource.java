package com.placement.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.placement.model.Candidate;
import com.placement.model.CreateCandidateRequest;
import com.placement.model.UpdateCandidateRequest;
import com.placement.service.CandidateService;

@RestController
@RequestMapping("/placement/candidate")
public class CandidateResource {
	@Autowired
	CandidateService candidateservice;

	@PostMapping("/createcandidate")
	public ResponseEntity<?> createCandidate(@RequestBody CreateCandidateRequest request) {

		return new ResponseEntity<String>(this.candidateservice.createCandidate(request), HttpStatus.OK);
	}

	@GetMapping("/getcandidates")
	public ResponseEntity<?> getCandidates(@RequestParam(required = false) String searchInput) {
		return new ResponseEntity<>(this.candidateservice.getCandidates(searchInput), HttpStatus.OK);
	}

	@GetMapping("/getcandidate")
	public ResponseEntity<?> getCandidate(@RequestParam String id) {
		return new ResponseEntity<Candidate>(this.candidateservice.getCandidateById(id), HttpStatus.OK);
	}

	@PutMapping("/updatecandidate")
	public ResponseEntity<?> updateCandidate(@RequestBody UpdateCandidateRequest request) {
		boolean flag = this.candidateservice.updateCandidate(request);
		if (flag)
			return new ResponseEntity<>("Candidate" + request.getCandidateId() + " is successfully updated", HttpStatus.OK);
		else
			return new ResponseEntity<>("No Candidate found with Id- " + request.getCandidateId(), HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/deletecandidate")
	public ResponseEntity<?> deleteCandidate(@RequestParam String id) {
		boolean flag = this.candidateservice.deleteCustomer(id);
		if (flag)
			return new ResponseEntity<>("Candidate" + id + " is successfully deleted", HttpStatus.OK);
		else
			return new ResponseEntity<>("No Candidate found with Id- " + id, HttpStatus.NOT_FOUND);
	}

}
