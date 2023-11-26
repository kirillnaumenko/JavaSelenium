package task40;

public class TableModel {
    public String name;
    public String position;
    public String office;
    public int age;
    public String startDate;
    public int salary;

    public TableModel(String name, String position, String office, int age, String startDate, String salary) {
        this.name = name;
        this.position = position;
        this.office = office;
        this.age = age;
        this.startDate = startDate;
        this.salary = this.convertSalaryToInt(salary);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSalary(String salary) {
        this.salary = this.convertSalaryToInt(salary);
    }

    private int convertSalaryToInt(String salaryString) {
        String cleanedString = salaryString.replaceAll("[^0-9]", "");
        int salary = Integer.parseInt(cleanedString);

        return salary;
    }
}
