/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.user;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, of = "phoneNumber")
@Table(name = "CONTACT")
@PrimaryKeyJoinColumn(name = "ID")
public class Contact extends User {

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "BIRTH_DAY", nullable = false)
    private Date birthDay;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    public Date getBirthDay() {
        return (Date) birthDay.clone();
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = (Date) birthDay.clone();
    }
}
