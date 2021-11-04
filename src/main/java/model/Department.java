package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department", schema = "dbo")
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_no")
    private int deptNo;

    @Column(name = "dept_name", length = 50)
    private String deptName;

    @Column(name = "description", length = 100)
    private String description;

    @OneToMany
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
    private List<WorkingHistory> workingHistoryList = new ArrayList<>();

    public Department(String deptName, String description) {
        if (validateName(deptName) && validateDes(description)) {
            this.deptName = deptName;
            this.description = description;
        } else {
            throw new IllegalStateException("Invalid Information");
        }
    }

    public Department() {
        super();
    }


    public int getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(int dept_no) {
        this.deptNo = dept_no;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WorkingHistory> getWorkingHistoryList() {
        return workingHistoryList;
    }

    public void setWorkingHistoryList(List<WorkingHistory> workingHistoryList) {
        this.workingHistoryList = workingHistoryList;
    }

    private boolean validateName(String deptName) {
        if (deptName.length() > 50) {
            return false;
        }
        return true;
    }

    private boolean validateDes(String description) {
        if (description.length() > 50) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptNo=" + deptNo +
                ", deptName='" + deptName + '\'' +
                ", description='" + description + '\'' +
                ", workingHistoryList=" + workingHistoryList +
                '}';
    }
}
