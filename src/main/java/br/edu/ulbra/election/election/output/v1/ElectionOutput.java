package br.edu.ulbra.election.election.output.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Election Output Information")
public class ElectionOutput {

    @ApiModelProperty(example = "1", notes = "Election Unique Identification")
    private Long id;
    @ApiModelProperty(example = "2018", notes = "Election Year")
    private Integer year;
    @ApiModelProperty(example = "RS", notes = "State Code")
    private String stateCode;
    @ApiModelProperty(example = "Senator", notes = "Election Description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
