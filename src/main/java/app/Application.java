package app;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.WorkingHistoryDao;
import model.Department;
import model.Employee;
import model.Gender;
import model.WorkingHistory;
import utils.HibernateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) throws ParseException {
        HibernateUtil.buildSessionFactory();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        Scanner sc = new Scanner(System.in);
        int userOption = 0;
        do {

            System.out.println("------------------------------------------------------------");
            System.out.println("Welcome to Company Management App\n" +
                    "What would you like to do(Enter the number):\n" +
                    "1. Employee management\n" +
                    "2. Department management\n" +
                    "0. Close program");

            userOption = Integer.parseInt(sc.nextLine());
            switch (userOption) {
                case 1:
                    System.out.println("1. Add a new Employee\n" +
                            "2. Update a specific Employee\n" +
                            "3. Find an employee by emp_no\n" +
                            "4. Add the working history\n" +
                            "5. Find all the employees by working period of time");
                    userOption = Integer.parseInt(sc.nextLine());
                    switch (userOption) {
                        case 1:
                            //add new emp
                            System.out.println("Enter employee first name:");
                            String firstName = sc.nextLine();
                            System.out.println("Enter employee last name:");
                            String lastName = sc.nextLine();
                            System.out.println("Enter birth date: (yyyy-MM-dd)");
                            Date birthDate = date.parse(sc.nextLine());
                            System.out.println("Enter hire date:  (yyyy-MM-dd)");
                            Date hireDate = date.parse(sc.nextLine());
                            System.out.println("Enter gender (MALE or FEMALE):");
                            Gender gender = Gender.valueOf(sc.nextLine());
                            Employee e = new Employee(birthDate, firstName, lastName, gender, hireDate);
                            EmployeeDao.save(e);
                            System.out.println("Done");
                            break;
                        case 2:
                            //update 1 emp
                            System.out.println("Enter employee id:");
                            int uid = Integer.parseInt(sc.nextLine());
                            Employee eInDb = EmployeeDao.findById(uid);
                            if (eInDb != null) {
                                System.out.println("Enter employee first name:");
                                String firstName1 = sc.nextLine();
                                System.out.println("Enter employee last name:");
                                String lastName1 = sc.nextLine();
                                System.out.println("Enter birth date: (yyyy-MM-dd)");
                                Date birthDate1 = date.parse(sc.nextLine());
                                System.out.println("Enter hire date:  (yyyy-MM-dd)");
                                Date hireDate1 = date.parse(sc.nextLine());
                                System.out.println("Enter gender (MALE or FEMALE):");
                                Gender gender1 = Gender.valueOf(sc.nextLine());
                                Employee e1 = new Employee(birthDate1, firstName1, lastName1, gender1, hireDate1);
                                EmployeeDao.update(uid, e1);
                                System.out.println("Done");
                            } else {
                                throw new IllegalStateException("There is no employee with this id");

                            }
                            break;
                        case 3:
                            //find emp by id
                            try {
                                System.out.println("Enter employee id:");
                                int id = Integer.parseInt(sc.nextLine());
                                System.out.println(EmployeeDao.findById(id));
                            } catch (Exception y) {
                                System.err.println("This employee doesnt exist");
                                break;
                            }
                            break;
                        case 4:
                            //add history
                            Employee thisEmployee = null;
                            Department thisDepartment = null;
                            try {
                                System.out.println("Enter employee id:");
                                int eId = Integer.parseInt(sc.nextLine());
                                thisEmployee = EmployeeDao.findById(eId);
                            } catch (Exception y) {
                                System.err.println("This employee doesnt exist");
                                break;
                            }
                            try {
                                System.out.println("Enter department id:");
                                int dId = Integer.parseInt(sc.nextLine());
                                thisDepartment = DepartmentDao.findById(dId);
                            } catch (Exception y) {
                                System.err.println("This department doesnt exist");
                                break;
                            }
                            System.out.println("Enter from date: (yyyy-MM-dd)");
                            Date fromDate = date.parse(sc.nextLine());
                            System.out.println("Enter to date:  (yyyy-MM-dd)");
                            Date toDate = date.parse(sc.nextLine());
                            try{
                                WorkingHistory workingHistory = new WorkingHistory(thisEmployee, thisDepartment, fromDate, toDate);
                                WorkingHistoryDao.save(workingHistory);
                            } catch (Exception y){
                                System.err.println("Invalid info");
                                break;
                            }
                            break;
                        case 5:
                            //find all emp by working time
                            System.out.println("Enter from date: (yyyy-MM-dd)");
                            String fromDate1 = sc.nextLine();
                            System.out.println("Enter to date:  (yyyy-MM-dd)");
                            String toDate1 = sc.nextLine();
                            EmployeeDao.findByWorkTime(fromDate1, toDate1).forEach(emp -> System.out.println(emp));
                            break;
                    }
                    break;

                case 2:
                    System.out.println("1. Add a new Department");
                    //add new department
                    System.out.println("Enter department name:");
                    String deptName = sc.nextLine();
                    System.out.println("Enter department description:");
                    String deptdes = sc.nextLine();
                    DepartmentDao.save(new Department(deptName, deptdes));
                    System.out.println("done");
                    break;
            }

        } while (userOption != 0);
        sc.close();
    }
}
