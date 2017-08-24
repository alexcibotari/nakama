package com.alexcibotari.nakama.domain;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Personal {

    private String givenName;
    private String familyName;
    private LocalDate birthday;

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }
}
