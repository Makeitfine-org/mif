/*
 * Created in scope of "Make it fine" project
 */

package com.stingion.makeifine.data.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
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
}
