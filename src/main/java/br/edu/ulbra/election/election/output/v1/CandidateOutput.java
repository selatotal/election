package br.edu.ulbra.election.election.output.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Candidate Output Information")
public class CandidateOutput {

    @ApiModelProperty(example = "1", notes = "Candidate Unique Identification")
    private Long id;
    @ApiModelProperty(example = "John Doe", notes = "Candidate name")
    private String name;
    @ApiModelProperty(example = "77654", notes = "Candidate Election Number")
    private Long numberElection;
    @ApiModelProperty(notes = "Candidate Party Data")
    private PartyOutput partyOutput;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumberElection() {
        return numberElection;
    }

    public void setNumberElection(Long numberElection) {
        this.numberElection = numberElection;
    }

    public PartyOutput getPartyOutput() {
        return partyOutput;
    }

    public void setPartyOutput(PartyOutput partyOutput) {
        this.partyOutput = partyOutput;
    }
}
