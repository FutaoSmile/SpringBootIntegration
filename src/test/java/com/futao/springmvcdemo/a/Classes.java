package com.futao.springmvcdemo.a;

import java.util.List;

/**
 * @author futao
 * Created on 2018/10/17.
 */
public class Classes {
    private String classRoom;
    private List<Student> students;

    public Classes(String classRoom, List<Student> students) {
        this.classRoom = classRoom;
        this.students = students;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
