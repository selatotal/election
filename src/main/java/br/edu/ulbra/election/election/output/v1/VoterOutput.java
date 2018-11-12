package br.edu.ulbra.election.election.output.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Voter Output Information")
public class VoterOutput {

    @ApiModelProperty(example = "1", notes = "Voter Unique Identification")
    private Long id;
    @ApiModelProperty(example = "john@doe.com", notes = "Voter email")
    private String email;
    @ApiModelProperty(example = "John Doe", notes = "Voter name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
