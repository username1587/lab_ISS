package models;

public class Bug {
    private boolean isActive;
    private String denumire;
    private String descriere;
    private Integer id;
    private User createdBy;
    private User solvedBy;

    public Bug() {
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean active) {
        isActive = active;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getSolvedBy() {
        return solvedBy;
    }

    public void setSolvedBy(User solvedBy) {
        this.solvedBy = solvedBy;
    }

    @Override
    public String toString() {
        return "Bug{" +
                "isActive=" + isActive +
                ", denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'' +
                ", id=" + id +
                ", createdBy=" + createdBy +
                ", solvedBy=" + solvedBy +
                '}';
    }
}
