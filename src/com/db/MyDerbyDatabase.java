package com.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyDerbyDatabase {

    Connection connectionObj = null;
    Statement statementObj = null;
    ResultSet resultsetObj = null;

    String tableName = "books";       // TableName
    String dataBaseName = "library";  // DataBaseName
    String id, bookName;
    public void dbInit(String dbPath) {

        String dbURL = "jdbc:derby:" + dbPath + File.separator + dataBaseName + ";create=true";
        try {
            connectionObj = DriverManager.getConnection(dbURL);

            statementObj = connectionObj.createStatement();

            statementObj.executeUpdate("CREATE TABLE " + tableName + "(BookId VARCHAR(20),BookName VARCHAR(20))");

            System.out.println("Database and Table Created Successfully !!!!");
            System.out.println("Database Name : "+dataBaseName+". Its Created in "+dbPath+"\n");
        } catch (SQLException ex) {
           // System.err.println(ex);
            System.out.println("Table Is Exist in "+dbPath);
        }
    }

    public void createTableRecord(String bookID, String BookName) {
        try {
            boolean result = statementObj.execute("INSERT INTO " + tableName + "(BookId,BookName)"
                    + "VALUES ('" + bookID + "','" + BookName + "')");
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public void updateTableRecord(String BookName, String bookID) {
        try {

            statementObj.executeUpdate("UPDATE  " + tableName + " SET BookName='" + BookName + "'WHERE BookId='" + bookID + "'");

        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public void readTableRecords() {
        try {
            resultsetObj = statementObj.executeQuery("select * FROM " + tableName);
            System.out.println("________________________________");
            System.out.println("| BOOK_ID |" + " BOOKNAME           |");
            while (resultsetObj.next()) {
                id = resultsetObj.getString(1);
                bookName = resultsetObj.getString(2);
                System.out.println("| " + id + "       | " + bookName);
            }
            System.out.println("________________________________");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void deleteTableRecord(String BookId) {
        try {
            boolean execute = statementObj.execute("DELETE  FROM " + tableName + " where BookId='" + BookId + "'");
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void dbClose() {

        if (connectionObj != null) {
            try {
                statementObj.close();
                connectionObj.close();
            } catch (SQLException ex) {
                System.err.println(ex);
            }
        }
    }
}
