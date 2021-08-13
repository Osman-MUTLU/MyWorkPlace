public class Address {
    String street,no,town,city,country;

    public Address(String street, String no, String town, String city, String country) {
        this.street = street;
        this.no = no;
        this.town = town;
        this.city = city;
        this.country = country;
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
    public String AdressName(){//Addresses adding for displaying
        String adress = getCity() +" ," +getCountry()+" ,"+ getNo()+" ,"+getStreet()+" ,"+getTown();
        return adress;
    }
}
