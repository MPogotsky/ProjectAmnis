package com.amnis.model;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BasicEntity implements Serializable {
    @Id
    int id;
}
