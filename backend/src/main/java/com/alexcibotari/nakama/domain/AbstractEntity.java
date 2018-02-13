package com.alexcibotari.nakama.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class AbstractEntity implements IdEntity<String> {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Access(AccessType.PROPERTY)
  private String id;

  /**
   * Returns the identifier of the entity.
   *
   * @return the id
   */
  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public abstract String toString();

  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }

    if (this.id == null || obj == null || !(this.getClass().equals(obj.getClass()))) {
      return false;
    }

    AbstractEntity that = (AbstractEntity) obj;

    return this.id.equals(that.getId());
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return id == null ? 0 : id.hashCode();
  }
}
