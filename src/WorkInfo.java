
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Afzaal isnaufal
 */
public class WorkInfo {
    private int yearJoined;
    private int monthJoined;
    private int dayJoined;
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    private int monthWorkingInYear;

    public void setMonthlySalary(int grade, boolean isForeigner) {
        int baseSalary = switch (grade) {
            case 1 -> 3000000;
            case 2 -> 5000000;
            case 3 -> 7000000;
            default -> 0;
        };
        this.monthlySalary = isForeigner ? (int) (baseSalary * 1.5) : baseSalary;
    }

    public void calculateMonthWorking() {
        LocalDate date = LocalDate.now();
        this.monthWorkingInYear = (date.getYear() == yearJoined) ? 
            date.getMonthValue() - monthJoined : 12;
    }

    // getters
}
