package com.dao;

import com.model.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Hostel Management.
 * Handles all CRUD operations and report queries against the MySQL database.
 */
public class HostelDAO {

    // JDBC connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/hosteldb";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    // SQL Statements
    private static final String INSERT_STUDENT =
        "INSERT INTO students (name, email, phone, room_number, course, year, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID =
        "SELECT * FROM students WHERE id = ?";
    private static final String SELECT_ALL_STUDENTS =
        "SELECT * FROM students ORDER BY id";
    private static final String UPDATE_STUDENT =
        "UPDATE students SET name=?, email=?, phone=?, room_number=?, course=?, year=?, address=? WHERE id=?";
    private static final String DELETE_STUDENT =
        "DELETE FROM students WHERE id = ?";

    // Report queries
    private static final String SELECT_BY_COURSE =
        "SELECT * FROM students WHERE course = ? ORDER BY name";
    private static final String SELECT_BY_YEAR =
        "SELECT * FROM students WHERE year = ? ORDER BY name";
    private static final String SELECT_BY_ROOM =
        "SELECT * FROM students WHERE room_number = ? ORDER BY name";
    private static final String COUNT_BY_COURSE =
        "SELECT course, COUNT(*) as total FROM students GROUP BY course ORDER BY course";
    private static final String COUNT_BY_YEAR =
        "SELECT year, COUNT(*) as total FROM students GROUP BY year ORDER BY year";

    /**
     * Load the MySQL JDBC driver.
     */
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found.", e);
        }
    }

    /**
     * Get a database connection.
     */
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // ==================== CRUD Operations ====================

    /**
     * Insert a new student into the database.
     */
    public void insertStudent(Student student) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getRoomNumber());
            ps.setString(5, student.getCourse());
            ps.setString(6, student.getYear());
            ps.setString(7, student.getAddress());
            ps.executeUpdate();
        }
    }

    /**
     * Select a student by their ID.
     */
    public Student selectStudentById(int id) throws SQLException {
        Student student = null;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_STUDENT_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                student = mapResultSetToStudent(rs);
            }
        }
        return student;
    }

    /**
     * Select all students from the database.
     */
    public List<Student> selectAllStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_STUDENTS)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }
        return students;
    }

    /**
     * Update an existing student record.
     */
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_STUDENT)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getEmail());
            ps.setString(3, student.getPhone());
            ps.setString(4, student.getRoomNumber());
            ps.setString(5, student.getCourse());
            ps.setString(6, student.getYear());
            ps.setString(7, student.getAddress());
            ps.setInt(8, student.getId());
            rowUpdated = ps.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    /**
     * Delete a student by their ID.
     */
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_STUDENT)) {
            ps.setInt(1, id);
            rowDeleted = ps.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    // ==================== Report Operations ====================

    /**
     * Get students filtered by course.
     */
    public List<Student> getStudentsByCourse(String course) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_COURSE)) {
            ps.setString(1, course);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }
        return students;
    }

    /**
     * Get students filtered by year.
     */
    public List<Student> getStudentsByYear(String year) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_YEAR)) {
            ps.setString(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }
        return students;
    }

    /**
     * Get students filtered by room number.
     */
    public List<Student> getStudentsByRoom(String roomNumber) throws SQLException {
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_ROOM)) {
            ps.setString(1, roomNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }
        return students;
    }

    /**
     * Get student count grouped by course.
     */
    public List<String[]> getCountByCourse() throws SQLException {
        List<String[]> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(COUNT_BY_COURSE)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new String[]{rs.getString("course"), String.valueOf(rs.getInt("total"))});
            }
        }
        return results;
    }

    /**
     * Get student count grouped by year.
     */
    public List<String[]> getCountByYear() throws SQLException {
        List<String[]> results = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(COUNT_BY_YEAR)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new String[]{rs.getString("year"), String.valueOf(rs.getInt("total"))});
            }
        }
        return results;
    }

    /**
     * Get total student count.
     */
    public int getTotalStudentCount() throws SQLException {
        int count = 0;
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) as total FROM students")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        }
        return count;
    }

    // ==================== Helper Methods ====================

    /**
     * Map a ResultSet row to a Student object.
     */
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String roomNumber = rs.getString("room_number");
        String course = rs.getString("course");
        String year = rs.getString("year");
        String address = rs.getString("address");
        return new Student(id, name, email, phone, roomNumber, course, year, address);
    }
}
