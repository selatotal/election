package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.model.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {

    Vote findFirstByVoterIdAndElection(Long voterId, Election election);

    Long countByElection(Election election);

    Long countByElectionAndBlankVote(Election election, Boolean blankVote);

    Long countByElectionAndNullVote(Election election, Boolean nullVote);

    Long countByCandidateId(Long candidateId);

    Long countByVoterId(Long voterId);
}
