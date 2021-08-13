public class Company {
    String company_name;
    Address address;
    Phone phone;
    String facility_area;
    String contact_person;

    public Company(String company_name, Address address, Phone phone, String facility_area, String contact_person) {
        this.company_name = company_name;
        this.address = address;
        this.phone = phone;
        this.facility_area = facility_area;
        this.contact_person = contact_person;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
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
}
