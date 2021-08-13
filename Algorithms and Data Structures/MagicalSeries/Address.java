package Midterm;

public class Address {
    private String country;
    private String city;
    private String district;
    private String address;

    public Address(String country, String city, String district, String address) {
        this.country = country;
        this.city = city;
        this.district = district;
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
