/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

/**
 *
 * @author me-aydin
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author JuliaD
 */
public class DBBean {

    private Connection con;

    private Statement state;
    private ResultSet rs;
    //private ResultSetMetaData rsMeta;
    //private String db;
    private String table;
    private String driverName;
    private String driverPass;
    private String driverReg;
    private String demandName;
    private String demandAddress;
    private String demandDest;
    private String demandDate;
    private String demandTime;
    private int demandId;
    private int costFive;
    private int costTen;

    public int getCostFive() {
        return costFive;
    }

    public void setCostFive(int costFive) {
        this.costFive = costFive;
    }

    public int getCostTen() {
        return costTen;
    }

    public void setCostTen(int costTen) {
        this.costTen = costTen;
    }
    
    

    public int getDemandId() {
        return demandId;
    }

    public void setDemandId(int demandId) {
        this.demandId = demandId;
    }
    
    

    public String getDemandName() {
        return demandName;
    }

    public void setDemandName(String demandName) {
        this.demandName = demandName;
    }

    public String getDemandAddress() {
        return demandAddress;
    }

    public void setDemandAddress(String demandAddress) {
        this.demandAddress = demandAddress;
    }

    public String getDemandDest() {
        return demandDest;
    }

    public void setDemandDest(String demandDest) {
        this.demandDest = demandDest;
    }

    public String getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(String demandDate) {
        this.demandDate = demandDate;
    }

    public String getDemandTime() {
        return demandTime;
    }

