public class Student {
    String name;
    String number;
    String bird_date;
    String gender;
    String classs;
    Phone student_phone;
    String training_type;
    Internship internship;
    Department department;

    public Student(String name, String number, String bird_date, String gender, String classs, Phone student_phone, String training_type, Internship internship, Department department) {
        this.name = name;
        this.number = number;
        this.bird_date = bird_date;
        this.gender = gender;
        this.classs = classs;
        this.student_phone = student_phone;
        this.training_type = training_type;
        this.internship = internship;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBird_date() {
        return bird_date;
    }

    public void setBird_date(String bird_date) {
        this.bird_date = bird_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public Phone getStudent_phone() {
        return student_phone;
    }

    public void setStudent_phone(Phone student_phone) {
        this.student_phone = student_phone;
    }

    public String getTraining_type() {
        return training_type;
    }

    public void setTraining_type(String training_type) {
        this.training_type = training_type;
    }

    public Internship getInternship() {
        return internship;
    }

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
