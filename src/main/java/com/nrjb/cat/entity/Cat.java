package com.nrjb.cat.entity;

import com.nrjb.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cat {

    @Id
    Integer id;

    SexType sexType;

    TnrType tnrType;

    String color;

    String kind;

    String explanation;

    @ManyToOne
    User userId;

}
