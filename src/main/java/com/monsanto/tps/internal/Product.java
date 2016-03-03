package com.monsanto.tps.internal;

import java.util.Date;

/**
 * Created by SMALA on 3/3/2016.
 */
public class Product {
    private Long id;
    private String preCommercialName;
    private String commercialName;
    private Date createdDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreCommercialName() {
        return preCommercialName;
    }

    public void setPreCommercialName(String preCommercialName) {
        this.preCommercialName = preCommercialName;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
