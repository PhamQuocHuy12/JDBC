package dao;

import model.Employee;
import model.Gender;
import model.WorkingHistory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;


import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeDao {
    private static Transaction tx;

//    public static void main (String args[]) throws ParseException {
//////        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//////        Employee employee = new Employee(date.parse("2000-30-8"), "D", "Nguyen", Gender.FEMALE, date.parse("2019-8-25"));
//////        update(2, employee);
////        String date1 = "2017-01-01";
////        String date2 = "2022-01-01";
////        findByWorkTime(date1, date2).forEach(e -> System.out.println(e));
////        System.out.println(findById(10));
//
//    }

    //ok
    @Transactional
    public static List<Employee> findAll(){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            Query query = session.createNativeQuery("SELECT * FROM employee").addEntity(Employee.class);
            tx.commit();
            return query.getResultList();
        }
    }

    //ok
    public static void save(Employee employee){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            session.save(employee);
            tx.commit();
        }
    }
    //ok
    public static void update(int empNo,Employee employee){
        Employee employee1 = findById(empNo);
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            employee1 = employee;
            employee1.setEmp_no(empNo);
            session.update(employee1);
            tx.commit();
        }
    }
    //ok
    public static void delete(int empNo){
        Employee employee = findById(empNo);
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            session.delete(employee);
            tx.commit();
        }
    }
    //ok
    public static Employee findById(int empNo){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            Employee employee = session.get(Employee.class, empNo);
            tx.commit();
            if(employee == null){
                throw new IllegalStateException("Not available");
            }
            return employee;
        }
    }

    @Transactional
    public static List<Employee> findByWorkTime(String fromDate, String toDate){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            String sql = String.format("SELECT * FROM dbo.working_history WHERE from_date >= '%s' AND to_date <= '%s'", fromDate, toDate);
            SQLQuery query = session.createSQLQuery(sql).addEntity(WorkingHistory.class);
            tx.commit();
            List<Employee> employeeList = new ArrayList<>();
            List<WorkingHistory> workingHistoryList =  query.list();
            workingHistoryList.forEach(w -> employeeList.add(w.getEmployee()));
            return employeeList;
        }
    }
}
