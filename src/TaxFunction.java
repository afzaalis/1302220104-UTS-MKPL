public class TaxFunction {

    public static class Income {
        private int monthlySalary;
        private int otherMonthlyIncome;

        public Income(int monthlySalary, int otherMonthlyIncome) {
            this.monthlySalary = monthlySalary;
            this.otherMonthlyIncome = otherMonthlyIncome;
        }

        public int getMonthlySalary() {
            return monthlySalary;
        }

        public int getOtherMonthlyIncome() {
            return otherMonthlyIncome;
        }

        public int getTotalIncomePerYear(int numberOfMonthWorking) {
            return (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        }
    }

    public static class WorkPeriod {
        private int numberOfMonthWorking;

        public WorkPeriod(int numberOfMonthWorking) {
            if (numberOfMonthWorking > 12) {
                System.err.println("More than 12 months working per year");
            }
            this.numberOfMonthWorking = Math.min(numberOfMonthWorking, 12); // Capping to 12 months
        }

        public int getNumberOfMonthWorking() {
            return numberOfMonthWorking;
        }
    }

    public static class FamilyStatus {
        private boolean isMarried;
        private int numberOfChildren;

        public FamilyStatus(boolean isMarried, int numberOfChildren) {
            this.isMarried = isMarried;
            this.numberOfChildren = Math.min(numberOfChildren, 3); // Capping to 3 children
        }

        public boolean isMarried() {
            return isMarried;
        }

        public int getNumberOfChildren() {
            return numberOfChildren;
        }

        public int getTaxExemption() {
            int exemption = 54000000; // Base exemption
            if (isMarried) {
                exemption += 4500000; // Additional for married
            }
            exemption += numberOfChildren * 4500000; // Additional for children
            return exemption;
        }
    }

    public static class TaxFunctions {

        /**
         * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
         *
         * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
         *
         * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
         * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
         * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
         */
        public static int calculateTax(Income income, WorkPeriod workPeriod, int deductible, FamilyStatus familyStatus) {
            int totalIncome = income.getTotalIncomePerYear(workPeriod.getNumberOfMonthWorking());
            int taxExemption = familyStatus.getTaxExemption();

            int taxableIncome = totalIncome - deductible - taxExemption;
            int tax = (int) Math.round(0.05 * taxableIncome);

            return Math.max(tax, 0); // Ensuring tax is non-negative
        }
    }

 
}
