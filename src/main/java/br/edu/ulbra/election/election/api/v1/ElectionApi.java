package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.input.v1.ElectionInput;
import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.service.ElectionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/election")
public class ElectionApi {

    private final ElectionService electionService;

    @Autowired
    public ElectionApi(ElectionService electionService){
        this.electionService = electionService;
    }

    @GetMapping("/")
    @ApiOperation(value = "Get election List")
    public List<ElectionOutput> getAll(){
        return electionService.getAll();
    }

    @GetMapping("/year/{year}")
    @ApiOperation(value = "Get election List by year")
    public List<ElectionOutput> getByYear(@PathVariable Integer year){
        return new ArrayList<>();
    }

    @GetMapping("/{electionId}")
    @ApiOperation(value = "Get election by Id")
    public ElectionOutput getById(@PathVariable Long electionId){
        return electionService.getById(electionId);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new election")
    public ElectionOutput create(@RequestBody ElectionInput electionInput){
        return electionService.create(electionInput);
    }

    @PutMapping("/{electionId}")
    @ApiOperation(value = "Update election")
    public ElectionOutput update(@PathVariable Long electionId, @RequestBody ElectionInput electionInput){
        return electionService.update(electionId, electionInput);
    }

    @DeleteMapping("/{electionId}")
    @ApiOperation(value = "Delete election")
    public GenericOutput delete(@PathVariable Long electionId){
        return electionService.delete(electionId);
    }
}
