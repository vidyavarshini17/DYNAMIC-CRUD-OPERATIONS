/*Database SI - Table studentData
Description of the studentData table
id	      int	        NO	   PRI    auto_increment
s_name	  varchar(50)	NO			
phone_no  int	        YES			
marks	  int	        YES	

//contents of the studentData table
1	vidya	    1111	100
2	varshini	2222	99
3	s	        3100	98 */

import java.sql.*;
import java.util.*;
import java.sql.Statement;
import java.sql.Connection;

public class user {
    public static void main(String[] args) throws Exception{
        Scanner sc=new Scanner(System.in); //create object of the scanner class

        System.out.println("Choose an operation to perform");
        System.out.println("1.Insert new entry 2. Display entry  3. Update  4.Delete an entry  5.Display all records"); 

        int option=sc.nextInt(); //get the option as an input from the user

        switch(option){
            case 1:
                System.out.println("Insertion operation");

                System.out.println("Enter the name");  //get the student name as input from the user
                String name=sc.next();

                System.out.println("Enter phone number"); //get the student phoneNo as input from the user
                int phone=sc.nextInt();

                System.out.println("Enter the mark");  //get the student mark as input from the user
                int mark=sc.nextInt();                

                insertStudent(name,phone,mark);//call the method insertStudent to insert new student data into the table
                break;

            case 2:
                System.out.println("Display operation");

                System.out.println("Enter the ID number to display");
                int id=sc.nextInt(); //get the id as input from the user to display the particular record

                displayStudent(id);//call the method displayStudent to display student data
            break;

            case 3:
                System.out.println("Updation operation");

                System.out.println("Enter the ID number to update");
                int idNo=sc.nextInt(); //get the id as input from the user to update the particular record

                System.out.println("Choose an option");
                System.out.println("1.update name 2.update phone_no 3.update marks");

                int n=sc.nextInt();
                switch(n){
                    case 1: //to update only the name
                    System.out.println("Enter the new name");
                    String newName=sc.next(); //get the new name as input

                    updateName(idNo,newName); //call updateName method to update the name of the student with the given id
                    break;

                    case 2: //to update only the phone number
                    System.out.println("Enter the new phone number");
                    int newPhone=sc.nextInt(); //get the new phone number as input

                    updatePhone(idNo,newPhone);//call updatePhone method to update the phone number of the student with the given id
                    break;

                    case 3: //to update only the marks
                    System.out.println("Enter the new mark"); //get the new marks as input
                    int newMark=sc.nextInt();
                    updateMarks(idNo,newMark);
                    break;

                    default:
                    System.out.println("Try again");
                    break;
                }
            break;

            case 4:
                System.out.println("Deletion Operation");

                System.out.println("Enter the ID number to delete");
                int ID=sc.nextInt(); //get the id as input from the user to delete the particular record

                deleteStudent(ID);
                break;

            case 5:
                System.out.println("Display entire student table");
                
                displayAll();
                break;

            default:
                System.out.println("Try again");
                break;
            }
        sc.close();
    }
    static void insertStudent(String name,int phone,int mark) throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class

            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection

            Statement s=conn.createStatement();//create statement object

            int i=s.executeUpdate("insert into studentdata(s_name,phone_no,marks) values('"+name+"','"+phone+"', '"+mark+"')"); 
            //i has the number of rows that are affected by the execution of the query

            if(i>=1){
                System.out.println("data inserted successfully"); //if i>=1 this statement is printed 
            }
            else{
                System.out.println("data insertion failed"); //if i=0 then this statement is printed
            }
            s.close(); //close the statement object
            conn.close(); //close the connection object
    }
    static void displayStudent(int id)throws Exception{
            Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class

            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection

            Statement s=conn.createStatement(); //create object of statement class
                        
            String sql="select * from studentdata where id='"+id+"'";
            ResultSet rs=s.executeQuery(sql); //rs contains the result set obtained after the execution of the above query

            rs.next(); //points to the first tuple that satisfy the condition
            System.out.println(rs.getInt(1)+" , "+rs.getString(2)+" , "+rs.getInt(3)+" , "+rs.getInt(4)); 
            // get the values of each column for the particular record based on the datatype

            s.close(); //close the statement object
            conn.close(); //close the connection object
    }
    static void updateName(int id,String name) throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection
        Statement s=conn.createStatement(); //create object of statement class

        String sql="update studentdata set s_name='"+name+"' where id='"+id+"'";
        int i=s.executeUpdate(sql); //i contains the value of the number of rows affected due to the query that's executed

        if(i>=1){
            System.out.println("Updation of name successful"); //if i is 1 then this statement is printed
        }
        else{
            System.out.println("Updation of name failed"); //if i is 0 then this statement is printed
        }
        s.close();
        conn.close();

    }
    static void updatePhone(int id,int phone) throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection
        Statement s=conn.createStatement(); //create object of statement class
        
        String sql="update studentdata set phone_no='"+phone+"' where id='"+id+"'"; 
        int i=s.executeUpdate(sql); //i has value of the no.of rows affected due to the execution of the query

        if(i>=1){
            System.out.println("Updation of phone number successful"); //printed if i is 1 after the above query is executed
        }
        else{
            System.out.println("Updation of phone number failed"); //printed if i=0 after the above query is executed
        }
        s.close(); //close statement object
        conn.close(); //close the connection object

    }
    static void updateMarks(int id,int mark) throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection
        Statement s=conn.createStatement(); //create object of statement class

        String sql="update studentdata set marks='"+mark+"' where id='"+id+"'";
        int i=s.executeUpdate(sql); //i has value of the no.of rows affected due to the execution of the query

        if(i>=1){
            System.out.println("Updation of marks successful"); //printed if i is 1 after the above query is executed
        }
        else{
            System.out.println("Updation of marks failed"); //printed if i=0 after the above query is executed
        }
        s.close();//close the statement object
        conn.close(); //close the connection object

    }
    static void deleteStudent(int id) throws Exception{

        Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection
        Statement s=conn.createStatement(); //create object of statement class

        String sql="delete from studentdata where id='"+id+"'"; 
        int i=s.executeUpdate(sql); //i has value of the no.of rows affected due to the execution of the query

        if(i>=1){
            System.out.println("Deleted successfully"); //printed if i is 1 after the above query is executed
        }
        else{
            System.out.println("deletion Failed"); //printed if i=0 after the above query is executed
        }

        s.close(); //close statement object
        conn.close(); //close connection object
    }

    static void displayAll() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver"); //load and register driver class
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/SI","root","Vidya@2001"); //establish connection
        Statement s=conn.createStatement(); //create object of statement class

        String sql="select * from studentdata"; //obtains all the data from the studentdata table
        ResultSet rs=s.executeQuery(sql); //results of the query are stored in rs

        System.out.println("--------------------------");
        while(rs.next()){ //while there exists atleast one more tuple to be displayed continue with the loop operations
            String data=" ";
            data=rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getInt(4);
            System.out.println(data);
        }
        System.out.println("--------------------------");

        s.close(); //close statement object
        conn.close(); //close connection object

    }
}
