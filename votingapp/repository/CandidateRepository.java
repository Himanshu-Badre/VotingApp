package com.votingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.votingapp.entity.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

	Candidate findByname(String name);
}
