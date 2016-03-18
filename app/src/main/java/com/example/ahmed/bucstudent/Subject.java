package com.example.ahmed.bucstudent;


public class Subject {

    private int id ;
    private String SubjectName ;
    private String day ;
    private int StartTimeH ;
    private int StartTimeM ;
    private String StartTimeAP ;
    private int EndTimeH ;
    private int EndTimeM ;
    private String EndTimeAP ;
    private String Room ;
    private String InstructorName ;
    private int dayIndex ;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public int getDayIndex() {
        return dayIndex;
    }

    public void setDayIndex(int dayIndex) {
        this.dayIndex = dayIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public int getStartTimeH() {
        return StartTimeH;
    }

    public void setStartTimeH(int startTimeH) {
        StartTimeH = startTimeH;
    }

    public int getStartTimeM() {
        return StartTimeM;
    }

    public void setStartTimeM(int startTimeM) {
        StartTimeM = startTimeM;
    }

    public String getStartTimeAP() {
        return StartTimeAP;
    }

    public void setStartTimeAP(String startTimeAP) {
        StartTimeAP = startTimeAP;
    }

    public int getEndTimeH() {
        return EndTimeH;
    }

    public void setEndTimeH(int endTimeH) {
        EndTimeH = endTimeH;
    }

    public int getEndTimeM() {
        return EndTimeM;
    }

    public void setEndTimeM(int endTimeM) {
        EndTimeM = endTimeM;
    }

    public String getEndTimeAP() {
        return EndTimeAP;
    }

    public void setEndTimeAP(String endTimeAP) {
        EndTimeAP = endTimeAP;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }
}
