package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.input.v1.ElectionInput;
import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/election")
public class ElectionApi {

    @GetMapping("/")
    @ApiOperation(value = "Get election List")
    public List<ElectionOutput> getAll(){
        return new ArrayList<>();
    }

    @GetMapping("/year/{year}")
    @ApiOperation(value = "Get election List by year")
    public List<ElectionOutput> getByYear(@PathVariable Integer year){
        return new ArrayList<>();
    }

    @GetMapping("/{electionId}")
    @ApiOperation(value = "Get election by Id")
    public ElectionOutput getById(@PathVariable Long electionId){
        return new ElectionOutput();
    }

    @PostMapping("/")
    @ApiOperation(value = "Create new election")
    public ElectionOutput create(@RequestBody ElectionInput electionInput){
        return new ElectionOutput();
    }

    @PutMapping("/{electionId}")
    @ApiOperation(value = "Update election")
    public ElectionOutput update(@PathVariable Long electionId, @RequestBody ElectionInput electionInput){
        return new ElectionOutput();
    }

    @DeleteMapping("/{electionId}")
    @ApiOperation(value = "Delete election")
    public GenericOutput delete(@PathVariable Long electionId){
        return new GenericOutput("OK");
    }
}
