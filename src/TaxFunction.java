
public class TaxFunction {

    // Konstanta untuk penghitungan pajak
    private static final int BASIC_NONTAXABLE_INCOME = 54_000_000;
    private static final int MARRIAGE_ALLOWANCE = 4_500_000;
    private static final int CHILD_ALLOWANCE = 4_500_000;
    private static final int MAX_CHILDREN_COUNT = 3;
    private static final double TAX_RATE = 0.05;

    private static final int MAX_MONTHS_IN_YEAR = 12;

    /**
  
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int monthsWorked, int deductible,
                                   boolean isMarried, int numberOfChildren) {

        if (monthsWorked > MAX_MONTHS_IN_YEAR) {
            System.err.println("Jumlah bulan bekerja tidak boleh lebih dari " + MAX_MONTHS_IN_YEAR + ".");
            monthsWorked = MAX_MONTHS_IN_YEAR; 
        }

        // batas jumlah anak maksimal sesuai peraturan
        int cappedChildren = Math.min(numberOfChildren, MAX_CHILDREN_COUNT);

        //  penghasilan tahunan dan penghasilan bersih
        int annualIncome = (monthlySalary + otherMonthlyIncome) * monthsWorked;
        int netIncome = annualIncome - deductible;

        //  penghasilan tidak kena pajak (PTKP)
        int nonTaxableIncome = BASIC_NONTAXABLE_INCOME;
        if (isMarried) {
            nonTaxableIncome += MARRIAGE_ALLOWANCE;
        }
        nonTaxableIncome += cappedChildren * CHILD_ALLOWANCE;

        // Hitung penghasilan kena pajak
        int taxableIncome = netIncome - nonTaxableIncome;
        if (taxableIncome < 0) {
            taxableIncome = 0;
        }

        // Hitung pajak 5%
        return (int) Math.round(TAX_RATE * taxableIncome);
    }
}
