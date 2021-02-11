package logic.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreation {
	
	private static final String USER = "root";
	private static final String PASS = "password";
	private static final String DB_URL = "jdbc:mysql://localhost:3306?useSSL=false&allowPublicKeyRetrieval=true";
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	
	private static DBCreation instance = null;
	
	private static final String CREATE_DATABASE_QUERY = "CREATE SCHEMA IF NOT EXISTS `dovestudi` ;";
	
	private static final String CREATE_TABLE_ACCOUNT = "CREATE TABLE IF NOT EXISTS `dovestudi`.`account` (\r\n" 
			+ " `CF` char(16) NOT NULL,\r\n" 
			+ " `Name` varchar(20) NOT NULL,\r\n"
			+ " `Surname` varchar(30) NOT NULL,\r\n"
			+ " `Email` varchar(100) NOT NULL,\r\n"
			+ " `Password` varchar(20) NOT NULL,\r\n"
			+ " `Date_Birth` date NOT NULL,\r\n"
			+ " `Number_Token` int NOT NULL,\r\n"
			+ " PRIMARY KEY (`CF`),\r\n"
			+ " UNIQUE KEY `Email_UNIQUE` (`Email`)\r\n"
			+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
			
	
	private static final String CREATE_TABLE_PERSON = "CREATE TABLE IF NOT EXISTS `dovestudi`.`person` (\r\n"
			+ " `ID` int NOT NULL AUTO_INCREMENT,\r\n"
			+ " `Username` varchar(15) NOT NULL,\r\n"
			+ " `Study_Grade` varchar(30) NOT NULL,\r\n"
			+ " `School` varchar(50) NOT NULL,\r\n"
			+ " `Account` char(16) NOT NULL,\r\n"
			+ " `Host_Rating` double NOT NULL,\r\n"
			+ " `Guest_Rating` double NOT NULL,\r\n"
			+ " PRIMARY KEY (`ID`),\r\n"
			+ " UNIQUE KEY `Username_UNIQUE` (`Username`),\r\n"
			+ " UNIQUE KEY `Account_UNIQUE` (`Account`),\r\n"
			+ " KEY `account_idx` (`Account`),\r\n"
			+ " CONSTRAINT `account` FOREIGN KEY (`Account`) REFERENCES `account` (`CF`) ON DELETE CASCADE ON UPDATE CASCADE\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	private static final String CREATE_TABLE_ROOM = "CREATE TABLE IF NOT EXISTS `dovestudi`.`room` (\r\n"
			+ " `ID` int NOT NULL AUTO_INCREMENT,\r\n"
			+ " `Name` varchar(45) NOT NULL,\r\n"
			+ " `Address` varchar(100) NOT NULL,\r\n"
			+ " `Num_Partecipants` int NOT NULL,\r\n"
			+ " `Num_Available_Seats` int NOT NULL,\r\n"
			+ " `Owner` char(16) NOT NULL,\r\n"
			+ " `Specification` int NOT NULL,\r\n"
			+ " PRIMARY KEY (`ID`),\r\n"
			+ " UNIQUE KEY `Name_UNIQUE` (`Name`),\r\n"
			+ " UNIQUE KEY `Specification_UNIQUE` (`Specification`),\r\n"
			+ " KEY `owner_idx` (`Owner`),\r\n"
			+ " KEY `specification_idx` (`Specification`),\r\n"
			+ " CONSTRAINT `owner` FOREIGN KEY (`Owner`) REFERENCES `account` (`CF`) ON DELETE CASCADE,\r\n"
			+ " CONSTRAINT `specification` FOREIGN KEY (`Specification`) REFERENCES `roomspec` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; ";
	
	private static final String CREATE_TABLE_ROOMSPEC = "CREATE TABLE IF NOT EXISTS `dovestudi`.`roomspec` (\r\n"
			+ " `ID` int NOT NULL AUTO_INCREMENT,\r\n"
			+ " `Description` varchar(300) NOT NULL,\r\n"
			+ " `Date` date NOT NULL,\r\n"
			+ " `Start_Time` time NOT NULL,\r\n"
			+ " `End_Time` time NOT NULL,\r\n"
			+ " `CAP` char(5) NOT NULL,\r\n"
			+ " PRIMARY KEY (`ID`)\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	private static final String CREATE_TABLE_RESERVATION = "CREATE TABLE IF NOT EXISTS `dovestudi`.`reservation` (\r\n"
			+ " `ID` int NOT NULL AUTO_INCREMENT,\r\n"
			+ " `Reserving_User` char(16) NOT NULL,\r\n"
			+ " `Room` int NOT NULL,\r\n"
			+ " `Room_Owner` int NOT NULL,\r\n"
			+ " `Date` date NOT NULL,\r\n"
			+ " `Start_Time` time NOT NULL,\r\n"
			+ " `End_Time` time NOT NULL,\r\n"
			+ " PRIMARY KEY (`ID`),\r\n"
			+ " KEY `reservingUser_idx` (`Reserving_User`),\r\n"
			+ " KEY `room_idx` (`Room`),\r\n"
			+ " KEY `roomOwner_idx` (`Room_Owner`),\r\n"
			+ " CONSTRAINT `reservingUser` FOREIGN KEY (`Reserving_User`) REFERENCES `account` (`CF`) ON DELETE CASCADE ON UPDATE RESTRICT,\r\n"
			+ " CONSTRAINT `room` FOREIGN KEY (`Room`) REFERENCES `room` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT,\r\n"
			+ " CONSTRAINT `roomOwner` FOREIGN KEY (`Room_Owner`) REFERENCES `person` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	private static final String CREATE_TABLE_GROUP = "CREATE TABLE IF NOT EXISTS `dovestudi`.`group_a` (\r\n"
			+ " `ID` int NOT NULL AUTO_INCREMENT,\r\n"
			+ " `Name` varchar(45) NOT NULL,\r\n"
			+ " `Admin` char(16) NOT NULL,\r\n"
			+ " `Num_Partecipants` int NOT NULL,\r\n"
			+ " `Partecipant` int NOT NULL,\r\n"
			+ " PRIMARY KEY (`ID`),\r\n"
			+ " UNIQUE KEY `name, admin, partecipant` (`Name`,`Admin`,`Partecipant`),\r\n"
			+ " KEY `admin_idx` (`Admin`) /*!80000 INVISIBLE */,\r\n"
			+ " KEY `partecipant_idx` (`Partecipant`),\r\n"
			+ " CONSTRAINT `admin` FOREIGN KEY (`Admin`) REFERENCES `account` (`CF`) ON DELETE CASCADE ON UPDATE RESTRICT,\r\n"
			+ " CONSTRAINT `partecipant` FOREIGN KEY (`Partecipant`) REFERENCES `person` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci; ";
	
	private static final String CREATE_TABLE_REVIEW = "CREATE TABLE IF NOT EXISTS `dovestudi`.`review` (\r\n" 
			+ " `ID` int NOT NULL AUTO_INCREMENT,\r\n"
			+ " `Title` varchar(45) NOT NULL,\r\n"
			+ " `Reviewer` char(16) NOT NULL,\r\n"
			+ " `Reviewed` int NOT NULL,\r\n"
			+ " `Rating` int NOT NULL,\r\n"
			+ " `Description` varchar(200) NOT NULL,\r\n"
			+ " `Tag` char(5) NOT NULL,\r\n"
			+ " PRIMARY KEY (`ID`),\r\n"
			+ " KEY `reviewer_idx` (`Reviewer`),\r\n"
			+ " KEY `reviewed_idx` (`Reviewed`),\r\n"
			+ " CONSTRAINT `reviewed` FOREIGN KEY (`Reviewed`) REFERENCES `person` (`ID`) ON DELETE CASCADE ON UPDATE RESTRICT,\r\n"
			+ " CONSTRAINT `reviewer` FOREIGN KEY (`Reviewer`) REFERENCES `account` (`CF`) ON DELETE RESTRICT ON UPDATE RESTRICT\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	private DBCreation() throws SQLException {
		
		createDatabase();
		
	}
	
	private static void createDatabase() throws SQLException {
		
		String driver = DRIVER_CLASS_NAME;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(CREATE_DATABASE_QUERY);
			
		}catch(ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			}catch(SQLException e2){
				e2.printStackTrace();
			}try {
				if (stmt != null) {
					stmt.close();
				}
			}catch(SQLException e3) {
				e3.printStackTrace();
			}
		}
	}
	
	private final void executeQuery(String query) throws SQLException {
		
		Connection conn = null;
		Statement stmt= null;
		
		try {
			
			conn = DBConnection.getInstanceConnection().getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		}finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public static synchronized DBCreation getInstanceCreation() throws SQLException {
		
		if (instance == null) {
			instance = new DBCreation();
		}

		return instance;
	}
	
	public static final void createTables() throws SQLException {
		
		DBCreation d = DBCreation.getInstanceCreation();
		
		d.executeQuery(CREATE_TABLE_ACCOUNT);
		d.executeQuery(CREATE_TABLE_PERSON);
		d.executeQuery(CREATE_TABLE_ROOMSPEC);
		d.executeQuery(CREATE_TABLE_ROOM);
		d.executeQuery(CREATE_TABLE_RESERVATION);
		d.executeQuery(CREATE_TABLE_REVIEW);
		d.executeQuery(CREATE_TABLE_GROUP);
		
	}
}

