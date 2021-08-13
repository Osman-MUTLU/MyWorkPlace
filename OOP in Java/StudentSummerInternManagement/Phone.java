package Homework;

public class Phone {
    //Phone class for student and,company
    String phone_country_code,phone_area_code,phone_number;

    public Phone(String phone_country_code, String phone_area_code, String phone_number) {
        this.phone_country_code = phone_country_code;
        this.phone_area_code = phone_area_code;
        this.phone_number = phone_number;
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
    public String phoneValue(){ // Phone adding for displaying
        String phone = getPhone_country_code()+" "+getPhone_area_code()+ " "+getPhone_number();
        return phone;
    }
}
