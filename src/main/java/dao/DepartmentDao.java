package dao;

import model.Department;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

public class DepartmentDao {
    private static Transaction tx;
//    public static void main (String args[]){
//        Department d1 = new Department("PE", "this is physical education");
//        save(d1);
//    }

    //ok
    public static void save(Department department){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            session.save(department);
            tx.commit();
        }
    }

    public static Department findById(int deptNo){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            Department department = session.get(Department.class, deptNo);
            if(department == null){
                throw new IllegalStateException("Not available");
            }
            tx.commit();
            return department;
        }
    }
}
