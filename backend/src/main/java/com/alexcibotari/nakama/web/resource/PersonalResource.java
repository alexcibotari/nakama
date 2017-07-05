package com.alexcibotari.nakama.web.resource;

import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PersonalResource {

    private String givenName;
    private String familyName;
    private LocalDate birthDate;

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(getGivenName())) {
            sb.append(getGivenName());
        }

        if (!StringUtils.isEmpty(getFamilyName())) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(getFamilyName());
        }
        return sb.toString();
    }
}
