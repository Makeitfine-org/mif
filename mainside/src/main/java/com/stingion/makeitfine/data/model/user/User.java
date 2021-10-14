/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2019
 *  @author stingion
 */

package com.stingion.makeitfine.data.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stingion.makeitfine.data.model.UserProfile;
import com.stingion.makeitfine.data.model.utils.AttrConverter;
import com.stingion.makeitfine.data.model.utils.State;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = "password")
@EqualsAndHashCode(of = "ssoId")
@Table(name = "APP_USER")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @TableGenerator(
            name = "User_gen",
            table = "SEQUENCES",
            pkColumnName = "SEQ_NAME",
            valueColumnName = "SEQ_NUMBER",
            pkColumnValue = "APP_USER_SEQUENCE",
            allocationSize = 1)
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User_gen")
    private int id;

    @Column(name = "SSO_ID", nullable = false, unique = true)
    private String ssoId;

    @JsonIgnore
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @Column(
            name = "STATE",
            nullable = false,
            columnDefinition = "ENUM('Active', 'Inactive', 'Deleted', 'Locked')")
    @Convert(converter = AttrConverter.StateConvertor.class)
    private State state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "APP_USER_USER_PROFILE",
            joinColumns = {@JoinColumn(name = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "USER_PROFILE_ID")})
    private Set<UserProfile> userProfiles;
}
