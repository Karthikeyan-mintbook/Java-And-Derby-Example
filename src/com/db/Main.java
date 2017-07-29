
package com.db;

import java.util.Scanner;

/**
 *
 * @author KARTHIKEYAN 9th July 17 
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("This Is CRUD (Create,Read,Update,Delete) Demo in Derby Database Using core Java 1.8.0_112");
        MyDerbyDatabase derby=new MyDerbyDatabase();
        Scanner s = new Scanner(System.in);
        System.out.print("Please Enter Your Database Destination Path Here:\n");
        System.out.println("Example -> C:\\Users\\Hello Programmer\\Documents or E:\\"+"\n");
        String dbPath = s.nextLine();
        System.out.println("\nPlease Wait..");
        derby.dbInit(dbPath);
        derby.createTableRecord("1", "RAMAYANAM");
        System.out.println("\n Created Records");
        derby.readTableRecords();
        derby.updateTableRecord("MAHABARATHAM", "1");
        System.out.println("\n After Update Operation.");
        derby.readTableRecords();
        derby.deleteTableRecord("1");
        System.out.println("\n After Delete Operation.Table Record Is Empty.");
        derby.readTableRecords();
        derby.dbClose();
        System.out.println("\n CRUD OPERATION HAS SUCCESS !!!!!");
    }
}
