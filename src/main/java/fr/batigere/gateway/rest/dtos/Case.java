package fr.batigere.gateway.rest.dtos;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class Case {

    @ApiModelProperty(value="ID technique du case", example="50fcecf6-bbc0-47fe-af49-a62355ae7f7a")
    @NotBlank
    private String id;

    @ApiModelProperty("titre du case")
    private String title;

    @ApiModelProperty("contenu du case tapé par l'utilisateur")
    private String description;

    @ApiModelProperty("détail de l'utilisateur créateur du case")
    private Contact createdBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Contact createdBy) {
        this.createdBy = createdBy;
    }
}
