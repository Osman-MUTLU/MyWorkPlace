package Midterm;

public class User {
    private String name;
    private String surname;
    private Date birthday;
    private String gender;
    private Address address;
    private Phone number;
    private int score;
    //For saving score
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User(String name, String surname, Date birthday, String gender, Address address, Phone number) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.gender = gender;
        this.address = address;
        this.number = number;
        this.score = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Phone getNumber() {
        return number;
    }

    public void setNumber(Phone number) {
        this.number = number;
    }
}
