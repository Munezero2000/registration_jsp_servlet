package dao;

import java.util.List;
import models.Student;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 * Data Access Object for Student.
 */
public class StudentDAO {

    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    public Student createStudent(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(student);
            session.getTransaction().commit();
            logger.log(Level.INFO, "Student created successfully, Student details={0}", student);
            return student;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error creating student", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public Student updateStudent(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
            logger.log(Level.INFO, "Student updated successfully, Student details={0}", student);
            return student;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error updating student", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public Student deleteStudent(Student student) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(student);
            session.getTransaction().commit();
            logger.log(Level.INFO, "Student deleted successfully, Student details={0}", student);
            return student;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
            }
            logger.log(Level.SEVERE, "Error deleting student", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public Student findStudentById(int studentId) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Student student = (Student) session.get(Student.class, studentId);
            logger.log(Level.INFO, "Student found successfully, Student details={0}", student);
            return student;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error finding student by id", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public List<Student> getAllStudents() {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            logger.log(Level.INFO, "Students retrieved successfully, Number of students={0}", students.size());
            return students;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error retrieving students", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
