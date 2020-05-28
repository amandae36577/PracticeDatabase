//Amanda Esposito
//Project Code in Java
/* This program manipulates a table known as USER_ACCOUNT and has a LEFT JOIN with POST so see a wider expanse of data. The program prompts the
user for different prefrences based on the options selected from CRUD*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import java.util.*;
import java.sql.*;
import java.math.*;

public class Facebook { 
	public static void main(String[] args) throws SQLException 
    	{
		//autoFill(); // rests the tables data
		Boolean finished = false;
        
   		 //continuosly prompts the user for thier choice of CRUD until the user chooses to finish/exit the program.
       		 while(finished == false)
        	{
            		System.out.println("Press a number: \n1:Create\n2:Read\n3:Update\n4:Delete\n5:Finished");
            		Scanner input = new Scanner(System.in);
            		String in = input.nextLine();
            		switch(in)
           		{ //choose which command to do
				case "1": 
                        		create();
                           		break;
                    		case "2":
                            		read();
                            		break;
                    		case "3":
                        		update();
                            		break;
                    		case "4":
                        		delete();
                        		break;
                    		case "5":
                        		finished = true;
                        		return;
	                }
        	}

	}
	//creates the tables and fills in some example data
		public static void autoFill() throws SQLException 
		{
			String url = "jdbc:mysql://deltona.birdnest.org:3306/my_espositoa2_Facebook";
        		String user = "my.espositoa2";
        		String password = "4s07$8v55";
			String add = "";    		
        		try 
			{
 
    				Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement statement;
 
    				if (conn != null) 
				{
					String droppedUA = "DROP TABLE IF EXISTS USER_ACCOUNT";
        				String droppedPOST = "DROP TABLE IF EXISTS POST";
        				String queryUA = "CREATE TABLE IF NOT EXISTS USER_ACCOUNT (id INT UNSIGNED NOT NULL auto_increment, Email VARCHAR(255) NOT NULL," 
        				+ " FirstName VARCHAR(255), LastName VARCHAR(255), Phone BIGINT UNSIGNED, StreetAddress VARCHAR(255),"
        				+ " City VARCHAR(255), State VARCHAR(2), Zip INT UNSIGNED, "
        				+ " PRIMARY KEY(id), UNIQUE(Email))";
            
            				String queryPOST = "CREATE TABLE IF NOT EXISTS POST (id INT UNSIGNED NOT NULL auto_increment, "
       					+ "Created TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, "
        				+ "Title VARCHAR(255), Content VARCHAR(510), Published TIMESTAMP "
        				+ "NOT NULL DEFAULT CURRENT_TIMESTAMP, UserID INT UNSIGNED NOT NULL, "
        				+ "PRIMARY KEY(id), FOREIGN KEY(UserID) REFERENCES USER_ACCOUNT(id) ON DELETE CASCADE ON UPDATE CASCADE)";
           
        				//creating tables, comment out after first use to keep tables
        
					statement = conn.prepareStatement(droppedPOST);
					statement.execute(droppedPOST);
					statement = conn.prepareStatement(droppedUA);
					statement.execute(droppedUA);
					statement = conn.prepareStatement(queryUA);
					statement.execute(queryUA);
					statement = conn.prepareStatement(queryPOST);
					statement.execute(queryPOST);

					add = "Insert INTO USER_ACCOUNT (id, Email, FirstName, LastName, "
        				+ "Phone, StreetAddress, City, State, Zip) VALUES (NULL, "
        				+ "'willc@winthrop.edu','Will', 'Cats', 6302182030, "
        				+ "'203 South Sike', 'Chicago', 'SC', 29037)";
					statement = conn.prepareStatement(add);
					statement.execute(add);
        				add = "Insert INTO USER_ACCOUNT (id, Email, FirstName, LastName, "
        				+ "Phone, StreetAddress, City, State, Zip) VALUES (NULL, "
        				+ "'Atsj@winthrop.edu','Jack', 'Ats', 6302103930, "
        				+ "'2032 South Street', 'Naperville', 'IL', 29034)";
					statement = conn.prepareStatement(add);
					statement.execute(add);
        				add = "Insert INTO USER_ACCOUNT (id, Email, FirstName, LastName, "
        				+ "Phone, StreetAddress, City, State, Zip) VALUES (NULL, "
       					+ "'dots@winthrop.edu','Slash', 'Dot', 6302452030, "
        				+ "'2248 North Street', 'Wheatom', 'NC', 24034)";
					statement = conn.prepareStatement(add);
					statement.execute(add);
        				add = "Insert Into POST (id, Title, Content, UserID)"
        				+ " VALUES (NULL, 'My Day', 'I Had an Amazing day!', 1)";
					statement = conn.prepareStatement(add);
					statement.execute(add);
        				add = "Insert Into POST (id, Title, Content, UserID)"
        				+ " VALUES (NULL, 'My First Day of School',"
        				+ " 'I could not believe how beautiful campus was!', 2)";
					statement = conn.prepareStatement(add);
					statement.execute(add);
				}
			}
			catch (SQLException ex) 
			{
				ex.printStackTrace();
			}
		}
//insert statement
	public static void create() throws SQLException
	{
		String url = "jdbc:mysql://deltona.birdnest.org:3306/my_espositoa2_Facebook";
        	String user = "my.espositoa2";
        	String password = "4s07$8v55";
		String Email = "", FirstName = "", LastName = "", StreetAddress = "", City = "", State = "", Phone1 = "", zipIN = "";
		int Zip = 0;
		long Phone = 0;
		String value = "";
		Scanner input;
    		
        	try 
		{ 
    			Connection conn = DriverManager.getConnection(url, user, password);
 
    			if (conn != null) 
			{
        			System.out.println("Connected");
				String sql = "INSERT INTO USER_ACCOUNT (Email, FirstName, LastName, Phone, StreetAddress, City, State, Zip) VALUES (?, ?, ?, ?, ?, ?, ? ,?)";
 		
				System.out.println("'*' means you MUST answer");
        			for(int count = 0; count < 9; count++)
        			{
            				if(count == 0)
            				{
                				value = "Email*";
                				System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				Email = input.nextLine();
           				}
            				if(count == 1)
            				{
               					value = "First Name";
                				System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				FirstName = input.nextLine();
            				}
            				if(count == 2)
            				{
                				value = "Last Name";
                				System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				LastName = input.nextLine();
            				}
            				if(count == 3)
            				{
               					value = "Phone Number in Format 999-999-9999";
        					System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				String in = input.nextLine();
						char phone[] = new char[13];
            					phone = in.toCharArray();
						in = "";
			            		for(int number = 0; number < 12; number++)
            					{
                					char check = phone[number];
                					if(check != '-')
                					{
                    						in = in + check;                            
                					}
            					}
						Phone = Long.parseLong(in);
            				}
            				if(count == 4)
            				{
               					value = "Street Address";
        					System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				StreetAddress = input.nextLine();
            				}
            				if(count == 5)
            				{
                				value = "City";
        					System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				City = input.nextLine();
            				}
            				if(count == 6)
            				{
                				value = "Two Letter State Abbreviation";
        					System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				State = input.nextLine();
            				}
            				if(count == 7)
            				{
                				value = "5-Digit Zip Code";
        					System.out.println("Type User " + value + ": ");
						input = new Scanner(System.in);
	            				zipIN = input.nextLine();
						Zip = Integer.parseInt(zipIN);
            				}
	
				}
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, Email);
				statement.setString(2, FirstName);
				statement.setString(3, LastName);
				statement.setLong(4, Phone);
				statement.setString(5, StreetAddress);
				statement.setString(6, City);
				statement.setString(7, State);
				statement.setInt(8, Zip);
 
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) 
				{
    					System.out.println("A new user was inserted successfully!");
				}
    			}
		} catch (SQLException ex) 
		{
			Logger lgr = Logger.getLogger(Facebook2.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
    			ex.printStackTrace();
		}

	}
	public static void read() throws SQLException
	{
		String url = "jdbc:mysql://deltona.birdnest.org:3306/my_espositoa2_Facebook";
        	String user = "my.espositoa2";
        	String password = "4s07$8v55";
		String value = "";
		Scanner input;
    		
        	try 
		{ 
    			Connection conn = DriverManager.getConnection(url, user, password);
 
    			if (conn != null) 
			{

				String sql = "SELECT * FROM USER_ACCOUNT";
 
				Statement statement = conn.createStatement();
				ResultSet result = statement.executeQuery(sql);
 
				int count = 0;
 
				while (result.next())
				{
    					String Email = result.getString(2);
    					String LastName = result.getString(3);
    					String FirstName = result.getString(4);
    					String Phone = result.getString(5);
    					String StreetAddress = result.getString(6);
    					String City = result.getString(7);
    					String State = result.getString(8);
    					String Zip = result.getString(9);
 
    					String output = "User #%d:\n %s - %s - %s - %s - %s - %s - %s - %s";
    					System.out.println(String.format(output, ++count, Email, FirstName, LastName, Phone, StreetAddress, City, State, Zip));
				}
			}
		} catch (SQLException ex) 
		{
			Logger lgr = Logger.getLogger(Facebook2.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
    			ex.printStackTrace();
		}
	}
	public static void update() throws SQLException
	{
		String url = "jdbc:mysql://deltona.birdnest.org:3306/my_espositoa2_Facebook";
        	String user = "my.espositoa2";
        	String password = "4s07$8v55";
        	try 
		{ 
    			Connection conn = DriverManager.getConnection(url, user, password);
 
    			if (conn != null) 
			{

		String update = "", userEmail = "", col = "", value = "";
        	Boolean str = true, done = false;
    		char phone[] = new char[13];
        	System.out.println("What is the users email?");
        	Scanner input = new Scanner(System.in);
        	String in = input.nextLine();
        	userEmail = in;
        	System.out.println("What field would you like to change?");
        	System.out.println("Choose a number: \n1:Email\n2:First Name\n3:Last Name\n4:Phone\n5:Street Address\n6:City\n7:State\n8:Zip Code");
    
    		input = new Scanner(System.in);
        	in = input.nextLine();
           	switch(in)
            	{
                	case "1":
                     		col = "Email";
                     		break;
                 	case "2":
                     		col = "FirstName";
                     		break;  
                 	case "3":
                     		col = "LastName";
                     		str = false;
                     		break; 
                 	case "4":
                     		col = "Phone";
                     		break;
                 	case "5":
                     		col = "StreetAddress";
                     		break;
                 	case "6":
        	    		col = "City";
         	    		break;
                 	case "7":
                     		col = "State";
                     		str = false;
                     		break;
                 	case "8":
                     		col = "Zip";
                     		break;
            	}       
        	System.out.println("What would you like to change the value too?");
        	input = new Scanner(System.in);
        	in = input.nextLine();
        	value = in;
		if(col.equals("Phone"))
        	{
            		phone = in.toCharArray();
            		value = "";
            		for(int number = 0; number < 12; number++)
            		{
                		char check = phone[number];
                		if(check != '-')
                		{
                    			value = value + check;                          
                		}
			}
			
		}

              		update = "UPDATE USER_ACCOUNT SET " + col + " = ? WHERE Email = ?";
			PreparedStatement statement = conn.prepareStatement(update);
			statement.setString(1, value);
			statement.setString(2, userEmail);
			
			int rowsUpdated = statement.executeUpdate();
            		if(rowsUpdated > 0)
            		{
                		System.out.println("Update Complete");
            		}
           		else
            		{
                		System.out.println("Update Failed");
            		}
		}
		} catch (SQLException ex) 
		{
			Logger lgr = Logger.getLogger(Facebook2.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
    			ex.printStackTrace();
		}



			/*String sql = "UPDATE USER_ACCOUNT SET Email =?, FirstName =?, LastName=?, Phone =?, StreetAddress =?, City =?, State =?, Zip =?";
 
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, "123456789");
			statement.setString(2, "William Henry Bill Gates");
			statement.setString(3, "bill.gates@microsoft.com");
			statement.setString(4, "bill");
 
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
    			System.out.println("An existing user was updated successfully!");
			}*/
	}
	public static void delete() throws SQLException
	{
		String url = "jdbc:mysql://deltona.birdnest.org:3306/my_espositoa2_Facebook";
        	String user = "my.espositoa2";
        	String password = "4s07$8v55";
    		
        	try 
		{
 
    			Connection conn = DriverManager.getConnection(url, user, password);
 
    			if (conn != null) 
			{
        			System.out.println("Connected");
				String sql = "DELETE FROM USER_ACCOUNT WHERE Email=?";

				System.out.println("What is the email of the user that you would like to delete?");
	       			Scanner input = new Scanner(System.in);
        			String in = input.nextLine();

				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setString(1, in);
 
				int rowsDeleted = statement.executeUpdate();
				if (rowsDeleted > 0) 
				{
    					System.out.println("A user was deleted successfully!");
				}
    			}
		} catch (SQLException ex) 
		{
			Logger lgr = Logger.getLogger(Facebook2.class.getName());
                        lgr.log(Level.SEVERE, ex.getMessage(), ex);
    			ex.printStackTrace();
		}
	}
}
