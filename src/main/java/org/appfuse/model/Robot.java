package org.appfuse.model;

import java.util.Date;


public class Robot extends BaseObject {

    private Long id;
    private String designation;
    private Boolean qualityCheckPassed;
    private Date dateOfBuild;
    private User owner;
    private String ownerId;

    public Robot()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDesignation()
    {
        return designation;
    }

    public void setDesignation(String designation)
    {
        this.designation = designation;
    }

    public Boolean getQualityCheckPassed()
    {
        return qualityCheckPassed;
    }

    public void setQualityCheckPassed(Boolean qualityCheckPassed)
    {
        this.qualityCheckPassed = qualityCheckPassed;
    }

    public Date getDateOfBuild()
    {
        return dateOfBuild;
    }

    public void setDateOfBuild(Date dateOfBuild)
    {
        this.dateOfBuild = dateOfBuild;
    }

    public User getOwner()
    {
        return owner;
    }

    public void setOwner(User user)
    {
        this.owner = user;
    }

    public String getOwnerId()
    {
        return ownerId;
    }

    public void setOwnerId(String ownerId)
    {
        this.ownerId = ownerId;
    }
}
