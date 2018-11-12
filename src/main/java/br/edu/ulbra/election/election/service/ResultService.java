package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import br.edu.ulbra.election.election.output.v1.ResultOutput;
import br.edu.ulbra.election.election.repository.ElectionRepository;
import br.edu.ulbra.election.election.repository.VoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    private final ElectionRepository electionRepository;
    private final VoteRepository voteRepository;
    private final ModelMapper modelMapper;

    private static final String MESSAGE_ELECTION_NOT_FOUND = "Election not found";

    @Autowired
    public ResultService(ElectionRepository electionRepository, VoteRepository voteRepository, ModelMapper modelMapper){
        this.electionRepository = electionRepository;
        this.voteRepository = voteRepository;
        this.modelMapper = modelMapper;
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
        return resultOutput;
    }
}
