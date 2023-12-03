package com.example.session;

public class Subjects {
    int subjects_id;
    String subject;
    int course_id;

    public Subjects(int subjects_id, String subject, int course_id) {
        this.subjects_id = subjects_id;
        this.subject = subject;
        this.course_id = course_id;
    }

    public int getSubjects_id() {
        return subjects_id;
    }

    public void setSubjects_id(int subjects_id) {
        this.subjects_id = subjects_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
}
