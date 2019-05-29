package com.luv2code.android.uvindex.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by lzugaj on 5/27/2019
 */

@Entity(tableName = "role")
public class Role {

    @PrimaryKey
    @ColumnInfo(name = "role_id")
    private Long id;

    @ColumnInfo(name = "description_name")
    private String descriptionName;

    @ColumnInfo(name = "code_name")
    private String codeName;

    public Role(String descriptionName, String codeName) {
        this.descriptionName = descriptionName;
        this.codeName = codeName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescriptionName() {
        return descriptionName;
    }

    public void setDescriptionName(String descriptionName) {
        this.descriptionName = descriptionName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", descriptionName='" + descriptionName + '\'' +
                ", codeName='" + codeName + '\'' +
                '}';
    }
}