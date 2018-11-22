package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.client.CandidateClientService;
import br.edu.ulbra.election.election.client.VoterClientService;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.model.Vote;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
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

    @Autowired
    public VoteService(VoteRepository voteRepository, ElectionRepository electionRepository, VoterClientService voterClientService, CandidateClientService candidateClientService){
        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
        this.voterClientService = voterClientService;
        this.candidateClientService = candidateClientService;
    }

    public GenericOutput electionVote(VoteInput voteInput){

        Election election = validateInput(voteInput.getElectionId(), voteInput);
        Vote vote = new Vote();
        vote.setElection(election);
        vote.setVoterId(voteInput.getVoterId());

        vote.setNullVote(false);
        if (voteInput.getCandidateNumber() == null){
            vote.setBlankVote(true);
        } else {
            vote.setBlankVote(false);
            try {
                candidateClientService.getById(voteInput.getCandidateNumber());
            } catch (FeignException ex){
                if (ex.status() == 500){
                    vote.setNullVote(true);
                }
            }
        }

        voteRepository.save(vote);

        return new GenericOutput("OK");
    }

    public GenericOutput multiple(List<VoteInput> voteInputList){
        for (VoteInput voteInput : voteInputList){
            this.electionVote(voteInput);
        }
        return new GenericOutput("OK");
    }

    public Election validateInput(Long electionId, VoteInput voteInput){
        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException("Invalid Election");
        }
        if (voteInput.getVoterId() == null){
            throw new GenericOutputException("Invalid Voter");
        }
        try {
            voterClientService.getById(voteInput.getVoterId());
        } catch (FeignException ex){
            if (ex.status() == 500){
                throw new GenericOutputException("Invalid Voter");
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
