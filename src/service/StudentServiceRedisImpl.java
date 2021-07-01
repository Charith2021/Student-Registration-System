package service;

import model.Student;
import redis.clients.jedis.Jedis;
import service.exception.DuplicateEntryException;
import service.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class StudentServiceRedisImpl {
    public void saveStudent(Student student) throws DuplicateEntryException {
        Jedis client = new Jedis("localhost", 9090);

        if(client.exists(student.getNic())){
            throw new DuplicateEntryException();
        }
        if (client.exists(student.getNic())) {
            throw new DuplicateEntryException();
        }
        client.hset(student.getNic(), student.toMap());
    }


    public void updateStudent(Student student) throws NotFoundException {
        Jedis client = new Jedis("localhost", 9090);
        if (!client.exists(student.getNic())) {
            throw new NotFoundException();
        }
        client.hset(student.getNic(), student.toMap());
    }



    public void deleteStudent(String nic) throws NotFoundException {

    }

    public List<Student> findAllStudents(){
        return null;

    }


    private Student getStudent(String nic){          //we made this private method.than kepayaka use wena nisa method ekakata gattha
       return null;
    }


    public Student findStudent(String nic) throws NotFoundException {
       return  null;
    }


    public List<Student> findStudents(String query){
      return null;
    }

}
