package br.edu.ulbra.election.election.client;

import br.edu.ulbra.election.election.output.v1.VoterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class VoterClientService {

    private final VoterClient voterClient;

    @Autowired
    public VoterClientService(VoterClient voterClient){
        this.voterClient = voterClient;
    }

    public VoterOutput getById(Long voterId){
        return voterClient.getById(voterId);
    }

    @FeignClient(name = "voter-service", url = "${url.voter-service}")
    private interface VoterClient {

        @GetMapping("/v1/voter/{voterId}")
        VoterOutput getById(@PathVariable(name="voterId") Long voterId);
    }
}
