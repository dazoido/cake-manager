package com.waracle.cakemgr.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "cake", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CakeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title", unique = true, nullable = false)
    @NotBlank
    private String title;

    @NotBlank
    private String desc;

    @NotBlank
    private String image;

    public CakeModel() {
    }

    public CakeModel(String title, String desc, String image) {
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotBlank
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @NotBlank
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Cake["
                    + "title => " + this.title
                    + "desc => " + this.desc
                    + "image => " + this.image
                    + "]";
    }
}
