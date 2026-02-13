package jdbc;

import java.sql.*;

public class DBConnector {
	private static String jdbcUrl = "jdbc:mysql://localhost:3306/usermanagement";
    private static String user = "root";
    private static String password = "hello12345";

    private static Connection connection;

    public static Connection getConnection(){
        try{
            /*
            if(connection != null){
                return connection;
            }
            */
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl, user, password);
            connection.setAutoCommit(false);
            return connection;
        }catch(ClassNotFoundException e) {
        	e.printStackTrace();
        	return null;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    // Methode, um die Verbindung und die Resoourcen zu schliessen, wenn sie nicht null sind.
    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        try{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Methode, um die Verbindung und die Ressourcen zu schliessen, wenn sie nicht null sind, hier überladen also ohne Resultset als Parameter.
    public static void closeResources(Connection connection, PreparedStatement preparedStatement){
        try{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void closeResources(Connection connection, Statement statement, ResultSet resultSet){
        try{
            if(connection != null){
                connection.close();
            }
            if(statement != null){
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
