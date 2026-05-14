package com.dao;

import com.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HostelDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/hosteldb";
    private String jdbcUsername = "root";
    private String jdbcPassword = "root123456789";

    private static final String INSERT_STUDENT =
        "INSERT INTO HostelStudents (StudentName, RoomNumber, AdmissionDate, FeesPaid, PendingFees) VALUES (?, ?, ?, ?, ?)";
    private static final String INSERT_STUDENT_WITH_ID =
        "INSERT INTO HostelStudents (StudentID, StudentName, RoomNumber, AdmissionDate, FeesPaid, PendingFees) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_MAX_ID =
        "SELECT MAX(StudentID) FROM HostelStudents";
    private static final String SELECT_ALL_STUDENTS =
        "SELECT * FROM HostelStudents ORDER BY StudentID";
    private static final String DELETE_STUDENT =
        "DELETE FROM HostelStudents WHERE StudentID = ?";
    private static final String UPDATE_STUDENT =
        "UPDATE HostelStudents SET StudentName = ?, RoomNumber = ?, AdmissionDate = ?, FeesPaid = ?, PendingFees = ? WHERE StudentID = ?";
    private static final String SELECT_STUDENT_BY_ID =
        "SELECT * FROM HostelStudents WHERE StudentID = ?";
    private static final String SELECT_STUDENTS_BY_ROOM =
        "SELECT * FROM HostelStudents WHERE RoomNumber = ?";
    private static final String SELECT_STUDENTS_BY_DATE_RANGE =
        "SELECT * FROM HostelStudents WHERE AdmissionDate BETWEEN ? AND ?";
    private static final String SELECT_STUDENTS_PENDING_FEES =
        "SELECT * FROM HostelStudents WHERE PendingFees > 0";

    public HostelDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertStudent(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT)) {
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getRoomNumber());
            preparedStatement.setDate(3, student.getAdmissionDate());
            preparedStatement.setDouble(4, student.getFeesPaid());
            preparedStatement.setDouble(5, student.getPendingFees());
            preparedStatement.executeUpdate();
        }
    }

    public void insertStudentWithID(Student student) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_WITH_ID)) {
            preparedStatement.setInt(1, student.getStudentID());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setString(3, student.getRoomNumber());
            preparedStatement.setDate(4, student.getAdmissionDate());
            preparedStatement.setDouble(5, student.getFeesPaid());
            preparedStatement.setDouble(6, student.getPendingFees());
            preparedStatement.executeUpdate();
        }
    }

    public int getNextStudentID() {
        int nextId = 1;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MAX_ID)) {
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                nextId = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nextId;
    }

    public List<Student> selectAllStudents() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean deleteStudent(int studentID) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT)) {
            statement.setInt(1, studentID);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT)) {
            statement.setString(1, student.getStudentName());
            statement.setString(2, student.getRoomNumber());
            statement.setDate(3, student.getAdmissionDate());
            statement.setDouble(4, student.getFeesPaid());
            statement.setDouble(5, student.getPendingFees());
            statement.setInt(6, student.getStudentID());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public Student selectStudent(int studentID) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, studentID);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                student = extractStudent(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    public List<Student> selectStudentsByRoom(String roomNumber) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_ROOM)) {
            preparedStatement.setString(1, roomNumber);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> selectStudentsByDateRange(Date fromDate, Date toDate) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_DATE_RANGE)) {
            preparedStatement.setDate(1, fromDate);
            preparedStatement.setDate(2, toDate);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public List<Student> selectStudentsPendingFees() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_PENDING_FEES)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                students.add(extractStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private Student extractStudent(ResultSet rs) throws SQLException {
        int studentID = rs.getInt("StudentID");
        String name = rs.getString("StudentName");
        String room = rs.getString("RoomNumber");
        Date admissionDate = rs.getDate("AdmissionDate");
        double feesPaid = rs.getDouble("FeesPaid");
        double pendingFees = rs.getDouble("PendingFees");
        return new Student(studentID, name, room, admissionDate, feesPaid, pendingFees);
    }
}
