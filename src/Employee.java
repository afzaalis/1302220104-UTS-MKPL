
// import java.time.LocalDate;
// import java.time.Month;
// import java.util.LinkedList;
// import java.util.List;

// public class Employee {

// 	private String employeeId;
// 	private String firstName;
// 	private String lastName;
// 	private String idNumber;
// 	private String address;
	
// 	private int yearJoined;
// 	private int monthJoined;
// 	private int dayJoined;
// 	private int monthWorkingInYear; 
	
// 	private boolean isForeigner;
// 	private boolean gender; //true = Laki-laki, false = Perempuan
	
// 	private int monthlySalary;
// 	private int otherMonthlyIncome;
// 	private int annualDeductible;
	
// 	private String spouseName;
// 	private String spouseIdNumber;

// 	private List<String> childNames;
// 	private List<String> childIdNumbers;
	
// 	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
// 		this.employeeId = employeeId;
// 		this.firstName = firstName;
// 		this.lastName = lastName;
// 		this.idNumber = idNumber;
// 		this.address = address;
// 		this.yearJoined = yearJoined;
// 		this.monthJoined = monthJoined;
// 		this.dayJoined = dayJoined;
// 		this.isForeigner = isForeigner;
// 		this.gender = gender;
		
// 		childNames = new LinkedList<String>();
// 		childIdNumbers = new LinkedList<String>();
// 	}
	
// 	/**
// 	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
// 	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
// 	 */
	
// 	public void setMonthlySalary(int grade) {	
// 		if (grade == 1) {
// 			monthlySalary = 3000000;
// 			if (isForeigner) {
// 				monthlySalary = (int) (3000000 * 1.5);
// 			}
// 		}else if (grade == 2) {
// 			monthlySalary = 5000000;
// 			if (isForeigner) {
// 				monthlySalary = (int) (3000000 * 1.5);
// 			}
// 		}else if (grade == 3) {
// 			monthlySalary = 7000000;
// 			if (isForeigner) {
// 				monthlySalary = (int) (3000000 * 1.5);
// 			}
// 		}
// 	}
	
// 	public void setAnnualDeductible(int deductible) {	
// 		this.annualDeductible = deductible;
// 	}
	
// 	public void setAdditionalIncome(int income) {	
// 		this.otherMonthlyIncome = income;
// 	}
	
// 	public void setSpouse(String spouseName, String spouseIdNumber) {
// 		this.spouseName = spouseName;
// 		this.spouseIdNumber = idNumber;
// 	}
	
// 	public void addChild(String childName, String childIdNumber) {
// 		childNames.add(childName);
// 		childIdNumbers.add(childIdNumber);
// 	}
	
// 	public int getAnnualIncomeTax() {
		
// 		//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
// 		LocalDate date = LocalDate.now();
		
// 		if (date.getYear() == yearJoined) {
// 			monthWorkingInYear = date.getMonthValue() - monthJoined;
// 		}else {
// 			monthWorkingInYear = 12;
// 		}
		
// 		return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
// 	}
// }


//Hasil Refactoring dari code di bawah ini beserta penjelasannya

package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;

public class Employee {

	//Long Parameter list class ini memiliki lebih daari 10 parameter yang dapat membuatnya sulit dipahami dan rentan terhadap kesalahan saat pemanggilan.
	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	
	//Long parameter list, ke empat parameter di bawah ini dapat  dijadikan satu yaitu dateJoin

	// private int yearJoined;
	// private int monthJoined;
	// private int dayJoined;
	// private int monthWorkingInYear; 

	private LocalDate dateJoin;
	
	private boolean isForeigner;
	//Menggubah variabel gender dari booealn menjadi enum Gender agar mudah di pahami dan tidak ambigu
	private Gender gender; //true = Laki-laki, false = Perempuan
	
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	
	private String spouseName;
	private String spouseIdNumber;

