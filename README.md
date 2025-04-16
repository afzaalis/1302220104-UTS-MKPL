# 💡 Code Smell Report UTS MKPL

- `TaxFunction.java`
- `Employee.java`

---

## 🧾 1. TaxFunction.java

### 🚨 Detected Code Smells:

| Code Smell            | Description |
|------------------------|-------------|
| **Long Method**        | The `calculateTax()` method is too long and combines multiple responsibilities such as income calculation, deduction logic, and tax rules. This violates the Single Responsibility Principle. |
| **Magic Numbers**      | Hardcoded values such as `54000000`, `4500000`, and `1500000` appear without context or constants, making the code harder to maintain and understand. |
| **Duplicate Code**     | Similar tax computation logic appears in both branches of the `isMarried` condition. This repetition can be extracted into a helper method. |
| **Primitive Obsession**| The method uses many primitive types (`int`, `boolean`) for things that could be encapsulated (e.g., marital status, number of children). This reduces readability and testability. |
| **Conditional Complexity** | Nested and repeated conditions decrease readability and increase cognitive load when understanding business logic. |


---

## 🧾 2. Employee.java

### 🚨 Detected Code Smells:

| Code Smell            | Description |
|------------------------|-------------|
| **Long Class**         | The `Employee` class handles too many responsibilities including salary setup, income calculation, family data management, and tax calculation. This should be broken down into multiple classes. |
| **Duplicate Code**     | Salary calculation for each grade has redundant logic. For example, the foreigner salary calculation is repeated in each `if` branch. |
| **Magic Numbers**      | Salary values like `3000000`, `5000000`, and `7000000` are hardcoded. These should be stored as named constants. |
| **Inconsistent Parameter Usage** | In `setSpouse()`, the `spouseIdNumber` is set to `idNumber` instead of the given parameter. This is likely a bug. |
| **Primitive Obsession**| Booleans like `gender` and `isForeigner` would be better represented using enums for clarity and type safety. |


---
