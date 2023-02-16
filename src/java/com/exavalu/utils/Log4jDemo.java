/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.exavalu.utils;

import org.apache.log4j.Logger;

/**
 *
 * @author SHIRSHAK
 */
public class Log4jDemo {
    
    private static final Logger logger = Logger.getLogger(Log4jDemo.class.getName());
    
    public static void main(String[] args)
    {
        logger.error("error message");  
        logger.info("info message");  
    }
}
