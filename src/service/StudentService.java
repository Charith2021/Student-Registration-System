package service;

import model.Student;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService {

    private static final List<Student> studentsDB=new ArrayList<>();

        static{
            //Lets add some dummy data
            Student s1 = new Student("456789123V", "Chandima Herath", "Galle", LocalDate.of(1996, 05, 01), "077-1234567", "abc@ijse.lk");
            Student s2 = new Student("879456123V", "Pethum Jeewantha", "Matara", LocalDate.of(1989, 10, 01), "077-456789", "pethum@hotmail.lk");
            Student s3 = new Student("456132789V", "Dilan Chathuranga", "Panadura", LocalDate.of(1999, 06, 24), "077-1234567", "dilan@ijse.lk");
            Student s4 = new Student("879456123V", "Pethum Nuwan", "Matara", LocalDate.of(1989, 10, 01), "077-456789", "pethum@ijse.lk");
            studentsDB.add(s1);
            studentsDB.add(s2);
            studentsDB.add(s3);
            studentsDB.add(s4);
        }


    public void saveStudent(Student student) throws DuplicateEntryException{
        Student student1 = getStudent(student.getNic());   //first see the get student method which is made by us//parameter eken ena student ge nic eka aran student1 kiyana variable eke store kara ganna
        if(student1 != null){
            throw new DuplicateEntryException();
        }
        studentsDB.add(student);
    }


    public void updateStudent(Student student) throws NotFoundException {
        Student s = findStudent(student.getNic());    //find student eken not found exception ekak enna puluwan.awoth palleha peli deka wada na.nathnam a deka wada
        int index=studentsDB.indexOf(s);
        studentsDB.set(index,student);
    }

    public static void deleteStudent(String nic) throws NotFoundException {
        Student student=findStudent(nic);
        studentsDB.remove(student);
    }

    public List<Student> findAllStudents(){
        return studentsDB;
    }


    private static Student getStudent(String nic){          //we made this private method.than kepayaka use wena nisa method ekakata gattha
        for (Student student : studentsDB) {

            if (student.getNic().equals(nic)){
                return student;
            }
        }
        return null;
    }


    public static Student findStudent(String nic) throws NotFoundException {
        Student student = getStudent(nic);       //Student type eke student kiyana variable ekak athulata getStudent() method eka return karana value eka dagena
        if(student != null){
                return student;
            }else{
                throw new NotFoundException();
            }
    }


    public List<Student> findStudents(String query){
        List<Student> result = new ArrayList<>();

        for (Student student : studentsDB) {

            if (student.getNic().contains(query) || student.getFullName().contains(query)
                    || student.getAddress().contains(query) || student.getEmail().contains(query) ||
                    student.getDateOfBirth().toString().contains(query) || student.getContact().contains(query)) {
                result.add(student);
            }
        }
        return result;

    }

}
