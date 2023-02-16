/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.models;

import com.exavalu.services.DepartmentService;
import com.exavalu.services.EmployeeService;

import com.exavalu.services.RoleService;
import com.exavalu.services.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author Biswajit
 */
public class User extends ActionSupport implements ApplicationAware, SessionAware, Serializable {

    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String addressLine1;
    private String addressLine2;
    private String phoneNumber;
    private String countryCode;
    private String stateCode;
    private String distCode;
    private int status;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistCode() {
        return distCode;
    }

    public void setDistCode(String distCode) {
        this.distCode = distCode;
    }

    private SessionMap<String, Object> sessionMap = (SessionMap) ActionContext.getContext().getSession();

    private ApplicationMap map = (ApplicationMap) ActionContext.getContext().getApplication();

    @Override
    public void setApplication(Map<String, Object> application) {
        map = (ApplicationMap) application;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        sessionMap = (SessionMap) session;
    }

    public String doLogin() throws Exception {
        String result = "FAILURE";

        boolean success = UserService.getInstance().doLogin(this);
        ArrayList empList = EmployeeService.getAllEmployees();
        ArrayList deptList = DepartmentService.getAllDepartment();
        ArrayList roleList = RoleService.getAllRole();

        if (success) {
            System.out.println("returning Success from doLogin method");
            sessionMap.put("User", this);
            sessionMap.put("EmpList", empList);
            sessionMap.put("DeptList", deptList);
            sessionMap.put("RoleList", roleList);
            result = "SUCCESS";
        } else {
           String loginErrorMsg = "Either Email or Password is Wrong!";
            sessionMap.put("LoginErrorMsg", loginErrorMsg);
            //System.out.println("returning Failure from doLogin method");
            Logger log = Logger.getLogger(User.class.getName());            
            log.error("Incorrect email or password");
            System.out.println("returning Failure from Login method");
        }
        return result;
    }

    public String doLogOut() {
        sessionMap.clear();
        return "SUCCESS";
    }

    public String addUser() {
        String result = "FAILURE";

        try {
            boolean res = UserService.sendData(this);
            if (res) {
                result = "SUCCESS";
            } else {
                String alreadyExist = "Email Id Already Exist";
                sessionMap.put("AlreadyExist", alreadyExist);
                Logger log = Logger.getLogger(User.class.getName());
            log.error("Email id already exists|||returning Failure from Login method");
            //System.out.println("returning Failure from Login method");
            }
        } catch (IOException ex) {
        }
        return result;
    }

    public String doPreSignup() throws Exception {

        String result = "SUCCESS";

        ArrayList countryList = UserService.getAllCountries();
        ArrayList stateList;
        ArrayList distList;

        sessionMap.put("CountryList", countryList);
        
        System.err.println("CountryCode "+this.countryCode);
        System.err.println("StateCode "+this.stateCode);
        System.err.println("DistCode "+this.distCode);
        
        if(this.countryCode!=null)
        {
            System.err.println("Selected country:: "+this.countryCode);
            stateList = UserService.getAllStates(this.countryCode);
            sessionMap.put("StateList", stateList);
            sessionMap.put("User", this);
            result="STATELIST";
        }
        
        if(this.stateCode!=null)
        {
            System.err.println("Selected state:: "+this.stateCode);
            distList = UserService.getAllDistricts(this.stateCode);
            sessionMap.put("DistList", distList);
            sessionMap.put("User", this);
            result="DISTRICTLIST";
        }
        
       if (this.firstName != null && this.firstName.length()>0 && this.lastName != null && this.lastName.length()>0 && this.emailAddress != null && this.emailAddress.length()>0 && this.password!= null && this.password.length()>0 && this.stateCode != null && this.stateCode.length() > 0 && this.countryCode != null && this.countryCode.length() > 0 && this.distCode != null && this.distCode.length() > 0) {
            System.out.println(firstName + lastName +  emailAddress + password+ this.getStateCode()+ this.getCountryCode()+ this.getDistCode());
            boolean res = UserService.sendData(this);
            if (res) {
                result = "GOOD";
            } else {
                String alreadyExist = "Email Id Already Exist";
                sessionMap.put("AlreadyExist", alreadyExist);
            }
        
}
        return result;
}
}
