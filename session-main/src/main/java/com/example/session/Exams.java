package com.example.session;

public class Exams {
    int exam_details_id,subject_id,teacher_id,student_id,grade,attempt_num;

    public Exams(int exam_details_id, int subject_id, int teacher_id, int student_id, int grade, int attempt_num) {
        this.exam_details_id = exam_details_id;
        this.subject_id = subject_id;
        this.teacher_id = teacher_id;
        this.student_id = student_id;
        this.grade = grade;
        this.attempt_num = attempt_num;
    }

    public int getExam_details_id() {
        return exam_details_id;
    }

    public void setExam_details_id(int exam_details_id) {
        this.exam_details_id = exam_details_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getAttempt_num() {
        return attempt_num;
    }

    public void setAttempt_num(int attempt_num) {
        this.attempt_num = attempt_num;
    }
}
