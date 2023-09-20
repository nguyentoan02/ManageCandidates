/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author MSII
 */


import java.util.Date;

public class Fresher extends Candidate {

    private Date graduationDate;
    private String graduationRank;
    private String university;

    public Fresher() {
        super();
    }

    public Fresher(Date graduationDate, String graduationRank, String university, String id,
            String firstName, String lastName, Date birthDate, String address,
            String phone, String email, int typeCandidate) {
        super(id, firstName, lastName, birthDate, address, phone, email,
                typeCandidate);
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.university = university;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }
}
