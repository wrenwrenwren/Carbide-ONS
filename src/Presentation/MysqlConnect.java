/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author weiren
 */

public class MysqlConnect {
    // init database constants
    private static final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/carbide_capital";
    
    private Connection connection;
    private boolean STATUS;

    // connect database
    public boolean connect(String USERNAME, String PASSWORD) {
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
                System.out.println("Connected!");
                STATUS = true;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Wrong user/password!!!");
            }
        }
        return STATUS;
    }

    // disconnect database
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("Disconnected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Object[][] query(String queryCommand){
        String usr = LoginInfo.username;
        String pwd = LoginInfo.password;
        
        List<String[]> table = new ArrayList<>();
        
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, usr, pwd);
                Statement st = connection.createStatement();
                ResultSet rs = st.executeQuery(queryCommand);
                ResultSetMetaData metadata = rs.getMetaData();
                int numberOfColumns = metadata.getColumnCount();
                
                while(rs.next()) {
                    String[] row = new String[numberOfColumns];
                    for( int iCol = 1; iCol <= numberOfColumns; iCol++ ){
                            Object obj = rs.getObject( iCol );
                            row[iCol-1] = (obj == null) ?null:obj.toString();
                    }
                    table.add( row );
                }

                STATUS = true;
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error in SQL Command!");
            }
        }
        
        Object[][] data = null;
        
        if (!table.isEmpty()){
            // convert list to array for table display
            int numCol = table.get(0).length; 
            data = new Object[table.size()][numCol];
        
            for (int i = 0; i < table.size(); i++){
                String[] strings = table.get(i);
                for (int j = 0; j < strings.length; j++){
                    data[i][j] = strings[j];
                }
            }
        }
        
        return data;
        
    }
    
    public void updateQuery(String queryCommand){
        String usr = LoginInfo.username;
        String pwd = LoginInfo.password;
        
        System.out.println(queryCommand);
                
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, usr, pwd);
                Statement st = connection.createStatement();
                st.executeUpdate(queryCommand);
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("Error in SQL Command!");
            }
        }
    }
    
    // query account data 
    public Object[][] queryAccount(String accDB) {
        
        String queryCommand = "";
        if (accDB == "normal"){
            queryCommand = "select * from accounts";
        } else if (accDB == "hedge"){
            queryCommand = "select * from accounts_hedge";
        }
        
        Object[][] result = query(queryCommand);
        
        return result;
    }
    
    //add a new account
    public void addNewAccount(String accDBname, String[] newAccount) {
        
        String newFcmName = newAccount[0];
        String newAccountName = newAccount[1];
        
        String queryCommand = "";
        
        if (accDBname == "normal"){
            queryCommand = "insert into accounts (FCM,Account) values ('" + newFcmName + "','" +  newAccountName + "')";
        } else if (accDBname == "hedge"){
            queryCommand = "insert into accounts_hedge (FCM,Account) values ('" + newFcmName + "','" +  newAccountName + "')";
        }
        
        updateQuery(queryCommand);

    }
    
    public void deleteAccount(String accDBname, String[] oldAccount) {
        
        String oldFcmName = oldAccount[0];
        String oldAccountName = oldAccount[1];
        
        String queryCommand = "";
        
        if (accDBname == "normal"){
            queryCommand = "delete from accounts where FCM='" + oldFcmName + "' and Account='" +  oldAccountName + "'";
        } else if (accDBname == "hedge"){
            queryCommand = "delete from accounts_hedge where FCM='" + oldFcmName + "' and Account='" +  oldAccountName + "'";
        }
        
        updateQuery(queryCommand);

    }
    
    public Object[][] queryAllocation(String accDB) {
        
        String queryCommand = "";
        if (accDB == "normal"){
            queryCommand = "select * from account_weights";
        } else if (accDB == "hedge"){
            queryCommand = "select * from account_weights_hedge";
        }
        
        Object[][] result = query(queryCommand);
        
        return result;
    }
    
    public void updateAllocation(String accDB, Object[][] newAllocData) {
        
        String queryCommand = "";
        String createtablequery = "";

        if (accDB == "normal"){
            queryCommand = "drop table account_weights";
            
            createtablequery = "create table account_weights (" 
            + "FCM VARCHAR(255),"  
            + "Account VARCHAR(255)," 
            + "Nominal Int(11),"  
            + "Weights DOUBLE);";
            
            updateQuery(queryCommand);
            updateQuery(createtablequery);
            
            for (int i = 0; i<newAllocData.length; i++){
                String insertquery = "";
                insertquery = "insert into account_weights (FCM,Account,Nominal,Weights) values ('" + newAllocData[i][0] + "','" +  newAllocData[i][1] + "','" +  newAllocData[i][2] + "','" +  newAllocData[i][3] + "')";
                updateQuery(insertquery);
            }
            
        } else if (accDB == "hedge"){
            queryCommand = "drop table account_weights_hedge";
        }
        
        
        
        
        
    }
    
    

}

