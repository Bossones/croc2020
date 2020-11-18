package ru.croc.coder.domain.users;

import ru.croc.coder.domain.tasks.Course;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity(name = "User")
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "USER_COURSE_LINK",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_ID")
    )
    private Set<Course> courses = new HashSet<>();

    public User setCourses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public User setOneCourse(Course course) {
        courses.add(course);
        return this;
    }

    private Integer attemptsCount = 0;

    public Set<Course> getCourses() {
        return courses;
    }

    public Integer getAttemptsCount() {
        return attemptsCount;
    }

    public User setAttemptsCount(Integer attemptsCount) {
        this.attemptsCount = attemptsCount;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        Objects.requireNonNull(id);
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        Objects.requireNonNull(email);
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
