package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee", schema = "dbo")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private int emp_no;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "hire_date")
    private Date hireDate;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    private List<WorkingHistory> workingHistories = new ArrayList<>();


    public Employee(Date birthDate, String firstName, String lastName, Gender gender, Date hireDate) {
        if (validateName(firstName, lastName)) {
            this.birthDate = birthDate;
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.hireDate = hireDate;
        } else {
            throw new IllegalStateException("Invalid Information");
        }
    }

    public Employee() {
        super();
    }

    public int getEmp_no() {
        return emp_no;
    }

    public void setEmp_no(int emp_no) {
        this.emp_no = emp_no;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<WorkingHistory> getWorkingHistories() {
        return workingHistories;
    }

    public void setWorkingHistories(List<WorkingHistory> workingHistories) {
        this.workingHistories = workingHistories;
    }

    private boolean validateName(String firstName, String lastName) {
        if (firstName.length() > 50 && lastName.length() > 50) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_no=" + emp_no +
                ", birthDate=" + birthDate +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", hireDate=" + hireDate +
                '}';
    }
}
