package com.amnis.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BasicEntity implements Serializable {
    @Id
    @Column(name = "id")
    int id;
    public int getId() {
        return id;
    }

    public void setId(int commitId) {
        this.id = commitId;
    }
}
