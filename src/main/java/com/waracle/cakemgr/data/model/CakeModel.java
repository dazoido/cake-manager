package com.waracle.cakemgr.data.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "cake", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class CakeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String desc;

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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

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
