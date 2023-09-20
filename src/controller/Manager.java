    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package controller;

    /**
     *
     * @author MSII
     */


    import view.Menu;
    import model.Candidate;
    import validation.Validation;

    import java.text.SimpleDateFormat;
    import java.util.ArrayList;
    import java.util.Calendar;
    import java.util.Date;
    import model.Experience;
    import model.Fresher;
    import model.Internship;
import static validation.Validation.checkIdExist;

    public class Manager extends Menu<String> {
        private ArrayList<Candidate> candidates;
        
    private ArrayList<Experience> experienceCandidates;
    private ArrayList<Fresher> fresherCandidates;
    private ArrayList<Internship> internshipCandidates;

        public Manager(ArrayList<Candidate> candidates, ArrayList<Experience> experienceCandidates, ArrayList<Fresher> fresherCandidates, ArrayList<Internship> internshipCandidates) {
        super("CANDIDATE MANAGEMENT SYSTEM", new String[]{"Experience", "Fresher", "Internship", "Searching", "Exit"});
        this.candidates = candidates;
        this.experienceCandidates = experienceCandidates;
        this.fresherCandidates = fresherCandidates;
        this.internshipCandidates = internshipCandidates;
    }

        @Override
        public void execute(int n) {
            switch (n) {
                case 1:
                    // Create Experience Candidate
                    createExperienceCandidate();
                    break;
                case 2:
                    // Create Fresher Candidate
                    createFresherCandidate();
                    break;
                case 3:
                    // Create Internship Candidate
                    createInternshipCandidate();
                    break;
                case 4:
                    // Searching
                    searchCandidates();
                    break;
                case 5:
                    // Exit
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid selection. Please select again.");
            }

        }

        private void createExperienceCandidate() {
        System.out.println("Creating Experience Candidate:");

        // Input candidate details
        String id;
        do {
            id = Validation.checkInputString("Enter ID: ");
        } while (!checkIdExist(candidates, id));

        String firstName = Validation.checkInputString("Enter First Name: ");
        String lastName = Validation.checkInputString("Enter Last Name: ");
        Date birthDate = Validation.checkInputDate("Enter Birth Date (yyyy-MM-dd): ");
        String address = Validation.checkInputString("Enter Address: ");
        String phone = Validation.checkInputPhone("Enter Phone: ");
        String email = Validation.checkInputEmail("Enter Email: ");

        // Calculate age
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthDate);
        int birthYear = calendar.get(Calendar.YEAR);
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int age = currentYear - birthYear;

        // Input year of experience
        int yearExperience = Validation.checkInputExperience("Enter Year of Experience: ", birthYear);

        String professionalSkill = Validation.checkInputString("Enter Professional Skill: ");

        // Format ngày tháng thành chuỗi "yyyy-MM-dd"
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDateStr = dateFormat.format(birthDate);

        // Create and add the Experience Candidate to the candidates list
        Candidate experienceCandidate = new Experience(yearExperience, professionalSkill, id, firstName, lastName, birthDate, address, phone, email, yearExperience);
        candidates.add(experienceCandidate);

        System.out.println("Experience Candidate created successfully.");

        if (!askToContinue()) {
            displayAllCandidates();
        }
    }

        private void createFresherCandidate() {
        System.out.println("Creating Fresher Candidate:");

        // Input candidate details
        String id;
        do {
            id = Validation.checkInputString("Enter ID: ");
        } while (!checkIdExist(candidates, id));

        String firstName = Validation.checkInputString("Enter First Name: ");
        String lastName = Validation.checkInputString("Enter Last Name: ");
        Date birthDate = Validation.checkInputDate("Enter Birth Date (yyyy-MM-dd): ");
        String address = Validation.checkInputString("Enter Address: ");
        String phone = Validation.checkInputPhone("Enter Phone: ");
        String email = Validation.checkInputEmail("Enter Email: ");
        Date graduationDate = Validation.checkInputDate("Enter Graduation Date (yyyy-MM-dd): ");
        String graduationRank = Validation.checkInputGraduationRank("Enter Graduation Rank (Excellence, Good, Fair, Poor): ");
        String university = Validation.checkInputString("Enter University: ");

        // Định dạng ngày tháng
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String birthDateString = dateFormat.format(birthDate);
        String graduationDateString = dateFormat.format(graduationDate);

        // Create and add the Fresher Candidate to the candidates list
        Candidate fresherCandidate = new Fresher(graduationDate, graduationRank, university, id, firstName, lastName, birthDate, address, phone, email, 0);
        candidates.add(fresherCandidate);

        System.out.println("Fresher Candidate created successfully.");

        // Hỏi người dùng nếu muốn tiếp tục
        if (!askToContinue()) {
            displayAllCandidates();
        }
    }

    private void createInternshipCandidate() {
        System.out.println("Creating Intern Candidate:");
        // Input candidate details
        String id;
        do {
            id = Validation.checkInputString("Enter ID: ");
        } while (!checkIdExist(candidates, id));

        String firstName = Validation.checkInputString("Enter First Name: ");
        String lastName = Validation.checkInputString("Enter Last Name: ");
        Date birthDate = Validation.checkInputDate("Enter Birth Date (yyyy-MM-dd): ");
        String address = Validation.checkInputString("Enter Address: ");
        String phone = Validation.checkInputPhone("Enter Phone: ");
        String email = Validation.checkInputEmail("Enter Email: ");
        String major = Validation.checkInputString("Enter Major: ");
        String semester = Validation.checkInputString("Enter Semester: ");
        String university = Validation.checkInputString("Enter University: ");

        // Create Date of Birth from Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOfBirthString = dateFormat.format(birthDate);

        // Create and add the Intern Candidate to the candidates list
        Candidate internCandidate = new Internship(major, semester, university, id, firstName, lastName, birthDate, address, phone, email, 0);
        candidates.add(internCandidate);

        System.out.println("Intern Candidate created successfully.");

        // Hỏi người dùng nếu muốn tiếp tục
        if (!askToContinue()) {
            displayAllCandidates();
        }
    }

        private void searchCandidates() {
            System.out.println("List of candidate:");
            // Hiển thị danh sách các ứng viên theo loại
            System.out.println("===========EXPERIENCE CANDIDATE============");
            boolean foundExperience = displayCandidatesByType(Experience.class);
            System.out.println("==========FRESHER CANDIDATE==============");
            boolean foundFresher = displayCandidatesByType(Fresher.class);
            System.out.println("===========INTERN CANDIDATE==============");
            boolean foundInternship = displayCandidatesByType(Internship.class);

            // Nhập tên ứng viên và loại ứng viên
            String searchName = Validation.checkInputString("Input Candidate name (First name or Last name): ");
            int typeCandidate = Validation.checkInputTypeCandidate("Input type of candidate (0 for Experience, 1 for Fresher, 2 for Internship): ");

            // Tạo mapping từ loại ứng viên sang chuỗi
            String typeString = typeCandidate == 0 ? "0" : (typeCandidate == 1 ? "1" : "2");

            // Hiển thị kết quả tìm kiếm
            System.out.println("The candidates found:");
            boolean found = false;
            for (Candidate candidate : candidates) {
                if (candidate.getFirstName().toLowerCase().contains(searchName.toLowerCase()) || candidate.getLastName().toLowerCase().contains(searchName.toLowerCase())) {
                    if ((typeCandidate == 0 && candidate instanceof Experience && foundExperience)
                            || (typeCandidate == 1 && candidate instanceof Fresher && foundFresher)
                            || (typeCandidate == 2 && candidate instanceof Internship && foundInternship)) {
                        String birthDateString = new SimpleDateFormat("yyyy").format(candidate.getBirthDate());
                        System.out.println(candidate.getFirstName() + " " + candidate.getLastName() + " | "
                                + birthDateString + " | " + candidate.getAddress() + " | "
                                + candidate.getPhone() + " | " + candidate.getEmail() + " | " + typeString);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No found infomation.");
            }
        }

        private boolean displayCandidatesByType(Class<?> type) {
            boolean found = false;
            for (Candidate candidate : candidates) {
                if (type.isInstance(candidate)) {
                    System.out.println(candidate.getFirstName() + " " + candidate.getLastName());
                    found = true;
                }
            }
            return found;
        }


        
    private boolean askToContinue() {
        System.out.println("Do you want to continue (Y/N)?");
        return Validation.checkInputYN("Y/N: ");
    }

    private void displayAllCandidates() {
        System.out.println("List of all candidates:");
        for (Candidate candidate : candidates) {
            String birthDateString = new SimpleDateFormat("yyyy-MM-dd").format(candidate.getBirthDate());
            System.out.println(candidate.getFirstName() + " " + candidate.getLastName() + " | " +
                    birthDateString + " | " + candidate.getAddress() + " | " +
                    candidate.getPhone() + " | " + candidate.getEmail() + " | " +
                    (candidate instanceof Experience ? "Experience" : (candidate instanceof Fresher ? "Fresher" : "Internship")));
        }
    }
        
    }

