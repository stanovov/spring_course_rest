package ru.sestanovov.spring.rest.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.sestanovov.spring.rest.entity.Employee;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Employee", Employee.class)
                .getResultList();
    }

    @Override
    public void saveEmployee(Employee employee) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        return sessionFactory.getCurrentSession()
                .get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        sessionFactory.getCurrentSession()
                .createQuery("delete from Employee where id = :empId")
                .setParameter("empId", id)
                .executeUpdate();
    }
}
