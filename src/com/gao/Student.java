package com.gao;

import java.io.Serializable;

/**
 * User: wangchen
 * Date: 15/11/9
 * Time: 11:57
 */
public class Student implements Serializable {

    private static final long serialVersionUID = -8105598675888831734L;

    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
