package com.votingapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.votingapp.entity.Candidate;
import com.votingapp.repository.CandidateRepository;

import jakarta.servlet.http.HttpSession;


@Service
public class CandidateService {

//	@Autowired
//	CandidateRepository candidateRepo;

	@Autowired
    private CandidateRepository candidateRepository; // Uncommented @Autowired annotation	
	
	public List<Candidate> getCandidates() {
        return candidateRepository.findAll();
    }

    public Candidate getCandidateByName(String candidateName) {
        return candidateRepository.findByname(candidateName);
    }

    public boolean addVote(String candidateName) {
        Candidate candidate = candidateRepository.findByname(candidateName);
        if (candidate != null) {
            candidate.setVotes(candidate.getVotes() + 1);
            candidateRepository.save(candidate);
            return true; // Vote counted successfully
        }
        return false; // Candidate not found
    }
    
    @Autowired
    public void logout() {
        // Get the session and invalidate it
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpSession session = requestAttributes.getRequest().getSession(false);
            if (session != null) {
                session.invalidate();
            }
        }
    }
    
    
  }
