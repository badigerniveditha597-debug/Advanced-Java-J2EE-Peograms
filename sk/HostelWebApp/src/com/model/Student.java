package com.model;

/**
 * Student model class representing a hostel student record.
 * Maps to the 'students' table in the database.
 */
public class Student {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String roomNumber;
    private String course;
    private String year;
    private String address;

    // Default constructor
    public Student() {}

    // Constructor with basic fields
    public Student(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Full constructor
    public Student(int id, String name, String email, String phone,
                   String roomNumber, String course, String year, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.course = course;
        this.year = year;
        this.address = address;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', email='" + email +
               "', phone='" + phone + "', room='" + roomNumber + "', course='" + course +
               "', year='" + year + "'}";
    }
}
