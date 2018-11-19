package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.client.CandidateClientService;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.output.v1.*;
import br.edu.ulbra.election.election.repository.ElectionRepository;
import br.edu.ulbra.election.election.repository.VoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ElectionRepository electionRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;
    private final CandidateClientService candidateClientService;

    private static final String MESSAGE_ELECTION_NOT_FOUND = "Election not found";

    @Autowired
    public ResultService(ElectionRepository electionRepository, VoteRepository voteRepository, ModelMapper modelMapper, CandidateClientService candidateClientService){
        this.electionRepository = electionRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
        this.candidateClientService = candidateClientService;
    }

    public ResultOutput getResultByElection(Long electionId){
        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        Long totalVotes = voteRepository.countByElection(election);
        Long blankVotes = voteRepository.countByElectionAndBlankVote(election, true);
        Long nullVotes = voteRepository.countByElectionAndNullVote(election, true);

        ResultOutput resultOutput = new ResultOutput();
        resultOutput.setElection(modelMapper.map(election, ElectionOutput.class));
        resultOutput.setTotalVotes(totalVotes);
        resultOutput.setBlankVotes(blankVotes);
        resultOutput.setNullVotes(nullVotes);
        resultOutput.setCandidates(getCandidatesResult(electionId));
        return resultOutput;
    }

    private List<ElectionCandidateResultOutput> getCandidatesResult(Long electionId){
        List<CandidateOutput> candidateOutputList = candidateClientService.getByElection(electionId);
        return candidateOutputList.stream().map(this::toElectionCandidateResultOutput).collect(Collectors.toList());
    }

    private ElectionCandidateResultOutput toElectionCandidateResultOutput(CandidateOutput candidateOutput){
        ElectionCandidateResultOutput candidateResultOutput = new ElectionCandidateResultOutput();
        candidateResultOutput.setCandidate(candidateOutput);
        candidateResultOutput.setTotalVotes(voteRepository.countByCandidateId(candidateOutput.getId()));
        return candidateResultOutput;
    }

    public ElectionCandidateResultOutput getResultByCandidate(Long candidateId) {
        CandidateOutput candidateOutput = candidateClientService.getById(candidateId);
        return toElectionCandidateResultOutput(candidateOutput);
    }
}
