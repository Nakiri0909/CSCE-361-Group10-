package com.ceg.ext;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ceg.ext.dataContainer.*;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 15 methods in total, add more if required.
 * Donot change any method signatures or the package name.
 * 
 */

public class InvoiceData {

	private static Connection connect = null;
    private static Statement statement = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;	
	
    private static void open() throws SQLException, ClassNotFoundException{
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");

        String url = "jdbc:mysql://127.0.0.1:3366/invoice_db?"
                + "user=root&password=123&useUnicode=true&characterEncoding=UTF8";

        // Setup the connection with the DB
        connect = DriverManager.getConnection(url);
    }
    private static void close() {
        try {
                if (resultSet != null) {
                        resultSet.close();
                }

                if (statement != null) {
                        statement.close();
                }

                if (connect != null) {
                        connect.close();
                }
        } catch (Exception e) {

        }
    }
	
	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		try{
			open();
			connect.setAutoCommit(false);
			
			String sql1 = "delete from PersonAddress";
			String sql2 = "delete from Email";
			String sql3 = "delete from ProductList";
			String sql4 = "delete from Invoice";
			String sql5 = "delete from CustomerAddress";
			String sql6 = "delete from Customer";
			String sql7 = "delete from Person";
			preparedStatement = connect.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql3);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql4);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql5);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql6);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql7);
			preparedStatement.executeUpdate();
			connect.commit();
			
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
			
		}
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		try{
			open();
			connect.setAutoCommit(false);
			String sql = "select max(personid) as maxid from person";
			preparedStatement = connect.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			int curid=0;
			if(resultSet.next()){
				curid = resultSet.getInt("maxid")+1;
			}else{
				curid = 1;
			}
			System.out.println("curid = "+curid);
			
			
			preparedStatement = connect
                    .prepareStatement("insert into  person values (?, ?, ?, ?)");
			preparedStatement.setInt(1, curid);
			
			preparedStatement.setString(2, personCode);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			
			preparedStatement.executeUpdate();
			
			preparedStatement = connect
                    .prepareStatement("insert into  personAddress values (?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, curid);
			preparedStatement.setString(2, street);
			preparedStatement.setString(3, city);
			preparedStatement.setString(4, state);
			preparedStatement.setString(5, zip);
			preparedStatement.setString(6, country);
			
			preparedStatement.executeUpdate();

			connect.commit();
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
		}
	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {
	
		try{
			
			open();
			// get personID
			preparedStatement = connect.prepareStatement("select personid from person where personcode='"+personCode+"'");
			resultSet = preparedStatement.executeQuery();
			int personid=0;
			if(resultSet.next()){
				personid = resultSet.getInt("personid");
			}else{
				System.out.println("no such person.");
				return;
			}
			System.out.println("personid = "+personid);
			
			// get EmailID
			preparedStatement = connect.prepareStatement("select max(emailid) as maxid from email where personid="+personid);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int emailid=resultSet.getInt("maxid")+1;
			System.out.println("emailid = "+emailid);
			
			// insert Email
			preparedStatement = connect.prepareStatement("insert into email values(?,?,?)");
			preparedStatement.setInt(1, emailid);
			preparedStatement.setInt(2, personid);
			preparedStatement.setString(3, email);
			preparedStatement.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

	/**
	 * 4. Method that removes every customer record from the database
	 */
	public static void removeAllCustomers() {
		try{
			open();
			connect.setAutoCommit(false);
			
			String sql1 = "delete from ProductList";
			String sql2 = "delete from Invoice";
			String sql3 = "delete from CustomerAddress";
			String sql4 = "delete from Customer";
			preparedStatement = connect.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql3);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql4);
			preparedStatement.executeUpdate();
			connect.commit();
		
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
		}
	}

	public static void addCustomer(String customerCode, String customerType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		try{
			open();
			connect.setAutoCommit(false);
			// get person id
			preparedStatement = connect.prepareStatement("select personid from person where personcode='"+primaryContactPersonCode+"'");
			resultSet = preparedStatement.executeQuery();
			int personid=0;
			if(resultSet.next()){
				personid = resultSet.getInt("personid");
			}else{
				System.out.println("no such person.");
				return;
			}
			System.out.println("personid = "+personid);
			
			// get a free customer id
			preparedStatement = connect.prepareStatement("select max(customerid) as maxid from customer");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int customerid=resultSet.getInt("maxid")+1;
			System.out.println("customerid = "+customerid);
			
			// insert customer table
			preparedStatement = connect.prepareStatement("insert into customer values (?,?,?,?,?,?)");
			preparedStatement.setInt(1, customerid);
			preparedStatement.setInt(2, personid);
			preparedStatement.setString(3, customerCode);
			preparedStatement.setString(4, customerType);
			preparedStatement.setString(5, primaryContactPersonCode);
			preparedStatement.setString(6, name);
			preparedStatement.executeUpdate();
			
			// insert customer address table
			preparedStatement = connect.prepareStatement("insert into customeraddress values (?,?,?,?,?,?)");
			preparedStatement.setInt(1, customerid);
			preparedStatement.setString(2, street);
			preparedStatement.setString(3, city);
			preparedStatement.setString(4, state);
			preparedStatement.setString(5, zip);
			preparedStatement.setString(6, country);
			preparedStatement.executeUpdate();
			connect.commit();
			
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
		}
	}
	
	/**
	 * 5. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		
		try{
			open();
			connect.setAutoCommit(false);
			
			String sql1 = "delete from seasonpass";
			String sql2 = "delete from productlist";
			String sql3 = "delete from refreshment";
			String sql4 = "delete from movieticketaddress";
			String sql5 = "delete from movieticket";
			String sql6 = "delete from pakingpass";
			String sql7 = "delete from product";
			preparedStatement = connect.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql3);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql4);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql5);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql6);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql7);
			preparedStatement.executeUpdate();
			connect.commit();
		
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
		}
	}

	/**
	 * 6. Adds an movieTicket record to the database with the provided data.
	 */
	public static void addMovieTicket(String productCode, String dateTime, String movieName, String street, String city,String state, String zip, String country, String screenNo, double pricePerUnit) {
		try{
			open();
			connect.setAutoCommit(false);
			// get productid
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return ;
			}
			int productid = resultSet.getInt("productid");
			System.out.println("product id = "+productid);
			
			// get a free movieticket id
			preparedStatement = connect.prepareStatement("select max(movieticketid) as maxid from movieticket");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int movieticketid = resultSet.getInt("maxid")+1;
			System.out.println("movieticketid = "+movieticketid);
			
			// insert movieticket table
			preparedStatement = connect.prepareStatement("insert into movieticket values(?,?,?,?,?,?)");
			preparedStatement.setInt(1, movieticketid);
			preparedStatement.setInt(2, productid);
			preparedStatement.setString(3, movieName);
			preparedStatement.setString(4, dateTime);
			preparedStatement.setString(5, screenNo);
			preparedStatement.setDouble(6, pricePerUnit);
			preparedStatement.executeUpdate();
			
			// insert movieticketaddress table
			preparedStatement = connect.prepareStatement("insert into movieticketaddress values(?,?,?,?,?,?)");
			preparedStatement.setInt(1, movieticketid);
			preparedStatement.setString(2, street);
			preparedStatement.setString(3, city);
			preparedStatement.setString(4, state);
			preparedStatement.setString(5, zip);
			preparedStatement.setString(6, country);
			preparedStatement.executeUpdate();
			
			
			connect.commit();
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
		}
	}

	/**
	 * 7. Adds a seasonPass record to the database with the provided data.
	 */
	public static void addSeasonPass(String productCode, String name, String seasonStartDate, String seasonEndDate,	double cost) {
		
		try{
			open();
			// get productid
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return ;
			}
			int productid = resultSet.getInt("productid");
			System.out.println("product id = "+productid);
			
			// get a free seasonpass id
			preparedStatement = connect.prepareStatement("select max(seasonpassid) as maxid from seasonpass");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int seasonpassid = resultSet.getInt("maxid")+1;
			System.out.println("seasonpassid = "+seasonpassid);
			
			// insert seasonpass table
			preparedStatement = connect.prepareStatement("insert into seasonpass values(?,?,?,?,?,?)");
			preparedStatement.setInt(1, seasonpassid);
			preparedStatement.setInt(2, productid);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, seasonStartDate);
			preparedStatement.setString(5, seasonEndDate);
			preparedStatement.setDouble(6, cost);
			preparedStatement.executeUpdate();
			
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

	/**
	 * 8. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		try{
			open();
			// get productid
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return ;
			}
			int productid = resultSet.getInt("productid");
			System.out.println("product id = "+productid);
			
			// get a free pakingpass id
			preparedStatement = connect.prepareStatement("select max(pakingpassid) as maxid from pakingpass");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int pakingpassid = resultSet.getInt("maxid")+1;
			System.out.println("pakingpassid = "+pakingpassid);
			
			// insert pakingpass table
			preparedStatement = connect.prepareStatement("insert into pakingpass values(?,?,?)");
			preparedStatement.setInt(1, pakingpassid);
			preparedStatement.setInt(2, productid);
			preparedStatement.setDouble(3, parkingFee);
			preparedStatement.executeUpdate();
			
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
		
	}

	/**
	 * 9. Adds a refreshment record to the database with the provided data.
	 */
	public static void addRefreshment(String productCode, String name, double cost) {
		try{
			open();
			// get productid
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return ;
			}
			int productid = resultSet.getInt("productid");
			System.out.println("product id = "+productid);
			
			// get a free refreshment id
			preparedStatement = connect.prepareStatement("select max(refreshmentid) as maxid from refreshment");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int refreshmentid = resultSet.getInt("maxid")+1;
			System.out.println("refreshmentid = "+refreshmentid);
			
			// insert pakingpass table
			preparedStatement = connect.prepareStatement("insert into refreshment values(?,?,?,?)");
			preparedStatement.setInt(1, refreshmentid);
			preparedStatement.setInt(2, productid);
			preparedStatement.setString(3, name);
			preparedStatement.setDouble(4, cost);
			preparedStatement.executeUpdate();
			
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

	/**
	 * 10. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		try{
			open();
			connect.setAutoCommit(false);
			
			String sql1 = "delete from ProductList";
			String sql2 = "delete from Invoice";
			preparedStatement = connect.prepareStatement(sql1);
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(sql2);
			preparedStatement.executeUpdate();
			connect.commit();
			
		}catch(Exception e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connect.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			close();
			
		}

	}

	/**
	 * 11. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String customerCode, String salesPersonCode, String invoiceDate) {
		try{
			open();
			
			// get a free invoice id
			preparedStatement = connect.prepareStatement("select max(invoiceid) as maxid from invoice");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int invoiceid = resultSet.getInt("maxid")+1;
			
			// get customer id
			preparedStatement = connect.prepareStatement("select customerid from customer where customercode='"+customerCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such customer.");
				return;
			}
			int customerid = resultSet.getInt("customerid");
			
			// get person id
			preparedStatement = connect.prepareStatement("select personid from person where personcode='"+salesPersonCode+"'");
			resultSet= preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such person.");
				return;
			}
			int personid = resultSet.getInt("personid");

			// insert invoice table
			preparedStatement = connect.prepareStatement("insert into invoice values(?,?,?,?,?)");
			preparedStatement.setInt(1, invoiceid);
			preparedStatement.setString(2, invoiceCode);
			preparedStatement.setInt(3, personid);
			preparedStatement.setInt(4, customerid);
			preparedStatement.setString(5, invoiceDate);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

	/**
	 * 12. Adds a particular movieticket (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addMovieTicketToInvoice(String invoiceCode, String productCode, int quantity) {
		try{
			open();
			
			// get invoice id
			preparedStatement = connect.prepareStatement("select invoiceid from invoice where invoicecode='"+invoiceCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such invoice.");
				return;
			}
			int invoiceid = resultSet.getInt("invoiceid");
			
			// get product id
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return;
			}
			int productid = resultSet.getInt("productid");
			
			// get productlist id
			preparedStatement = connect.prepareStatement("select max(productlistid) as maxid from productlist where invoiceid="+invoiceid);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int productlistid = resultSet.getInt("maxid")+1;
			
			// insert productlist table
			preparedStatement = connect.prepareStatement("insert into productlist values(?,?,?,?)");
			preparedStatement.setInt(1, productlistid);
			preparedStatement.setInt(2, invoiceid);
			preparedStatement.setInt(3, productid);
			preparedStatement.setInt(4, quantity);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

	/*
	 * 13. Adds a particular seasonpass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addSeasonPassToInvoice(String invoiceCode, String productCode, int quantity) {
		try{
			open();
			
			// get invoice id
			preparedStatement = connect.prepareStatement("select invoiceid from invoice where invoicecode='"+invoiceCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such invoice.");
				return;
			}
			int invoiceid = resultSet.getInt("invoiceid");
			
			// get product id
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return;
			}
			int productid = resultSet.getInt("productid");
			
			// get productlist id
			preparedStatement = connect.prepareStatement("select max(productlistid) as maxid from productlist where invoiceid="+invoiceid);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int productlistid = resultSet.getInt("maxid")+1;
			
			// insert productlist table
			preparedStatement = connect.prepareStatement("insert into productlist values(?,?,?,?)");
			preparedStatement.setInt(1, productlistid);
			preparedStatement.setInt(2, invoiceid);
			preparedStatement.setInt(3, productid);
			preparedStatement.setInt(4, quantity);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
	}

     /**
     * 14. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity.
     * NOTE: ticketCode may be null
     */
    public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String ticketCode) {
		try{
			open();
			
			// get invoice id
			preparedStatement = connect.prepareStatement("select invoiceid from invoice where invoicecode='"+invoiceCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such invoice.");
				return;
			}
			int invoiceid = resultSet.getInt("invoiceid");
			
			// get product id
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return;
			}
			int productid = resultSet.getInt("productid");
			
			// get productlist id
			preparedStatement = connect.prepareStatement("select max(productlistid) as maxid from productlist where invoiceid="+invoiceid);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int productlistid = resultSet.getInt("maxid")+1;
			
			// insert productlist table
			preparedStatement = connect.prepareStatement("insert into productlist values(?,?,?,?)");
			preparedStatement.setInt(1, productlistid);
			preparedStatement.setInt(2, invoiceid);
			preparedStatement.setInt(3, productid);
			preparedStatement.setInt(4, quantity);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}
    	
    }
	
    /**
     * 15. Adds a particular refreshment (corresponding to <code>productCode</code> to an 
     * invoice corresponding to the provided <code>invoiceCode</code> with the given
     * number of quantity. 
     */
    public static void addRefreshmentToInvoice(String invoiceCode, String productCode, int quantity) {
		try{
			open();
			
			// get invoice id
			preparedStatement = connect.prepareStatement("select invoiceid from invoice where invoicecode='"+invoiceCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such invoice.");
				return;
			}
			int invoiceid = resultSet.getInt("invoiceid");
			
			// get product id
			preparedStatement = connect.prepareStatement("select productid from product where pcode='"+productCode+"'");
			resultSet = preparedStatement.executeQuery();
			if(!resultSet.next()){
				System.out.println("no such product.");
				return;
			}
			int productid = resultSet.getInt("productid");
			
			// get productlist id
			preparedStatement = connect.prepareStatement("select max(productlistid) as maxid from productlist where invoiceid="+invoiceid);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int productlistid = resultSet.getInt("maxid")+1;
			
			// insert productlist table
			preparedStatement = connect.prepareStatement("insert into productlist values(?,?,?,?)");
			preparedStatement.setInt(1, productlistid);
			preparedStatement.setInt(2, invoiceid);
			preparedStatement.setInt(3, productid);
			preparedStatement.setInt(4, quantity);
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			close();
		}

    }
    
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    
    public static ArrayList<Person> getAllPersons() throws Exception{
    	ArrayList<Person> res = new ArrayList<Person>();
    	try{
    		open();
    		preparedStatement = connect.prepareStatement("select * from person left join personaddress on person.personid=personaddress.personid");
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()){
    			int personid = resultSet.getInt("personid");
    			String personcode = resultSet.getString("personcode");
    			String firstname = resultSet.getString("firstname");
    			String lastname = resultSet.getString("lastname");
    			String street = resultSet.getString("street");
    			String city = resultSet.getString("city");
    			String state = resultSet.getString("state");
    			String zip = resultSet.getString("zip");
    			String country = resultSet.getString("country");
    			Address address = new Address(street,city,state,zip,country);
    			//System.out.println("country="+country);
    			Person p = new Person(personid,personcode,firstname,lastname,address );
    			res.add(p);
    		}
    		return res;
    	}catch(Exception e){
    		throw e;
    	}finally{
    		close();
    	}
    }
    
    public static Person getPersonById(int id) throws Exception{
    	try{
    		open();
    		preparedStatement = connect.prepareStatement(
    				"select * from person left join personaddress on person.personid=personaddress.personid where personid="+id);
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next()){
    			int personid = resultSet.getInt("personid");
    			String personcode = resultSet.getString("personcode");
    			String firstname = resultSet.getString("firstname");
    			String lastname = resultSet.getString("lastname");
    			String street = resultSet.getString("street");
    			String city = resultSet.getString("city");
    			String state = resultSet.getString("state");
    			String zip = resultSet.getString("zip");
    			String country = resultSet.getString("country");
    			Address address = new Address(street,city,state,zip,country);
    			System.out.println("country="+country);
    			Person p = new Person(personid,personcode,firstname,lastname,address );
    			return (p);
    		}else{
    			return null;
    		}
    		
    	}finally{
    		close();
    	}
    }

    public static ArrayList<Customer> getAllCustomers() throws Exception{
    	ArrayList<Customer> res = new ArrayList<Customer>();
    	try{
    		open();
    		preparedStatement = connect.prepareStatement(
    				"select * from customer left join customeraddress on customer.customerid=customeraddress.customerid");
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()){
    			int customerid = resultSet.getInt("customerid");
    			int personid = resultSet.getInt("personid");
    			String customercode = resultSet.getString("customercode");
    			String type = resultSet.getString("type");
    			String personcode = resultSet.getString("personcode");
    			String customername = resultSet.getString("customername");
    			
    			String street = resultSet.getString("street");
    			String city = resultSet.getString("city");
    			String state = resultSet.getString("state");
    			String zip = resultSet.getString("zip");
    			String country = resultSet.getString("country");
    			
    			Address address = new Address(street,city,state,zip,country);
    			
    			Customer c = new Customer(customerid, personid, customercode, type, personcode,customername,address );
    			res.add(c);
    		}
    		return res;
    	}finally{
    		close();
    	}
    }
    
    public static Customer getCustomerById(int id) throws ClassNotFoundException, SQLException{
    	try{
    		open();
    		preparedStatement = connect.prepareStatement(
    				"select * from customer left join customeraddress on customer.customerid=customeraddress.customerid");
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next()){
    			int customerid = resultSet.getInt("customerid");
    			int personid = resultSet.getInt("personid");
    			String customercode = resultSet.getString("customercode");
    			String type = resultSet.getString("type");
    			String personcode = resultSet.getString("personcode");
    			String customername = resultSet.getString("customername");
    			
    			String street = resultSet.getString("street");
    			String city = resultSet.getString("city");
    			String state = resultSet.getString("state");
    			String zip = resultSet.getString("zip");
    			String country = resultSet.getString("country");
    			
    			Address address = new Address(street,city,state,zip,country);
    			
    			Customer c = new Customer(customerid, personid, customercode, type, personcode,customername,address );
    			return (c);
    		}else
    			return null;
    	}finally{
    		close();
    	}
    }
    
    public static ArrayList<Invoice> getAllInvoices() throws ClassNotFoundException, SQLException{
    	ArrayList<Invoice> list = new ArrayList<Invoice>();
    	try{
    		open();
    		preparedStatement = connect.prepareStatement("select * from invoice");
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()){
    			int invoiceid = resultSet.getInt("invoiceid");
    			String invoicecode = resultSet.getString("invoicecode");
    			int personid = resultSet.getInt("personid");
    			int customerid = resultSet.getInt("customerid");
    			String invoicedate = resultSet.getString("invoicedate");
    			Invoice i = new Invoice(invoiceid,invoicecode,personid,customerid,invoicedate);
    			list.add(i);
    		}
    		return list;
    	}finally{
    		close();
    	}
    }
    

    public static Invoice getInvoiceById(int id) throws ClassNotFoundException, SQLException{
    	try{
    		open();
    		preparedStatement = connect.prepareStatement("select * from invoice where id="+id);
    		resultSet = preparedStatement.executeQuery();
    		if(resultSet.next()){
    			int invoiceid = resultSet.getInt("invoiceid");
    			String invoicecode = resultSet.getString("invoicecode");
    			int personid = resultSet.getInt("personid");
    			int customerid = resultSet.getInt("customerid");
    			String invoicedate = resultSet.getString("invoicedate");
    			Invoice i = new Invoice(invoiceid,invoicecode,personid,customerid,invoicedate);
    			return i;
    		}else{
    			return null;
    		}
    	}finally{
    		close();
    	}
    }

    public static ArrayList<Product> getAllProducts() throws ClassNotFoundException, SQLException{
    	ArrayList<Product> list = new ArrayList<Product>();
    	try{
    		open();
    		preparedStatement = connect.prepareStatement("select productid,pcode,ptype from product");
    		resultSet = preparedStatement.executeQuery();
    		while(resultSet.next()){
    			int productid = resultSet.getInt("productid");
    			String pcode = resultSet.getString("pcode");
    			String ptype = resultSet.getString("ptype");
    			Product p = new Product(productid,pcode,ptype);
    			list.add(p);
    		}
    		return list;
    	}finally{
    		close();
    	}
    }
    
    
}
