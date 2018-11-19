package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.output.v1.ElectionCandidateResultOutput;
import br.edu.ulbra.election.election.output.v1.ResultOutput;
import br.edu.ulbra.election.election.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/result")
public class ResultApi {

    private final ResultService resultService;

    @Autowired
    public ResultApi(ResultService resultService){
        this.resultService = resultService;
    }

    @GetMapping("/election/{electionId}")
    public ResultOutput getResultByElection(@PathVariable Long electionId){
        return resultService.getResultByElection(electionId);
    }

    @GetMapping("/candidate/{candidateId}")
    public ElectionCandidateResultOutput getResultByCandidate(@PathVariable Long candidateId){
        return resultService.getResultByCandidate(candidateId);
    }

}
