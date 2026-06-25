package com.nj.candidate.service;

import com.nj.candidate.dto.CandidateDetails;
import com.nj.candidate.mapper.EntityDtoMapper;
import com.nj.candidate.repository.CandidateRepository;
import org.springframework.stereotype.Service;

@Service
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public CandidateDetails getCandidateDetails(Integer id) {
        return this.candidateRepository.findById(id)
                                       .map(EntityDtoMapper::toCandidateDetails)
                                       .orElseThrow();
    }

}
