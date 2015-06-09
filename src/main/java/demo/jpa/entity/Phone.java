package demo.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phone")
public class Phone {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  @Enumerated(EnumType.STRING)
  private PhoneType type;
  
  @Column(name = "phone_number")
  private String phoneNumber;
  
  @Column(name = "country_code")
  private String countryCode;
  
  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Employee owner;

  public Long getId() {
    return id;
  }

  public Phone setId(Long id) {
    this.id = id;
    return this;
  }

  public PhoneType getType() {
    return type;
  }

  public Phone setType(PhoneType type) {
    this.type = type;
    return this;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public Phone setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public Phone setCountryCode(String country_code) {
    this.countryCode = country_code;
    return this;
  }

  public Employee getOwner() {
    return owner;
  }

  public Phone setOwner(Employee owner) {
    this.owner = owner;
    return this;
  }
}