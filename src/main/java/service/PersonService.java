package service;

import java.sql.*;
import java.util.ArrayList;
import jdbc.DBConnector;
import model.Person;

public class PersonService {	
	//private static ArrayList<Person> persons = new ArrayList<>();
	public static void insert(Person person){
		//persons.add(person);
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBConnector.getConnection();
			String sqlInsert = "INSERT INTO persons(uuid, vorname, nachname, geburtsdatum) VALUES(?, ?, ?, ?)";
			preparedStatement = connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, person.getUUID());
			preparedStatement.setString(2, person.getVorname());
			preparedStatement.setString(3, person.getNachname());
			preparedStatement.setDate(4, Date.valueOf(person.getGeburtsdatum()));
			
			int rowCount = preparedStatement.executeUpdate();
			
			if(rowCount == 1) {
				connection.commit();
				System.out.println("Wert erfolgreich hinzugefügt.");
			}else {
				connection.rollback();
				System.out.println("Wert nicht erfolgreich hinzugefügt.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeResources(connection, preparedStatement);
		}
	}
	
	public static void delete(String uuid) {
		/*
		for(Person person : persons) {
			if(person.getUUID().equals(uuid)) {
				persons.remove(person);
				break;
			}
		}
		*/
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = DBConnector.getConnection();
			String sqlDelete = "DELETE FROM persons WHERE uuid = ?;";
			preparedStatement = connection.prepareStatement(sqlDelete);
			preparedStatement.setString(1, uuid);
			
			int rowCount = preparedStatement.executeUpdate();
			if(rowCount == 1) {
				connection.commit();
				System.out.println("Wert erfolgreich entfernt.");
			}else {
				connection.rollback();
				System.out.println("Wert nicht erfolgreich entfernt.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeResources(connection, preparedStatement);
		}
	}
	
	public static void update(String uuid, Person person) {
		/*
		for(Person p : persons) {
			if(p.getUUID().equals(uuid)) {
				int index = persons.indexOf(p);
				persons.set(index, person);
				break;
			}
		}
		*/
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = DBConnector.getConnection();
			String sqlUpdate = "UPDATE persons SET vorname = ?, nachname = ?, geburtsdatum = ? WHERE uuid = ?";
			preparedStatement = connection.prepareStatement(sqlUpdate);
			preparedStatement.setString(1, person.getVorname());
			preparedStatement.setString(2, person.getNachname());
			preparedStatement.setDate(3, Date.valueOf(person.getGeburtsdatum()));
			preparedStatement.setString(4, uuid);
			
			int rowCount = preparedStatement.executeUpdate();
			if(rowCount == 1) {
				connection.commit();
				System.out.println("Wert erfolgreich aktualisiert.");
			}else {
				connection.rollback();
				System.out.println("Wert nicht erfolgreich aktualisiert.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeResources(connection, preparedStatement);
		}

	}
	
	public static ArrayList<Person> getPersons(String method){
		//return persons;
		ArrayList<Person> persons = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String sqlSelect = "SELECT * FROM persons";
		try {
			connection = DBConnector.getConnection();
			if(method != null) {
				if(method.equals("datumAufsteigend")) {
					sqlSelect = "SELECT * FROM persons ORDER BY geburtsdatum ASC";
				}else if(method.equals("datumAbsteigend")) {
					sqlSelect = "SELECT * FROM persons ORDER BY geburtsdatum DESC";
				}
			}
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlSelect);
			
			while(resultSet.next()) {
				Person person = new Person(
						resultSet.getString("uuid"),
						resultSet.getString("vorname"),
						resultSet.getString("nachname"),
						resultSet.getDate("geburtsdatum").toLocalDate()
				);
				persons.add(person);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnector.closeResources(connection, statement, resultSet);
		}
		return persons;
	}
}
