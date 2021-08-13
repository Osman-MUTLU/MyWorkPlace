package Homework;

public class Internship {
    String department_code;
    String student_number;
    String student_name;
    String gender;
    String birthday;
    String classs;
    String student_phone_country_code,student_phone_area_code,student_phone_number;
    String training_type;
    String start_date;
    String end_date;
    String company_name;
    String street,no,town,city,country;
    String phone_country_code,phone_area_code,phone_number;
    String facility_area,contact_person;

    public String getDepartment_code() {
        return department_code;
    }

    public void setDepartment_code(String department_code) {
        this.department_code = department_code;
    }

    public String getStudent_number() {
        return student_number;
    }

    public void setStudent_number(String student_number) {
        this.student_number = student_number;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getStudent_phone_country_code() {
        return student_phone_country_code;
    }

    public void setStudent_phone_country_code(String student_phone_country_code) {
        this.student_phone_country_code = student_phone_country_code;
    }

    public String getStudent_phone_area_code() {
        return student_phone_area_code;
    }

    public void setStudent_phone_area_code(String student_phone_area_code) {
        this.student_phone_area_code = student_phone_area_code;
    }

    public String getStudent_phone_number() {
        return student_phone_number;
    }

    public void setStudent_phone_number(String student_phone_number) {
        this.student_phone_number = student_phone_number;
    }

    public String getTraining_type() {
        return training_type;
    }

    public void setTraining_type(String training_type) {
        this.training_type = training_type;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone_country_code() {
        return phone_country_code;
    }

    public void setPhone_country_code(String phone_country_code) {
        this.phone_country_code = phone_country_code;
    }

    public String getPhone_area_code() {
        return phone_area_code;
    }

    public void setPhone_area_code(String phone_area_code) {
        this.phone_area_code = phone_area_code;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getFacility_area() {
        return facility_area;
    }

    public void setFacility_area(String facility_area) {
        this.facility_area = facility_area;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public Internship(String department_code, String student_number, String student_name, String gender, String birthday, String classs, String student_phone_country_code, String student_phone_area_code, String student_phone_number, String training_type, String start_date, String end_date, String company_name, String street, String no, String town, String city, String country, String phone_country_code, String phone_area_code, String phone_number, String facility_area, String contact_person) {
        this.department_code = department_code;
        this.student_number = student_number;
        this.student_name = student_name;
        this.gender = gender;
        this.birthday = birthday;
        this.classs = classs;
        this.student_phone_country_code = student_phone_country_code;
        this.student_phone_area_code = student_phone_area_code;
        this.student_phone_number = student_phone_number;
        this.training_type = training_type;
        this.start_date = start_date;
        this.end_date = end_date;
        this.company_name = company_name;
        this.street = street;
        this.no = no;
        this.town = town;
        this.city = city;
        this.country = country;
        this.phone_country_code = phone_country_code;
        this.phone_area_code = phone_area_code;
        this.phone_number = phone_number;
        this.facility_area = facility_area;
        this.contact_person = contact_person;
    }
    public Phone getStudent_Phone(){
        Phone phone= new Phone(getStudent_phone_country_code(),getStudent_phone_area_code(),getStudent_phone_number());
        return phone;
    }
    public Phone getCompany_Phone(){
        Phone phone= new Phone(getPhone_country_code(),getPhone_area_code(),getPhone_number());
        return phone;
    }
    public Address getAddress(){
        Address address = new Address(getStreet(),getNo(),getTown(),getCity(),getCountry());
        return address;
    }
    public Date EndDate(){
        String[] array = getEnd_date().split("\\.");
        Date date = new Date(array[0],array[1],array[2]);
        return date;
    }
    public Date StartDate(){
        String[] array = getStart_date().split("\\.");

        Date date = new Date(array[0],array[1],array[2]);
        return date;
    }
    public String number_of_week(){ // Calculate to number of week
        int year1 =Integer.parseInt(EndDate().getYear());
        int year2 =Integer.parseInt(StartDate().getYear());
        int month1 =Integer.parseInt(EndDate().getMonth());
        int month2 =Integer.parseInt(StartDate().getMonth());
        int day1 =Integer.parseInt(EndDate().getDay());
        int day2 =Integer.parseInt(StartDate().getDay());
        int sum = Math.abs(year1-year2)*52+Math.abs(month1-month2)*4+Math.abs(day1-day2)/7;
        String number_of_week = String.valueOf(sum);
        return number_of_week;
    }
}
