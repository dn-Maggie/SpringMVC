package com.dn.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * 
 * @author Maggie
 * java��ȡproperties�ļ�ʵ��
 */
public class PropertiesTest {
	  public static void main(String[] args) throws UnsupportedEncodingException {  
	        // TODO Auto-generated method stub  
	        Logger log= Logger.getLogger("log");  
	        log.setLevel(Level.INFO);//���Ƶȼ�  
	        Properties pro = new Properties();  
	        InputStream inStream = PropertiesTest.class  
	                .getResourceAsStream("/config/properties/config.properties");  
	        try {  
	            pro.load(inStream);  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        log.info(pro.getProperty("jdbc_password"));  
	        String words =pro.getProperty("jdbc_url");  
	        words = new String(words.getBytes("ISO-8859-1"),"UTF-8");//ת��  
	        log.info(words);  
	    }  
}
