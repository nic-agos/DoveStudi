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
			+ " CONSTRAINT `reviewer` FOREIGN KEY (`Reviewer`) REFERENCES `account` (`CF`) ON DELETE CASCADE ON UPDATE RESTRICT\r\n"
			+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
	
	private DBCreation() {
		
		createDatabase();
		
	}
	
	private static void createDatabase() {
		
		String driver = DRIVER_CLASS_NAME;
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			stmt.executeUpdate(CREATE_DATABASE_QUERY);
			
		}catch(ClassNotFoundException | SQLException e1 ) {
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
	
	private final void executeQuery(String query) {
		
		Connection conn = null;
		Statement stmt= null;
		
		try {
			
			conn = DBConnection.getInstanceConnection().getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public static synchronized DBCreation getInstanceCreation() {
		
		if (instance == null) {
			instance = new DBCreation();
		}

		return instance;
	}
	
	public static final void createTables() {
		
		try {
			DBCreation d = DBCreation.getInstanceCreation();
			
			d.executeQuery(CREATE_TABLE_ACCOUNT);
			d.executeQuery(CREATE_TABLE_PERSON);
			d.executeQuery(CREATE_TABLE_ROOMSPEC);
			d.executeQuery(CREATE_TABLE_ROOM);
			d.executeQuery(CREATE_TABLE_RESERVATION);
			d.executeQuery(CREATE_TABLE_REVIEW);
			d.executeQuery(CREATE_TABLE_GROUP);
			
			d.executeQuery("INSERT INTO `account` VALUES ('CSSNTN82L12A662C','Antonio','Cassano','antonio.cassano@gmail.com','beri','1982-07-12',7),"
					+ "('DLNFNC99S30H501B','Francesco','Dalena','kecco9939@gmail.com','pincopallo','1999-11-30',6),"
					+ "('GSTNCL99C23H501K','Niccolo','Agostinelli','niccolo.agostinelli@gmail.com','password','1999-03-23',6),"
					+ "('MGNFLV96L44B352G','Flavia','Magnoni','flavia.magnoni@gmail.com','asd','1996-07-04',6)");
			
			d.executeQuery("INSERT INTO `person` VALUES (1,'flaps496','University','Tor Vergata','MGNFLV96L44B352G',0,3.5),"
					+ "(2,'nicco2303','University','Tor Vergata','GSTNCL99C23H501K',3,3),"
					+ "(3,'Anarion','University','Tor Vergata','DLNFNC99S30H501B',2.5,0),"
					+ "(4,'fantantonio82','Elementary School','Ungaretti','CSSNTN82L12A662C',0,0)");
			
			d.executeQuery("INSERT INTO `group_a` VALUES (1,'Develop','DLNFNC99S30H501B',3,3),(2,'Develop','DLNFNC99S30H501B',3,1),(3,'Develop','DLNFNC99S30H501B',3,2)");
			
			d.executeQuery("INSERT INTO `roomspec` VALUES (1,'Stanza con ampio tavolo e connessione Wi-Fi disponibile','2021-02-22','16:00:00','20:00:00','00133'),(2,'Stanza silenziosa dotata di Wi-Fi e riscaldamento','2021-02-22','14:00:00','17:00:00','00012'),(3,'Stanza pulita e accogliente, climatizzata, con ampio spazio per tutti. Offro inoltre ripetizioni e aiuti per ragazzi delle superiori in Matematica, Fisica, Chimica e Scienze Biologiche','2021-02-22','10:00:00','16:00:00','00172'),(4,'Spazio enorme per talenti sprecati','2021-02-10','07:00:00','14:00:00','00161')");
			
			d.executeQuery("INSERT INTO `room` VALUES (1,'Stanza Niccolò','Via dei centauri 32',4,4,'GSTNCL99C23H501K',1),(2,'Stanza Flavia','via nomentana 410',3,3,'MGNFLV96L44B352G',2),(3,'Stanza Francesco','via Onorato Ardoino 67',10,10,'DLNFNC99S30H501B',3),(4,'Stanza di Antonio','Via bari 89',7,4,'CSSNTN82L12A662C',4)");
		
			d.executeQuery("INSERT INTO `reservation` VALUES (1,'GSTNCL99C23H501K',4,4,'2021-02-10','07:00:00','14:00:00'),(2,'MGNFLV96L44B352G',4,4,'2021-02-10','07:00:00','14:00:00'),(3,'DLNFNC99S30H501B',4,4,'2021-02-10','07:00:00','14:00:00')");
			
			d.executeQuery("INSERT INTO `review` VALUES (1,'Brava ragazza','DLNFNC99S30H501B',1,4,'Un po\\' fuori di testa','GUEST'),(2,'Pronto? Falco mi senti?','DLNFNC99S30H501B',2,3,'Dove sei? A casa di ciccio','GUEST'),(3,'Hey Francè','MGNFLV96L44B352G',3,3,'Rispondi ancora che voi','HOST'),(4,'Bravo ragazzo','MGNFLV96L44B352G',2,3,'in un brutto quartiere','HOST'),(5,'dopo si abbracciano','GSTNCL99C23H501K',3,2,'come a c\\'è posta per te','HOST'),(6,'Ma siamo sulla Nomentana','GSTNCL99C23H501K',1,3,' e ti sei addormentata','GUEST')");
			

		}catch(NullPointerException ne) {
			ne.printStackTrace();
		}
	}
}

