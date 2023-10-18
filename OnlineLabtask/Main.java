import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final int VACATION_OFFICER = 15;
    private static final int SICK_OFFICER = 10;
    private static final int VACATION_STAFF = 10;
    private static final int SICK_STAFF = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee information:");

        for (int i = 0; i < 5; i++) {
            System.out.print("ID: ");
            String id = scanner.nextLine();

            System.out.print("Name: ");
            String name = scanner.nextLine();

            System.out.print("Date of Birth (dd/MM/yyyy): ");
            String dobString = scanner.nextLine();
            Date dateOfBirth = parseDate(dobString);

            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Joining Date (dd/MM/yyyy): ");
            String joiningDateString = scanner.nextLine();
            Date joiningDate = parseDate(joiningDateString);

            System.out.print("Employee Type (1 - Officer, 2 - Staff): ");
            int employeeType = scanner.nextInt();
            scanner.nextLine();

            Employee employee;
            if (employeeType == 1) {
                employee = new Officer(id, name, dateOfBirth, email, joiningDate);
            } else {
                employee = new Staff(id, name, dateOfBirth, email, joiningDate);
            }

            int vacationLeave = calculateLeave(employee, "Vacation");
            int sickLeave = calculateLeave(employee, "Sick");

            System.out.println("\nEmployee Details:");
            System.out.println("ID: " + employee.getId());
            System.out.println("Name: " + employee.getName());
            System.out.println("Date of Birth: " + formatDate(employee.getDateOfBirth()));
            System.out.println("Email: " + employee.getEmail());
            System.out.println("Joining Date: " + formatDate(employee.getJoiningDate()));
            System.out.println("Vacation Leave: " + vacationLeave);
            System.out.println("Sick Leave: " + sickLeave);
        }

        scanner.close();
    }

    private static Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String formatDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    private static int calculateLeave(Employee employee, String leaveType) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 11); // December, starts from 0
        calendar.set(Calendar.DAY_OF_MONTH, 31); // Last day of the year

        int totalDays = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        if (calendar.getActualMaximum(Calendar.DAY_OF_YEAR) == 365) { //2023 is not leap year so 365days
            totalDays = 365;
        }

        double leaveDays = ((calendar.getTime().getTime() - employee.getJoiningDate().getTime()) / (24 * 60 * 60 * 1000) + 1)
                * getTotalLeaveDays(employee, leaveType);

        DecimalFormat decimalFormat = new DecimalFormat("#.##########");
        double leaveFraction = Double.parseDouble(decimalFormat.format(leaveDays / totalDays));

        if (leaveFraction <0.5) {
            return (int) Math.floor(leaveFraction);
        } else {
            return (int) Math.ceil(leaveFraction);
        }
    }

    private static int getTotalLeaveDays(Employee employee, String leaveType) {
        if (employee instanceof Officer) {
            if (leaveType.equals("Vacation")) {
                return VACATION_OFFICER;
            } else if (leaveType.equals("Sick")) {
                return SICK_OFFICER;
            }
        } else if (employee instanceof Staff) {
            if (leaveType.equals("Vacation")) {
                return VACATION_STAFF;
            } else if (leaveType.equals("Sick")) {
                return SICK_STAFF;
            }
        }

        return 0;
    }
}