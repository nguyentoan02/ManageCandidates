    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package view;

    /**
     *
     * @author MSII
     */
    import controller.Manager;
    import model.Candidate;

    import java.util.ArrayList;
import model.Experience;
import model.Fresher;
import model.Internship;

    public class Main {
        public static void main(String[] args) {
            ArrayList<Candidate> candidates = new ArrayList<>();
        ArrayList<Experience> experienceCandidates = new ArrayList<>();
        ArrayList<Fresher> fresherCandidates = new ArrayList<>();
        ArrayList<Internship> internshipCandidates = new ArrayList<>();

        Manager manager = new Manager(candidates, experienceCandidates, fresherCandidates, internshipCandidates);


            while (true) {
                manager.run();
            }
        }
    }