	private List<String> childNames;
	private List<String> childIdNumbers;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined,LocalDate dateJoin, int monthJoined, int dayJoined, boolean isForeigner, Gender gender) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.dateJoin = dateJoin;
		// this.yearJoined = yearJoined;
		// this.monthJoined = monthJoined;
		// this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;
		
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
	}
	
	/**
	 * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya (grade 1: 3.000.000 per bulan, grade 2: 5.000.000 per bulan, grade 3: 7.000.000 per bulan)
	 * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
	 */

	
	
	//Code Smell satu type  Primitive Obsession
	// Refactoring Megubah tipedata boolean menjadi enum daripada boolean untuk membuat code lebih jeals
	public enum Gender{
		MALE,FEMALE;
	}

	//Menambahkan Enum Grade Menggunakan enum atau class konstan untuk menyimpan nilai gaji berdasarkan grade.
	public enum Grade {
		GRADE_1(3000000),
		GRADE_2(5000000),
		GRADE_3(7000000);
	
		private final int salary;
	
		Grade(int salary) {
			this.salary = salary;
		}
	
		public int getSalary() {
			return salary;
		}
	}


	//Menggubah setMonthlySalary menjadi effective menghindari =  SwitchStatment 
	public void setMonthlySalary(Grade grade) {
        monthlySalary = grade.getSalary();
        if (isForeigner) {
            monthlySalary = (int) (monthlySalary * 1.5);
        }
    }

	//code dibawah mengandug SwitchStatment code bisa dibuat menjadi lebih simpel

	// public void setMonthlySalary(int grade) {	
	// 	if (grade == 1) {
	// 		monthlySalary = 3000000;
	// 		if (isForeigner) {
	// 			monthlySalary = (int) (3000000 * 1.5);
	// 		}
	// 	}else if (grade == 2) {
	// 		monthlySalary = 5000000;
	// 		if (isForeigner) {
	// 			monthlySalary = (int) (3000000 * 1.5);
	// 		}
	// 	}else if (grade == 3) {
	// 		monthlySalary = 7000000;
	// 		if (isForeigner) {
	// 			monthlySalary = (int) (3000000 * 1.5);
	// 		}
	// 	}
	// }
	
	

	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}

	//Menambahkan Mehode baru bernama getMonthsWorkedInYear()

	public int getMonthsWorkedInYear() {
		LocalDate currentDate = LocalDate.now();
		if (dateJoin.getYear() == currentDate.getYear()) {
			return currentDate.getMonthValue() - dateJoin.getMonthValue() + 1;
		}
		return 12; // Jika bergabung tahun sebelumnya, dianggap bekerja 12 bulan dalam tahun ini
	}
	


	// Menyederhanakan metode getAnnualIncomeTax dengan menggunakan hasil dari metode utilitas dan perhitungan yang lebih mudah dibaca
		public int calculateAnnualIncomeTax() {
        	int monthsWorked = getMonthsWorkedInYear();
        	boolean hasSpouse = !spouseIdNumber.isEmpty();
        	return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, hasSpouse, childIdNumbers.size());
   		 }




	//



	// public int getAnnualIncomeTax() {
		
	// 	//Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
	// 	LocalDate date = LocalDate.now();
		
	// 	if (date.getYear() == yearJoined) {
	// 		monthWorkingInYear = date.getMonthValue() - monthJoined;
	// 	}else {
	// 		monthWorkingInYear = 12;

	
	// 	}
		
	// 	return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
	// }


}



///



// package lib;

// import java.time.LocalDate;
// import java.util.ArrayList;
// import java.util.List;

// public class Employee {

//     private String employeeId;
//     private String firstName;
//     private String lastName;
//     private String idNumber;
//     private String address;

//     private LocalDate dateJoined;
//     private boolean isForeigner;
//     private boolean gender;

//     private int monthlySalary;
//     private int otherMonthlyIncome;
//     private int annualDeductible;

//     private String spouseName;
//     private String spouseIdNumber;

//     private List<String> childNames;
//     private List<String> childIdNumbers;

//     public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate dateJoined, boolean isForeigner, boolean gender) {
//         this.employeeId = employeeId;
//         this.firstName = firstName;
//         this.lastName = lastName;
//         this.idNumber = idNumber;
//         this.address = address;
//         this.dateJoined = dateJoined;
//         this.isForeigner = isForeigner;
//         this.gender = gender;

//         childNames = new ArrayList<>();
//         childIdNumbers = new ArrayList<>();
//     }

//     public void setMonthlySalary(Grade grade) {
//         monthlySalary = grade.getSalary();
//         if (isForeigner) {
//             monthlySalary = (int) (monthlySalary * 1.5);
//         }
//     }

//     public void setAnnualDeductible(int deductibleAmount) {
//         this.annualDeductible = deductibleAmount;
//     }

//     public void setOtherMonthlyIncome(int additionalIncome) {
//         this.otherMonthlyIncome = additionalIncome;
//     }

//     public void setSpouse(String spouseName, String spouseIdNumber) {
//         this.spouseName = spouseName;
//         this.spouseIdNumber = spouseIdNumber;
//     }

//     public void addChild(String childName, String childIdNumber) {
//         childNames.add(childName);
//         childIdNumbers.add(childIdNumber);
//     }

//     public int getMonthsWorkedInYear() {
//         LocalDate currentDate = LocalDate.now();
//         if (dateJoined.getYear() == currentDate.getYear()) {
//             return currentDate.getMonthValue() - dateJoined.getMonthValue() + 1;
//         }
//         return 12;
//     }

//     public int calculateAnnualIncomeTax() {
//         int monthsWorked = getMonthsWorkedInYear();
//         boolean hasSpouse = !spouseIdNumber.isEmpty();
//         return TaxCalculator.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorked, annualDeductible, hasSpouse, childIdNumbers.size());
//     }
// }