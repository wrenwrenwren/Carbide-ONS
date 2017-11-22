/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
    
    public void updateBatchQuery(ArrayList<String> batchqueryCommand){
        
        String usr = LoginInfo.username;
        String pwd = LoginInfo.password;
        
        for (String cmd : batchqueryCommand){
            System.out.println(cmd);
        }
                        
        if (connection == null) {
            try {
                Class.forName(DATABASE_DRIVER);
                connection = DriverManager.getConnection(DATABASE_URL, usr, pwd);

                for (String command : batchqueryCommand){
                    PreparedStatement statement = connection.prepareStatement(command);
                    statement.addBatch();
                    statement.executeBatch();
                }
                
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
        ArrayList<String> queryBatches = new ArrayList<String>();
                
        if (accDB == "normal"){
            queryCommand = "drop table account_weights";
            
            createtablequery = "create table account_weights (" 
            + "FCM VARCHAR(255),"  
            + "Account VARCHAR(255)," 
            + "Nominal Int(11),"  
            + "Weights DOUBLE);";
            
            queryBatches.add(queryCommand);
            queryBatches.add(createtablequery);
            
            for (int i = 0; i<newAllocData.length; i++){
                String insertquery = "";
                insertquery = "insert into account_weights (FCM,Account,Nominal,Weights) values ('" + newAllocData[i][0] + "','" +  newAllocData[i][1] + "','" +  newAllocData[i][2] + "','" +  newAllocData[i][3] + "')";
                queryBatches.add(insertquery);
            }
            
            updateBatchQuery(queryBatches);
            
        } else if (accDB == "hedge"){
            
            queryCommand = "drop table account_weights_hedge";
            
            createtablequery = "create table account_weights_hedge (" 
            + "FCM VARCHAR(255),"  
            + "Account VARCHAR(255)," 
            + "Nominal Int(11),"  
            + "Weights DOUBLE);";
            
            queryBatches.add(queryCommand);
            queryBatches.add(createtablequery);
            
            for (int i = 0; i<newAllocData.length; i++){
                String insertquery = "";
                insertquery = "insert into account_weights_hedge (FCM,Account,Nominal,Weights) values ('" + newAllocData[i][0] + "','" +  newAllocData[i][1] + "','" +  newAllocData[i][2] + "','" +  newAllocData[i][3] + "')";
                queryBatches.add(insertquery);
            }
            
            updateBatchQuery(queryBatches);        
        }

    }
    
        public void insertSingleEntry(Object[] newEntry) {
            
            ArrayList<String> queryBatches = new ArrayList<String>();
            
            String date = (String) newEntry[0];
            String symbol = (String) newEntry[1];
            float amount = (float) newEntry[2];
            float strike = (float) newEntry[3];
            float price = (float) newEntry[4];
            String type = (String) newEntry[5];
            String expiry = (String) newEntry[6];
            String month = (String) newEntry[7];
            String comments = (String) newEntry[8];
            String account = (String) newEntry[9];
            String accDB = (String) newEntry[10];
            
            String queryCommand = "insert into entries (Date,Symbol,Amount,Strike,Price,Type,Expiry,Month,Comments,Account,AccType) values ('" + date + "','" +  symbol + "','" +  amount + "','" +  strike + "','" +  price + "','" +  type + "','" +  expiry + "','" +  month + "','" +  comments + "','" +  account + "','" +  accDB + "')";
            queryBatches.add(queryCommand);
            
            updateBatchQuery(queryBatches);
            
        }
        
        public void insertBatchEntry(Object[][] newEntry) {
            
            ArrayList<String> queryBatches = new ArrayList<String>();

            for (int i =0; i < newEntry.length; i++){
                
                String date = (String) newEntry[i][0];
                String symbol = (String) newEntry[i][1];
                float amount = (float) newEntry[i][2];
                float strike = (float) newEntry[i][3];
                float price = (float) newEntry[i][4];
                String type = (String) newEntry[i][5];
                String expiry = (String) newEntry[i][6];
                String month = (String) newEntry[i][7];
                String comments = (String) newEntry[i][8];
                String account = (String) newEntry[i][9];
                String accDB = (String) newEntry[i][10];
                
                String queryCommand = "insert into entries (Date,Symbol,Amount,Strike,Price,Type,Expiry,Month,Comments,Account,AccType) values ('" + date + "','" +  symbol + "','" +  amount + "','" +  strike + "','" +  price + "','" +  type + "','" +  expiry + "','" +  month + "','" +  comments + "','" +  account + "','" +  accDB + "')";
                
                queryBatches.add(queryCommand);

            }
            
            for (String cmds : queryBatches){
                System.out.println(cmds);
            }
                
            
            updateBatchQuery(queryBatches);
            
        }
        
        public ArrayList<String> getTableNames(){       
            String usr = LoginInfo.username;
            String pwd = LoginInfo.password;
            ArrayList<String> tableLists = new ArrayList<String>();
                    
            if (connection == null) {
                try {
                    Class.forName(DATABASE_DRIVER);
                    connection = DriverManager.getConnection(DATABASE_URL, usr, pwd);
                    DatabaseMetaData md = connection.getMetaData();
                    ResultSet rs = md.getTables(null, null, "%", null);
                    
                    while (rs.next()) {
                      tableLists.add(rs.getString(3));
                    }
                    
                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println("Error in SQL Command!");
                }
            }
            
            return tableLists;
        }
        
        public void aggregation(int[] conditions){
            
            ArrayList<String> queryBatches = new ArrayList<String>();
            
            if (conditions[0] == 1){
                queryBatches.add("drop table allcombined");
                
                String query = "create table allcombined (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Strike DOUBLE,"
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Amount DOUBLE," 
                    + "Price DOUBLE);";
                
                queryBatches.add(query);
                
                String aggregatequery = "insert into allcombined select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price from entries group by Symbol, Strike, Type, Expiry, Month, Price;";
                
                queryBatches.add(aggregatequery);
                
            } else {
                
                String query = "create table allcombined (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Strike DOUBLE,"
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Amount DOUBLE," 
                    + "Price DOUBLE);";
                
                queryBatches.add(query);
                
                String aggregatequery = "insert into allcombined select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price from entries group by Symbol, Strike, Type, Expiry, Month, Price;";
                
                queryBatches.add(aggregatequery);
                
            }
            
            if (conditions[1] == 1){
                queryBatches.add("drop table normalcombined");
                
                String query = "create table normalcombined (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Strike DOUBLE,"
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Amount DOUBLE," 
                    + "Price DOUBLE, AccType VARCHAR(255));";
                
                queryBatches.add(query);
                
                String aggregatequery = "insert into normalcombined select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price, AccType from entries where AccType = 'Absolute' group by Symbol, Strike, Type, Expiry, Month, Price;";
                
                queryBatches.add(aggregatequery);
                
            } else {
                
                String query = "create table normalcombined (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Strike DOUBLE,"
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Amount DOUBLE," 
                    + "Price DOUBLE, AccType VARCHAR(255));";
                
                queryBatches.add(query);
                
                String aggregatequery = "insert into normalcombined select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price, AccType from entries where AccType = 'Absolute' group by Symbol, Strike, Type, Expiry, Month, Price;";
                
                queryBatches.add(aggregatequery);
                
            }
            
            if (conditions[2] == 1){
                queryBatches.add("drop table hedgecombined");
                
                String query = "create table hedgecombined (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Strike DOUBLE,"
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Amount DOUBLE," 
                    + "Price DOUBLE, AccType VARCHAR(255));";
                
                queryBatches.add(query);
                
                String aggregatequery = "insert into hedgecombined select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price, AccType from entries where AccType = 'Hedge' group by Symbol, Strike, Type, Expiry, Month, Price;";
                
                queryBatches.add(aggregatequery);
                
            } else {
                
                String query = "create table hedgecombined (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Strike DOUBLE,"
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Amount DOUBLE," 
                    + "Price DOUBLE, AccType VARCHAR(255));";
                
                queryBatches.add(query);
                
                String aggregatequery = "insert into hedgecombined select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price, AccType from entries where AccType = 'Hedge' group by Symbol, Strike, Type, Expiry, Month, Price;";
                
                queryBatches.add(aggregatequery);
                
            }

            updateBatchQuery(queryBatches);

        }
        
        public void createEntries(){
            
            String createentriesquery = "create table entries (" 
                    + "Date VARCHAR(255),"  
                    + "Symbol VARCHAR(255)," 
                    + "Amount DOUBLE,"  
                    + "Strike DOUBLE,"
                    + "Price DOUBLE,"  
                    + "Type VARCHAR(255),"
                    + "Expiry VARCHAR(255),"  
                    + "Month VARCHAR(255),"
                    + "Comments VARCHAR(255),"  
                    + "Account VARCHAR(255),"
                    + "AccType VARCHAR(255));";
            
            
            updateQuery(createentriesquery);
            
        }
        
        public Object[][] getAccInEntries(){
            
            Object[][] AccNameEntries = null;

            AccNameEntries = query("select distinct(Account) from entries");

            return(AccNameEntries);
            
        }
        
        public Object[][] getAllCombined(){
            
            Object[][] AccNameEntries = null;

            AccNameEntries = query("select * from allcombined");

            return(AccNameEntries);
            
        }
        
        public Object[][] getAbCombined(){
            
            Object[][] AccNameEntries = null;

            AccNameEntries = query("select * from normalcombined");

            return(AccNameEntries);
            
        }
        
        public Object[][] getHeCombined(){
            
            Object[][] AccNameEntries = null;

            AccNameEntries = query("select * from hedgecombined");

            return(AccNameEntries);
            
        }
        
        public Object[][] getSpecificCombined(String AccName){
            
            Object[][] AccNameEntries = null;

            AccNameEntries = query("select max(Date), Symbol, Strike, Type, Expiry, Month, sum(Amount), Price, AccType from entries where Account = '" + AccName + "' group by Symbol, Strike, Type, Expiry, Month, Price, AccType;");

            return(AccNameEntries);
            
        }
        
        
        
}

