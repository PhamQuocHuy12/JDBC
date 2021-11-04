package dao;

import model.Department;
import model.Employee;
import model.WorkingHistory;
import model.WorkingHistory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class WorkingHistoryDao {

    private static Transaction tx;

//    public static void main(String args[]) throws ParseException {
//        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//        Employee e = EmployeeDao.findById(1);
//        Department d = DepartmentDao.findById(1);
//        WorkingHistory w = new WorkingHistory(e, d, date.parse("2020-8-23"), date.parse("2021-5-21"));
//        save(w);
//    }
    //ok
    public static void save(WorkingHistory workingHistory){
        try(Session session = HibernateUtil.openSession()){
            if(tx == null || !tx.isActive()) {
                tx = session.beginTransaction();
            }
            session.save(workingHistory);
            tx.commit();
        }
    }
}
