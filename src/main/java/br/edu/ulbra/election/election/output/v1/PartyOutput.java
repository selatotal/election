package br.edu.ulbra.election.election.output.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Party Output Information")
public class PartyOutput {

    @ApiModelProperty(example = "1", notes = "Party Unique Identification")
    private Long id;
    @ApiModelProperty(example = "PJ", notes = "Party Code")
    private String code;
    @ApiModelProperty(example = "Party of Java", notes = "Party Name")
    private String name;
    @ApiModelProperty(example = "77", notes = "Party Number")
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
