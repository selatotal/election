package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.client.CandidateClientService;
import br.edu.ulbra.election.election.client.VoterClientService;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.model.Vote;
import br.edu.ulbra.election.election.output.v1.CandidateOutput;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.output.v1.VoterOutput;
import br.edu.ulbra.election.election.repository.ElectionRepository;
import br.edu.ulbra.election.election.repository.VoteRepository;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final ElectionRepository electionRepository;

    private final VoterClientService voterClientService;

    private final CandidateClientService candidateClientService;

    private static final String MESSAGE_INVALID_VOTER = "Invalid Voter";

    @Autowired
    public VoteService(VoteRepository voteRepository, ElectionRepository electionRepository, VoterClientService voterClientService, CandidateClientService candidateClientService){
        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
        this.voterClientService = voterClientService;
        this.candidateClientService = candidateClientService;
    }

    public GenericOutput electionVote(String token, VoteInput voteInput){

        Election election = validateInput(token, voteInput.getElectionId(), voteInput);
        Vote vote = new Vote();
        vote.setElection(election);
        vote.setVoterId(voteInput.getVoterId());

        vote.setNullVote(false);
        if (voteInput.getCandidateNumber() == null){
            vote.setBlankVote(true);
        } else {
            vote.setBlankVote(false);
            try {
                CandidateOutput candidateOutput = candidateClientService.getByNumberAndElection(voteInput.getElectionId(), voteInput.getCandidateNumber());
                vote.setCandidateId(candidateOutput.getId());
            } catch (FeignException ex){
                if (ex.status() == 500){
                    vote.setNullVote(true);
                }
            }
        }

        voteRepository.save(vote);

        return new GenericOutput("OK");
    }

    public GenericOutput multiple(String token, List<VoteInput> voteInputList){
        for (VoteInput voteInput : voteInputList){
            this.electionVote(token, voteInput);
        }
        return new GenericOutput("OK");
    }

    private Election validateInput(String token, Long electionId, VoteInput voteInput){
        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException("Invalid Election");
        }
        if (voteInput.getVoterId() == null){
            throw new GenericOutputException(MESSAGE_INVALID_VOTER);
        }
        try {
            VoterOutput voterOutput = voterClientService.checkToken(token);
            if (!voterOutput.getId().equals(voteInput.getVoterId())){
                throw new GenericOutputException(MESSAGE_INVALID_VOTER);
            }
        } catch (FeignException ex){
            if (ex.status() == 500){
                throw new GenericOutputException(MESSAGE_INVALID_VOTER);
            }
        }

        Vote vote = voteRepository.findFirstByVoterIdAndElection(voteInput.getVoterId(), election);
        if (vote != null){
            throw new GenericOutputException("Voter already vote on that election");
        }
        return election;
    }

    public GenericOutput findVotesByVoter(Long voterId) {
        return new GenericOutput(""+voteRepository.countByVoterId(voterId));
    }
}
