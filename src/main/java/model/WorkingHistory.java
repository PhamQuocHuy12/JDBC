package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "working_history", schema = "dbo")
public class WorkingHistory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "working_history_id")
    private int workingHistoryId;

    @ManyToOne
    @JoinColumn(name = "emp_no")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "dept_no")
    private Department department;

    @Column(name = "from_date")
    private Date fromDate;

    @Column(name = "to_date")
    private Date toDate;

    public WorkingHistory(Employee employee, Department department, Date fromDate, Date toDate) {
        if (validateDate(fromDate, toDate)) {
            this.employee = employee;
            this.department = department;
            this.fromDate = fromDate;
            this.toDate = toDate;
        } else {
            throw new IllegalStateException("Invalid Date");
        }

    }

    public WorkingHistory() {
    }

    public int getWorkingRecord() {
        return workingHistoryId;
    }

    public void setWorkingRecord(int workingRecord) {
        this.workingHistoryId = workingRecord;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    private boolean validateDate(Date fromDate, Date toDate) {
        if (fromDate.toInstant().isBefore(toDate.toInstant())) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "WorkingHistory{" +
                "workingHistoryId=" + workingHistoryId +
                ", employee=" + employee.getEmp_no() +
                ", department=" + department.getDeptNo() +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                "}";
    }
}
