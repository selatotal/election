package br.edu.ulbra.election.election.client;

import br.edu.ulbra.election.election.output.v1.CandidateOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CandidateClientService {

    private final CandidateClient candidateClient;

    @Autowired
    public CandidateClientService(CandidateClient candidateClient){
        this.candidateClient = candidateClient;
    }

    public CandidateOutput getById(Long candidateId){
        return candidateClient.getById(candidateId);
    }

    public List<CandidateOutput> getByElection(Long electionId){
        return candidateClient.getByElection(electionId);
    }

    @FeignClient(name = "candidate-service", url = "${url.candidate-service}")
    private interface CandidateClient {

        @GetMapping("/v1/candidate/{candidateId}")
        CandidateOutput getById(@PathVariable(name = "candidateId") Long candidateId);

        @GetMapping("/v1/candidate/election/{electionId}")
        List<CandidateOutput> getByElection(@PathVariable(name = "electionId") Long electionId);
    }
}
