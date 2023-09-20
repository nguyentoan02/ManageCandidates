/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package validation;

/**
 *
 * @author MSII
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import model.Candidate;

public class Validation {

    private final static Scanner in = new Scanner(System.in);
    private static final String PHONE_VALID = "^\\d{10}\\d*$";
    private static final String EMAIL_VALID = "^[A-Za-z0-9+_.-]+@(.+)$"; // Biểu thức chính quy cho email
    
        /**
     * Nhập một số nguyên từ người dùng và kiểm tra nó có nằm trong một khoảng cho trước không.
     * @param min Giá trị tối thiểu được chấp nhận.
     * @param max Giá trị tối đa được chấp nhận.
     * @return Giá trị nguyên hợp lệ nằm trong khoảng [min, max].
     */
    public static int checkInputIntLimit(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input number in range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }
    
        /**
     * Nhập một chuỗi từ người dùng và đảm bảo rằng nó không rỗng.
     * @param message Thông báo để hiển thị cho người dùng trước khi nhập chuỗi.
     * @return Chuỗi hợp lệ không rỗng.
     */
    public static String checkInputString(String message) {
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if (!result.isEmpty()) {
                return result;
            }
            System.err.println("Field cannot be empty.");
        }
    }
    
        /**
     * Nhập một câu trả lời (Y/N) từ người dùng và kiểm tra nó.
     * @param message Thông báo để hiển thị cho người dùng.
     * @return True nếu người dùng nhập "Y" hoặc "y", False nếu nhập "N" hoặc "n".
     */
    public static boolean checkInputYN(String message) {
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            } else if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input Y/y or N/n.");
        }
    }

        /**
     * Nhập số điện thoại từ người dùng và kiểm tra tính hợp lệ.
     * @param message Thông báo để hiển thị cho người dùng.
     * @return Số điện thoại hợp lệ (10 chữ số).
     */
    public static String checkInputPhone(String message) {
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if (result.matches(PHONE_VALID)) {
                return result;
            }
            System.err.println("Phone number must be 10 digits.");
        }
    }
    
        /**
     * Nhập địa chỉ email từ người dùng và kiểm tra tính hợp lệ.
     * @param message Thông báo để hiển thị cho người dùng.
     * @return Email hợp lệ theo định dạng <account name>@<domain>.
     */
    public static String checkInputEmail(String message) {
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if (Pattern.matches(EMAIL_VALID, result)) {
                return result;
            }
            System.err.println("Please enter a valid email address.");
        }
    }
    
        /**
     * Nhập xếp hạng tốt nghiệp từ người dùng và kiểm tra tính hợp lệ.
     * @param message Thông báo để hiển thị cho người dùng.
     * @return Một trong các giá trị sau: "Excellence", "Good", "Fair", "Poor".
     */
    public static String checkInputGraduationRank(String message) {
        while (true) {
            System.out.print(message);
            String result = in.nextLine().trim();
            if (result.equalsIgnoreCase("Excellence")
                    || result.equalsIgnoreCase("Good")
                    || result.equalsIgnoreCase("Fair")
                    || result.equalsIgnoreCase("Poor")) {
                return result;
            }
            System.err.println("Please input one of: Excellence, Good, Fair, Poor");
        }
    }
    
        /**
     * Kiểm tra xem một ID đã tồn tại trong danh sách ứng viên hay chưa.
     * @param candidates Danh sách ứng viên đã tồn tại.
     * @param id ID cần kiểm tra.
     * @return True nếu ID đã tồn tại, False nếu ID chưa tồn tại.
     */
    public static boolean checkIdExist(ArrayList<Candidate> candidates, String id) {
        for (Candidate candidate : candidates) {
            if (candidate.getId().equalsIgnoreCase(id)) {
                System.err.println("ID already exists.");
                return false;
            }
        }
        return true;
    }
    
        /**
     * Nhập và kiểm tra ngày tháng từ người dùng.
     * @param prompt Thông báo để hiển thị cho người dùng.
     * @return Ngày tháng hợp lệ trong định dạng "yyyy-MM-dd".
     */
    public static Date checkInputDate(String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);  // Ngăn ngừa việc chấp nhận ngày tháng không hợp lệ

        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = in.nextLine().trim();
                Date date = dateFormat.parse(dateStr);

                // Kiểm tra xem ngày tháng có hợp lệ (ví dụ: không nhập ngày sinh trong tương lai)
                if (date.after(new Date())) {
                    throw new ParseException("Invalid date", 0);
                }

                return date;
            } catch (ParseException e) {
                System.err.println("Invalid date format or future date. Please enter in yyyy-MM-dd format.");
            }
        }
    }
    
            /**
     * Nhập và kiểm tra năm kinh nghiệm từ người dùng.
     * @param prompt Thông báo để hiển thị cho người dùng.
     * @param birthYear Năm sinh của ứng viên, sử dụng để xác định giới hạn tối đa cho năm kinh nghiệm.
     * @return Năm kinh nghiệm hợp lệ trong khoảng từ 0 đến (năm hiện tại - năm sinh).
     */
    public static int checkInputExperience(String prompt, int birthYear) {
        while (true) {
            System.out.print(prompt);
            int experience = checkInputIntLimit(0, 100); // Kiểm tra người dùng nhập một số từ 0 đến 100

            // Kiểm tra kinh nghiệm không vượt quá tuổi
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);
            int maxExperience = currentYear - birthYear;

            if (experience <= maxExperience) {
                return experience;
            } else {
                System.err.println("Experience must be less than or equal to " + maxExperience);
            }
        }
    }
    
    
    /**
     * Nhập và kiểm tra loại ứng viên từ người dùng.
     * @param message Thông báo để hiển thị cho người dùng.
     * @return Loại ứng viên (0 cho Experience, 1 cho Fresher, 2 cho Internship).
     */
    public static int checkInputTypeCandidate(String message) {
        int typeCandidate;
        while (true) {
            try {
            Scanner scanner = new Scanner(System.in);
            System.out.print(message);
            typeCandidate = Integer.parseInt(scanner.nextLine());
            if (typeCandidate >= 0 && typeCandidate <= 2) {
                break;
            } else {
                System.out.println("Invalid input. Please enter 0 for Experience, 1 for Fresher, or 2 for Internship.");
            }
        }   catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    return typeCandidate;
}
   


}
