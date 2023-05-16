package com.example.thesisprojectmobileapp;

public class schedule {
    private String time_start;
    private  String time_end;
    private String subject;
    private String section;
    private String instructor;



    public schedule(){

    }

    public schedule(String time_start, String time_end, String subject, String section, String instructor){
        this.time_start = time_start;
        this.time_end = time_end;
        this.subject = subject;
        this.section = section;
        this.instructor = instructor;
    }

    public String getTime_start(){
        return time_start;
    }
    public String getTime_end(){
        return time_end;
    }
    public String getSubject(){
        return subject;
    }
    public String getSection(){
        return section;
    }
    public String getInstructor(){
        return instructor;
    }

}
