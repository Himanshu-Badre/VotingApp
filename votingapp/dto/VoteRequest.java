package com.votingapp.dto;

public class VoteRequest {

	 private String candidateName;

	    public VoteRequest() {}

	    public String getCandidateName() {
	        return candidateName;
	    }

	    public void setCandidateName(String candidateName) {
	        this.candidateName = candidateName;
	    }
}
