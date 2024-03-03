package com.votingapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.votingapp.dto.VoteRequest;
import com.votingapp.entity.Candidate;
import com.votingapp.service.CandidateService;

@RestController
public class CandidateController {

			
		@Autowired
		CandidateService candidateService;
	    
	    @PostMapping("/vote")
	    public ResponseEntity<String> vote(@RequestBody VoteRequest voteRequest) {
	        // Log the candidate name received from the front-end
	        System.out.println("Received candidate name: " + voteRequest.getCandidateName());

	        Candidate candidate = candidateService.getCandidateByName(voteRequest.getCandidateName());
	        Map<String, String> body = new HashMap<>();
	        body.put("isUserPresent", "true");

	        if (candidate != null) {
	            candidateService.addVote(voteRequest.getCandidateName());
	            return ResponseEntity.ok("Vote cast successfully.");
	        } else {
	            // Log a message if the candidate is not found
	            System.out.println("Candidate not found for name: " + voteRequest.getCandidateName());
	            return ResponseEntity.ok("Candidate Not Found For Name : " + voteRequest.getCandidateName());
	        }
	    }
	    
	    @PostMapping("/logout")
	    public ResponseEntity<Void> logout() {
	        // Call the logout method in the LogoutService
	    	candidateService.logout();

	        // Return a response indicating the success of the logout operation
	        return ResponseEntity.ok().build();
	    }
	    
	    @GetMapping("/candidates")
	    public List<Candidate> getCandidates() {
	        return candidateService.getCandidates();
	    }
}   
	      
	    
	    
		
		
//		@PostMapping("/vote")
//		public ResponseEntity<String> vote(@RequestBody VoteRequest voteRequest) {
//		    // Log the candidate name received from the front-end
//		    System.out.println("Received candidate name: " + voteRequest.getCandidateName());
//
//		    Candidate candidate = candidateService.getCandidateByName(voteRequest.getCandidateName());
//
//		    if (candidate != null) {
//		        candidateService.addVote(voteRequest.getCandidateName());
//		        
//		        // Log the successful vote addition
//		        System.out.println("Vote for candidate " + voteRequest.getCandidateName() + " added successfully.");
//		        
//		        return ResponseEntity.ok("Vote cast successfully.");
//		    } else {
//		        // Log that the candidate was not found
//		        System.out.println("Candidate not found for name: " + voteRequest.getCandidateName());
//
//		        // Return a not found response with appropriate message
//		        return ResponseEntity.notFound().build();
//		    }
//		}
	