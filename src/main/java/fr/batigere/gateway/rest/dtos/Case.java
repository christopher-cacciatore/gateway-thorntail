package fr.batigere.gateway.rest.dtos;


public class Case {

    private String id;
    private String title;
    private String description;
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