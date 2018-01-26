package encryptedFileTransfer.restServer.dao;

import encryptedFileTransfer.restServer.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;

@Repository
public class StudentDao {

    public Collection<Student> getAllStudents() {
        return new ArrayList<Student>() {{
            add(new Student(1, "Mario", "Nothing"));
        }};
    }

    public Student getStudentById(int id) {
        return null;
    }

    public void removeStudentById(int id) {

    }

    public void updateStudent(Student student) {

    }

    public void insertStudent(Student student) {

    }
}
