package com.placement.service;

import java.util.List;

import com.placement.model.Candidate;
import com.placement.model.CreateCandidateRequest;
import com.placement.model.UpdateCandidateRequest;

public interface CandidateService {
	public String createCandidate(CreateCandidateRequest request);

	public List<?> getCandidates(String searchInput);

	public Candidate getCandidateById(String id);

	public boolean updateCandidate(UpdateCandidateRequest request);

	public boolean deleteCustomer(String Id);
}
