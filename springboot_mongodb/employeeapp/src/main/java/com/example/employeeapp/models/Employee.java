package com.example.employeeapp.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;
import java.time.LocalDate;


@Document(collection="employees")
@JsonIgnoreProperties(value = {"createdAt"}, allowGetters = true)
public class Employee {

    //Employee Attributes
    @Id
    private String id;

    @NotBlank
    @Size(max=50, message = "First name should be under 50 characters")
    private String firstName;

    @NotBlank
    @Size(max=50, message = "Last name should be under 50 characters")
    private String lastName;

    @Size(max=1, message = "Middle Initial should be 1 character")
    private String middleInitial;

    @NotBlank
    @Email(message = "Email should be valid")
    @Size(max=100)
    private String emailAddress;

    //@Pattern(regexp="[\\d]{11}")
    //@Size(max=11, message = "Phone number should be under 11 characters")
    @Size(min=0,max=11)
    private String phoneNumber;

    private String positionCategory;

    @NotNull
    @Past
    private LocalDate dateHired;

    private String addressOne;

    private String addressTwo;

    private String city;

    private String state;

    @Digits(integer=5, fraction=0)
    private Integer zipCode;

    @NotNull
    private Boolean active;

    //Constructor
    public Employee(){
        super();
    }

    public Employee(String id, String firstName, String lastName, String middleInitial, String emailAddress, String phoneNumber,
                    String positionCategory, LocalDate dateHired, String addressOne, String addressTwo, String city,
                    String state, Integer zipCode, Boolean active){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.positionCategory = positionCategory;
        this.dateHired = dateHired;
        this.addressOne = addressOne;
        this.addressTwo = addressTwo;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.active = active;

    }

    //Get Methods
    public String getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getMiddleInitial(){
        return middleInitial;
    }

    public String getEmailAddress(){
        return emailAddress;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getPositionCategory(){
        return positionCategory;
    }

    public LocalDate getDateHired(){
        return dateHired;
    }

    public String getAddressOne(){
        return addressOne;
    }

    public String getAddressTwo(){
        return addressTwo;
    }

    public String getCity(){
        return city;
    }

    public String getState(){
        return state;
    }

    public Integer getZipCode(){
        return zipCode;
    }

    public Boolean getActive(){
        return active;
    }

    //Set Methods
    public void setId(String id){
        this.id = id;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setMiddleInitial(String middleInitial){
        this.middleInitial = middleInitial;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setPositionCategory(String positionCategory){
        this.positionCategory = positionCategory;
    }

    public void setDateHired(LocalDate dateHired){
        this.dateHired = dateHired;
    }

    public void setAddressOne(String addressOne){
        this.addressOne = addressOne;
    }

    public void setAddressTwo(String addressTwo){
        this.addressTwo = addressTwo;
    }

    public void setCity(String city){
        this.city = city;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setZipCode(Integer zipCode){
        this.zipCode = zipCode;
    }

    public void setActive(Boolean active){
        this.active = active;
    }
    //ToString Method
    @Override
    public String toString(){
        return String.format(
                "Employee[id=%s, firstName='%s', lastName='%s', middleInitial='%s', emailAddress='%s', phoneNumber='%s'," +
                        " positionCategory='%s', dateHired='%s', addressOne='%s', addressTwo='%s', city='%s', " +
                        "state='%s', zipCode='%s', active='%s']",
                id, firstName, lastName, middleInitial, emailAddress,
                phoneNumber, positionCategory, dateHired, addressOne, addressTwo, city, state, zipCode, active
        );
    }

}


