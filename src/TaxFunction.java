
public class TaxFunction {

    /**
     * Menghitung pajak penghasilan tahunan pegawai berdasarkan pendapatan dan status.
     * Refactor ini memecah fungsi panjang menjadi beberapa fungsi kecil agar lebih readable dan maintainable.
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            System.err.println("More than 12 month working per year");
        }

        int totalIncome = calculateTotalIncome(monthlySalary, otherMonthlyIncome, numberOfMonthWorking);
        int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);
        int taxableIncome = totalIncome - deductible - nonTaxableIncome;

        int tax = (int) Math.round(0.05 * taxableIncome);

        return Math.max(tax, 0);  // Pajak tidak boleh negatif
    }

    private static int calculateTotalIncome(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking) {
        return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        final int BASE_NON_TAXABLE = 54000000;
        final int MARRIED_BONUS = 4500000;
        final int CHILD_BONUS = 1500000;

        int nonTaxableIncome = BASE_NON_TAXABLE;

        if (isMarried) {
            nonTaxableIncome += MARRIED_BONUS;
        }

        // Maksimal 3 anak untuk pengurangan pajak
        int maxChildren = Math.min(numberOfChildren, 3);
        nonTaxableIncome += maxChildren * CHILD_BONUS;

        return nonTaxableIncome;
    }
}
