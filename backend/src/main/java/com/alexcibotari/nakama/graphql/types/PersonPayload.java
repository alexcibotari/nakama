package com.alexcibotari.nakama.graphql.types;

import java.time.LocalDate;

public class PersonPayload extends AbstractAuditingPayload {

  private String email;

  private String givenName;
  private String familyName;
  private LocalDate birthday;
  private String gender;

  private String jobTitle;
  private String telephone;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

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

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Override
  public String toString() {
    return "PersonPayload{"
      + "email='" + email + '\''
      + ", givenName='" + givenName + '\''
      + ", familyName='" + familyName + '\''
      + ", birthday=" + birthday
      + ", gender='" + gender + '\''
      + ", jobTitle='" + jobTitle + '\''
      + ", telephone='" + telephone + '\''
      + '}';
  }
}
