public class Employee {
    private String employeeId;
    private Personalinfo personalInfo;
    private FamilyInfo familyInfo;
    private WorkInfo workInfo;

    public Employee(String employeeId, Personalinfo personalInfo, int year, int month, int day) {
        this.employeeId = employeeId;
        this.personalInfo = personalInfo;
        this.familyInfo = new FamilyInfo();
        this.workInfo = new WorkInfo();
        // set tanggal masuk
        workInfo.yearJoined = year;
        workInfo.monthJoined = month;
        workInfo.dayJoined = day;
    }

    public void setSalaryGrade(int grade) {
        workInfo.setMonthlySalary(grade, personalInfo.isForeigner());
    }

    public void setAnnualDeductible(int deductible) {
        workInfo.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        workInfo.otherMonthlyIncome = income;
    }

    public void setSpouse(String name, String id) {
        familyInfo.setSpouse(name, id);
    }

    public void addChild(String name, String id) {
        familyInfo.addChild(name, id);
    }

    public int getAnnualIncomeTax() {
        workInfo.calculateMonthWorking();
        return TaxFunction.calculateTax(
            workInfo.monthlySalary,
            workInfo.otherMonthlyIncome,
            workInfo.monthWorkingInYear,
            workInfo.annualDeductible,
            !familyInfo.hasSpouse(),
            familyInfo.getNumberOfChildren()
        );
    }
}