    public void setDemandTime(String demandTime) {
        this.demandTime = demandTime;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverPass() {
        return driverPass;
    }

    public void setDriverPass(String driverPass) {
        this.driverPass = driverPass;
    }

    public String getDriverReg() {
        return driverReg;
    }

    public void setDriverReg(String driverReg) {
        this.driverReg = driverReg;
    }

    public DBBean() {
        //this.db = null;
        this.table = null;
    }

    public void setTable(String tbl) {
        this.table = tbl;
    }

    public void setCon(Connection cn) {
        this.con = cn;
    }
    /* 
     public void setDB(String db) {
     this.db = db;
     }
     */
    
    public void assignDriver() {
        
        int rc;
        int custId = 0;
        int defaultDistance = 4;
        String status = "";
        System.out.println("Assigning driver");
        try {
            state = con.createStatement();
            rs = state.executeQuery("SELECT Name, Destination, Date, Time, Status from demands where id = " + '"' 
                    + demandId + '"');
            
            if (rs.next()) {
                setDemandName(rs.getString(1));
                setDemandDest(rs.getString(2));
                setDemandDate(rs.getString(3));
                setDemandTime(rs.getString(4));
                status.equals(rs.getString(5));
            }
            else {
                return;  // demand doesn't exist
            }

            rs = state.executeQuery("SELECT id from customer where Name = " + '"' 
                    + demandName.trim() + '"');
            if (rs.next()) {
                custId = Integer.parseInt(rs.getString(1));
            }
            else {
                return; // customer doesn't exist
            }
            rc = state.executeUpdate("INSERT INTO journey (Destination, Distance, `Customer.id`,"
                    + "`Drivers.Registration`, Date, Time)"
                    + "VALUES ('"
                    + demandDest.trim() + "', '" + defaultDistance + "', '" 
                    + custId + "', '" + driverReg.trim() + "', '"  + demandDate.trim() + "', '" 
                    + demandTime.trim() + "')");
            
            // Update status to "Assigned"
            rc = state.executeUpdate("UPDATE demands SET Status = 'Assigned'"
                    + "WHERE id = " + demandId);
            
            System.out.println("demands updated");
            
            rs.close();
            state.close();
                    } catch (SQLException e) {
            System.err.println(" Assign driver Error: " + e);
        }
        
    }

    public void addDemand() {

        int rc;
        try {
            state = con.createStatement();
            rc = state.executeUpdate("INSERT INTO demands (Name, Address, Destination, Date, Time, Status)"
                    + "VALUES ('"
                    + demandName.trim() + "', '" + demandAddress.trim() + "', '" 
                    + demandDest.trim() + "', '" + demandDate.trim() + "', '" 
                    + demandTime.trim() + "', 'Outstanding')");
            // If the customer doesn't exist, add it
            rs = state.executeQuery("SELECT * from customer where Name = " + '"' + demandName.trim() + '"');
            
            if (rs.next()) {
               // customer exists, do nothing
            }
            else {
                rc = state.executeUpdate("INSERT INTO customer (Name, Address)"
                    + "VALUES ('"
                    + demandName.trim() + "', '" + demandAddress.trim() + "')");
            }

            state.close();

        } catch (SQLException e) {
            System.err.println(" Add Demand Error: " + e);
        }

    }
    
    public String getCustomers() {
      StringBuilder sb = new StringBuilder();
        //sb.append(db.trim()+" "+table.trim());
      // (1) get today's date
    Date today = Calendar.getInstance().getTime();
 
    // (2) create a date "formatter" (the date format we want)
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 
    // (3) create a new String using the date format we want
    String todaysDate = formatter.format(today);
    int trips = 0;
    int custId = 0;
    int i = 1;
    int j = 0;
    int custServed = 0;
    boolean found = false;
    int[] custArray = new int[1000];
    int dist = 0;
    int cost = 0;
    
        System.out.println("today = " + todaysDate);

        try {
            // You will need to explicitly load this driver in a web app
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
            state = con.createStatement();
            rs = state.executeQuery("select customer.Name, customer.Address, journey.Destination,"
                    + " journey.Distance, journey.Date, journey.Time "
                    + "FROM journey"
                    + " inner join "
                    + "customer on customer.id = journey.`Customer.id` "
                    + "where journey.Date = "
                    + "'" + todaysDate.trim() + "'");
            int cols = rs.getMetaData().getColumnCount();
            System.out.println("dbbean, in get cust cols = " + cols);
            //System.out.println("col4 = " + rs.getString(4));
            
            while (rs.next()) {
                for (i = 1; i <= cols; i++) {
                    
                
                    sb.append(rs.getString(i) + " ");
                }
                if (Integer.parseInt(rs.getString(4)) < 6) {
                    cost = cost + costFive;
                }
                else {
                    cost = cost + costTen;
                }
                
                //sb.append(rs.getString(1) + " " + rs.getString(2));
                sb.append(" Cost = " + cost + "\n<br>");
            }
            
                      
            
            rs.close();
            state.close();
        } catch (SQLException e) {
            System.err.println(" Get Cust list Error: " + e);
            sb.append("Get Cust List Error: " + e);

        }//try
        return sb.toString();  
    }
    
    public String getTurnover() {
      StringBuilder sb = new StringBuilder();
        //sb.append(db.trim()+" "+table.trim());
      // (1) get today's date
    Date today = Calendar.getInstance().getTime();
 
    // (2) create a date "formatter" (the date format we want)
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
 
    // (3) create a new String using the date format we want
    String todaysDate = formatter.format(today);
    int trips = 0;
    int custId = 0;
    int i = 1;
    int j = 0;
    int custServed = 0;
    boolean found = false;
    int[] custArray = new int[1000];
    int cost = 0;
    
        System.out.println("today = " + todaysDate);

        try {
            // You will need to explicitly load this driver in a web app
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
            state = con.createStatement();
            rs = state.executeQuery("SELECT `Customer.id` from journey WHERE Date = " 
                    + "'" + todaysDate.trim() + "'");
            int cols = rs.getMetaData().getColumnCount();
            System.out.println("dbbean, in Turnover");
            System.out.println("SELECT `Customer.id` from journey WHERE Date = "
                    + todaysDate.trim());
            while (rs.next()) {
                System.out.println("got journeys");
                trips++;
                System.out.println("trips = " + trips);
                custId = Integer.parseInt(rs.getString(i));
                i++;
                found = false;
                
                for (j = 0; j <= i; j++) {
                    if (custArray[j] == 0) {  // it's not in the array, so add a customer served
                        custArray[j] = custId;
                        custServed++;
                        j = i;
                }
                    if (custArray[j] == custId) { //it is in the array so already counted
                        j=i;
                    }
                    
                }
                //sb.append(rs.getString(1) + " " + rs.getString(2));
                               //System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
            
            // now work out the cost
            rs = state.executeQuery("SELECT Distance from journey WHERE Date = " 
                    + "'" + todaysDate.trim() + "'");
            i = 1;
            while (rs.next()) {
                if (Integer.parseInt(rs.getString(i)) < 6) {
                    cost = cost + costFive;
                }
                else {
                    cost = cost + costTen;
                }
                i++;
                
            }
            
            sb.append("Total Journeys today is: " + trips + " Total customers served is: " 
                        + custServed + " Turnover is " + cost + "\n<br>");
            
            rs.close();
            state.close();
        } catch (SQLException e) {
            System.err.println(" Get Turnover Error: " + e);
            sb.append("Get Turnover Error: " + e);

        }//try
        return sb.toString();  
    }

    public boolean getDriverLogin(String dPass) {
        boolean valid = false;
        try {
            state = con.createStatement();
            rs = state.executeQuery("SELECT * from drivers where password = " + '"' + dPass.trim() + '"');
            valid = rs.next();
            rs.close();
            state.close();
            return valid;
        } catch (SQLException e) {
            System.err.println(" Get Driver Error: " + e);
        }
        return false;
    }

    public void addDriver() {

        int rc;
        try {
            state = con.createStatement();
            rc = state.executeUpdate("INSERT INTO drivers (Registration, Name, password)"
                    + "VALUES ('"
                    + driverReg.trim() + "', '" + driverName.trim() + "', '" + driverPass.trim()
                    + "')");

            state.close();

        } catch (SQLException e) {
            System.err.println(" Add Driver Error: " + e);
        }

    }

    public void delDriver() {

        int rc;
        try {

            System.out.println("dname = " + driverName.trim());

            state = con.createStatement();
            rc = state.executeUpdate("DELETE FROM drivers WHERE Name ="
                    + "'" + driverName.trim() + "'");

            state.close();

        } catch (SQLException e) {
            System.err.println(" Del Driver Error: " + e);
        }

    }

    public String getDriverJobs(String dName) {
        StringBuilder sb = new StringBuilder();
        String driverReg = "";
        //sb.append(db.trim()+" "+table.trim());

        try {
            // You will need to explicitly load this driver in a web app
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
            state = con.createStatement();
            rs = state.executeQuery("select drivers.Registration FROM drivers WHERE drivers.Name = '"
                    + dName.trim() + "'");
            if (rs.next()) {
                driverReg = rs.getString(1);
            }
            rs = state.executeQuery("select customer.Name, customer.Address, journey.Destination,"
                    + " journey.Distance, journey.Date, journey.Time "
                    + "FROM journey"
                    + " inner join "
                    + "customer on customer.id = journey.`Customer.id` "
                    + "where journey.`Drivers.Registration` = '"
                    + driverReg.trim() + "'");
            System.out.println("dbbean");
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    sb.append(rs.getString(i) + " ");
                }
                //sb.append(rs.getString(1) + " " + rs.getString(2));
                sb.append("\n<br>");
                //System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
            rs.close();
            state.close();
        } catch (SQLException e) {
            System.err.println(" Get Results Error: " + e);
            sb.append("Get Results Error: " + e);

        }//try
        return sb.toString();
    }

    public String getResults() {
        StringBuilder sb = new StringBuilder();
        //sb.append(db.trim()+" "+table.trim());

        try {
            // You will need to explicitly load this driver in a web app
            //Class.forName("com.mysql.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db.trim(), "root", "");
            state = con.createStatement();
            rs = state.executeQuery("SELECT * from " + table.trim());
            int cols = rs.getMetaData().getColumnCount();
            System.out.println("dbbean 2");
            while (rs.next()) {
                for (int i = 1; i <= cols; i++) {
                    sb.append(rs.getString(i) + " ");
                }
                //sb.append(rs.getString(1) + " " + rs.getString(2));
                sb.append("\n<br>");
                //System.out.println(rs.getString(1) + " " + rs.getString(2));
            }
            rs.close();
            state.close();
        } catch (SQLException e) {
            System.err.println(" Get Results Error: " + e);
            sb.append("Get Results Error: " + e);

        }//try
        //return "hairy badger";
        return sb.toString();
    }
//  public static void main(String[] str){
//      DBBean db = new DBBean("MyDB", "MyTable");
//      System.out.println(db.doQuery(""));
//  }
} //class

