package P1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


import org.apache.james.mime4j.field.datetime.DateTime;

public class BookDB2 {
	String url = "jdbc:mysql://ebus-xp.ckfadxypzblq.ap-southeast-1.rds.amazonaws.com:3306/QA_CTB_WS";
	String username = "ebusxpdb";
    String password = "ctbtesting!";
    
    public void insertBookingDetails(String operator,String source, String destination, String hashtext,String tripid,double fare,String ftime, int numberOfAvailableSeats,String currency,String Msource,String Mdestination) throws InterruptedException, InstantiationException,
	IllegalAccessException, ClassNotFoundException, SQLException, ParseException {
    	Class.forName("com.mysql.jdbc.Driver").newInstance();
    	try {
				Connection connection = DriverManager.getConnection(url, username, password);
				String query = " insert into t_selenium_trips_new(f_operator_name, f_source, f_destination,f_trip_depart_date_time,f_trip_arrival_date_time	,f_hash_key,f_trip_identifier,f_route_id,f_from_city_code,f_to_city_code, f_trip_number,f_adult_fare,f_child_fare,f_senior_fare,f_disabled_fare,f_currency, f_pickup_point_code,f_dropoff_point_code,f_empty_seats)"
		    	        + " values (?, ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		        Timestamp ts = Timestamp.valueOf(ftime);

			    
		    	      // create the mysql insert preparedstatement
		    	      java.sql.PreparedStatement preparedStmt = connection.prepareStatement(query);
		    	      preparedStmt.setString (1, operator);
		    	      preparedStmt.setString (2, Msource);
		    	      preparedStmt.setString   (3, Mdestination);
		    	      
		    	     preparedStmt.setTimestamp(4, ts);
		    	      preparedStmt.setDate(5,null);
		    	      preparedStmt.setString   (6, hashtext);
						preparedStmt.setString   (7, tripid);
						preparedStmt.setString   (8, null);
						preparedStmt.setString   (9, source);
						preparedStmt.setString   (10, destination);
						preparedStmt.setString   (11, null);
						preparedStmt.setDouble(12, fare);
						preparedStmt.setNull(13, Types.DOUBLE);
						preparedStmt.setNull(14, Types.DOUBLE);
						preparedStmt.setNull(15, Types.DOUBLE);
						preparedStmt.setString   (16,currency);
						preparedStmt.setString   (17, null);
						preparedStmt.setString   (18, null);
		    	      preparedStmt.setInt    (19, numberOfAvailableSeats);
				
			    	      // execute the preparedstatement f_child_fare
		    	     // f_senior_fare f_adult_fare
			    	      preparedStmt.execute();
				          connection.close();
				          System.out.println("done--------------------------");

    		} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    		}
    }
}
