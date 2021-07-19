package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.student;


public class Studentdao
{
	private String jdbcURL="jdbc:mysql://localhost:3309/sinchana?studentSSL=false";
	private String jdbcstudent="root";
	private String jdbcpassword="1234";

	private static final String inqry="insert into sinchana.student values('?','?','?')";
	private static final String selusn="select * from sinchana.student where usn=?";
    private static final String selall = "select * from sinchana.student";
    private static final String delqry = "delete from sinchana.student where usn = ?";
    private static final String upqry = "update sinchana.student set name = ?, branch=?, collge=? where usn= ?";
    public Studentdao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcstudent, jdbcpassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertstudent(student student) throws SQLException {
        System.out.println(inqry);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(inqry);) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getBranch());
            preparedStatement.setString(3, student.getCollege());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();;
        }
    }

    public student selectstudent(int usn) {
        student student = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(selusn);) {
            preparedStatement.setInt(1, usn);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                student = new student(usn, name, email, country);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    public List < student > selectAllstudents() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < student > student = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(selall);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String country = rs.getString("country");
                student.add(new student(id, name, email, country));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return student;
    }

    public boolean deletestudent(int usn) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(delqry);) {
            statement.setInt(1, usn);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updatestudent(student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(upqry);) {
            statement.setString(1, student.getName());
            statement.setString(2, student.getBranch());
            statement.setString(3, student.getCollege());
            statement.setInt(4, student.getUsn());
rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }}