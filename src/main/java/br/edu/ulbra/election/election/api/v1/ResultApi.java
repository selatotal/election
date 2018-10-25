package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.output.v1.ElectionCandidateResultOutput;
import br.edu.ulbra.election.election.output.v1.ResultOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/result")
public class ResultApi {

    @GetMapping("/election/{electionId}")
    public ResultOutput getResultByElection(@PathVariable Long electionId){
        return new ResultOutput();
    }

    @GetMapping("/candidate/{candidateId}")
    public ElectionCandidateResultOutput getResultByCandidate(@PathVariable Long candidateId){
        return new ElectionCandidateResultOutput();
    }

}
