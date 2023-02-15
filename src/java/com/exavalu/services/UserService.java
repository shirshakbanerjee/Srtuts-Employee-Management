/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.services;

import com.exavalu.models.Country;
import com.exavalu.models.District;
import com.exavalu.models.State;
import com.exavalu.models.User;
import com.exavalu.utils.JDBCConnectionManager;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author SHIRSHAK
 */
public class UserService {

    public static UserService loginService = null;

    private UserService() {
    }

    public static UserService getInstance() {
        if (loginService == null) {
            return new UserService();
        } else {
            return loginService;
        }
    }
    
    public boolean doLogin(User user) {
        boolean success = false;

        String sql = "Select * from users where status=1 and emailAddress=? and password=?";

        try {
            Connection con = JDBCConnectionManager.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmailAddress());
            ps.setString(2, user.getPassword());

            System.out.println("LoginService :: " + ps);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                success = true;
            }

        } catch (SQLException ex) {

        }

        return success;
    }

    public static boolean sendData(User user) throws IOException {
        boolean result = false;
        try (Connection con = JDBCConnectionManager.getConnection()) {
            String sql2 = "INSERT INTO users (emailAddress, password, firstName, lastName,status,countryCode,stateCode,districtCode) values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql2);
            preparedStatement.setString(1, user.getEmailAddress());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, user.getCountryCode());
            preparedStatement.setString(7, user.getStateCode());
            preparedStatement.setString(8, user.getDistCode());
            int row;
            row = preparedStatement.executeUpdate();
            System.out.println(row);
            if (row != 0) {
                result = true;
            }

            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    public static ArrayList getAllCountries() {
        
    ArrayList countryList= new ArrayList();
    Connection con= JDBCConnectionManager.getConnection();
    
    try {
        
        String sql= "Select * from countries";
        
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        ResultSet rs=preparedStatement.executeQuery();
        
        while(rs.next())
        {
            Country country= new Country();
            country.setCountryCode(rs.getString("countryCode"));
            country.setCountryName(rs.getString("countryName"));
            countryList.add(country);
        }
    }catch(SQLException ex)
    {
        ex.printStackTrace();
    }
    System.out.println("CountryList:" + countryList.size());
    return countryList;
}

    public static ArrayList getAllStates(String countryCode) {
        
        ArrayList stateList= new ArrayList();
    Connection con= JDBCConnectionManager.getConnection();
    
    try {
        
        String sql= "Select * from states where countryCode=?";
        
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,countryCode);
        
        ResultSet rs=preparedStatement.executeQuery();
        
        while(rs.next())
        {
            State state= new State();
            state.setCountryCode(rs.getString("countryCode"));
            state.setStateName(rs.getString("stateName"));
            state.setStateCode(rs.getString("stateCode"));
            stateList.add(state);
        }
    }catch(SQLException ex)
    {
        ex.printStackTrace();
    }
    System.out.println("StateList:" + stateList.size());
    return stateList;
    }

    public static ArrayList getAllDistricts(String stateCode) {
        
          ArrayList distList= new ArrayList();
    Connection con= JDBCConnectionManager.getConnection();
    
    try {
        
        String sql= "Select * from districts where stateCode=?";
        
        PreparedStatement preparedStatement=con.prepareStatement(sql);
        preparedStatement.setString(1,stateCode);
        
        ResultSet rs=preparedStatement.executeQuery();
        
        while(rs.next())
        {
            District dist= new District();
            dist.setDistCode(rs.getString("districtCode"));
            dist.setDistName(rs.getString("districtName"));
            dist.setStateCode(rs.getString("stateCode"));
            distList.add(dist);
        }
    }catch(SQLException ex)
    {
        ex.printStackTrace();
    }
    System.out.println("DistList:" + distList.size());
    return distList;
    }
}