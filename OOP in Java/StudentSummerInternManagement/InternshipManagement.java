package Homework;
import java.io.*;

public class InternshipManagement {

    static int departmentCounter,internshipCounter;
    static String file_name = "test.txt";
    public static Department[] getDepartments() throws IOException {
        //Getting department list and save all variables , Counting departments and internships in test.txt.
        try{
            internshipCounter = 0;
            departmentCounter =0;
            FileReader fReader = new FileReader(file_name);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            while(line != null){
                String[] dataList = line.split(",");
                if (dataList[0].equals("Department"))departmentCounter++;
                else if(dataList[0].equals("Internship"))internshipCounter++;
                line= bReader.readLine();
            }
            bReader.close();
            fReader.close();
        }catch (Exception e){
            System.err.println("Error : " + e.getMessage());
        }
        //Creating departments list
        Department[] departments = new Department[departmentCounter];
        try {
            FileReader fReader = new FileReader(file_name);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            int counter = 0;
            while(line!=null){
                String[] dataList = line.split(",");
                if (dataList[0].equals("Department")){
                    //Creating Instructors and Trainings
                    Department.Instructor[] instructor = new Department.Instructor[3];
                    Department.Training[] trainings = new Department.Training[3];
                    instructor[0] = new Department.Instructor(dataList[3],dataList[4]);
                    instructor[1] = new Department.Instructor(dataList[5],dataList[6]);
                    instructor[2] = new Department.Instructor(dataList[7],dataList[8]);
                    trainings[0] = new Department.Training(dataList[9],dataList[10]);
                    trainings[1] = new Department.Training(dataList[11],dataList[12]);
                    trainings[2] = new Department.Training(dataList[13],dataList[14]);
                    departments[counter] = new Department(dataList[1],dataList[2],instructor,trainings);
                    counter++;
                }
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        return departments;
    }
    public static Internship[] getInternships(){
        //Getting Internship list and save all variables in test.txt
        Internship[] internships = new Internship[internshipCounter];
        try {
            FileReader fReader = new FileReader(file_name);
            BufferedReader bReader = new BufferedReader(fReader);
            String line = bReader.readLine();
            int counter = 0;
            while(line!=null){
                String[] dataList = line.split(",");
                if(dataList[0].equals("Internship")){
                    internships[counter] = new Internship(dataList[1],dataList[2],dataList[3],dataList[4],dataList[5],dataList[6],dataList[7] ,dataList[8],dataList[9],dataList[10],dataList[11],dataList[12],dataList[13],dataList[14],dataList[15],dataList[16],dataList[17],dataList[18],dataList[19],dataList[20],dataList[21],dataList[22],dataList[23]);
                    counter++;
                }
                line = bReader.readLine();
            }
            bReader.close();
            fReader.close();
        } catch (Exception e) {
            System.err.println("Error : " + e.getMessage());
        }
        return internships;
    }
    public static Student[] getStudents() throws IOException {
        //Getting student list and save all variables
        Department[] departments = getDepartments();
        Internship[] internships = getInternships();
        Student[] students = new Student[internships.length];
        for (int i = 0;i<internships.length;i++){
            String name = internships[i].getStudent_name();
            String number = internships[i].getStudent_number();
            String bird_date = internships[i].getBirthday();
            String gender = internships[i].getGender();
            String classs = internships[i].getClasss();
            Phone student_phone = internships[i].getStudent_Phone();
            String training_type = internships[i].getTraining_type();
            Internship internship = internships[i];
            Department department = null;
            for (int j = 0;j<departments.length;j++){
                if (internships[i].getDepartment_code().equals(departments[j].getCode())){
                    department = departments[j];
                    break;
                }
            }
            students[i] = new Student(name, number, bird_date, gender, classs, student_phone, training_type, internship, department);
        }
        return students;
    }
    public static Company[] getCompanies(Internship[] internships) throws IOException {
        //Getting company list and save all variables in test.txt
        Company[] companies = new Company[internships.length];
        for (int i = 0;i<internships.length;i++){
            String company_name = internships[i].getCompany_name();
            Address address = internships[i].getAddress();
            Phone phone = internships[i].getCompany_Phone();
            String facility_area = internships[i].getFacility_area();
            String contact_person = internships[i].getContact_person();
            companies[i] = new Company(company_name,address,phone,facility_area,contact_person);
        }
        return companies;
    }
    public void Display() throws IOException {
        // Displaying and Main Processing
        Department[] departments = getDepartments();
        Internship[] internships = getInternships();
        Student[] students = getStudents();
        Company[] companies = getCompanies(internships);
        System.out.println("--------- Department List -------");
        for(int i = 0;i<departments.length;i++){
            Department.Training[] trainings = departments[i].getTrainings();
            System.out.println(departments[i].getCode() +" ,"+departments[i].getName() +" ,"+ trainings[0].getType()+ " ,"+trainings[0].getWeek()+" ,"+ trainings[1].getType()+ " ,"+trainings[1].getWeek()+" ,"+ trainings[2].getType()+ " ,"+trainings[2].getWeek());
        }
        System.out.println();
        System.out.println("--------- Instructors List -------");
        for (int i = 0;i<departments.length;i++){
            Department.Instructor[] instructors = departments[i].getInstructors();
            for(int j = 0;j<instructors.length;j++){
                System.out.println(departments[i].getName()+" - " + instructors[j].getTitle()+ " " + instructors[j].getInstructor());
            }
        }
        System.out.println();
        System.out.println("--------- Students List -------");
        for (int i = 0;i<students.length;i++){
            System.out.println(students[i].getName()+ " ,"+students[i].getNumber() +" ,"+students[i].getDepartment().getName()+" ,"+students[i].getBird_date()+" ," + students[i].getGender()+" ,"+ students[i].getStudent_phone().phoneValue() +" ,"+students[i].getTraining_type());
        }
        System.out.println();
        System.out.println("--------- Company List -------");
        for(int i = 0;i<companies.length;i++){
            System.out.println(companies[i].getCompany_name()+" ,"+companies[i].getAddress().AdressName()+", "+companies[i].getPhone().phoneValue()+" ,"+companies[i].getContact_person()+" ,"+ companies[i].getFacility_area());
        }
        System.out.println();
        System.out.println("--------- InternShip List -------");
        for(int i = 0;i<internships.length;i++){
            System.out.println(internships[i].getStudent_number()+" ,"+internships[i].getCompany_name()+" ,"+internships[i].getTraining_type()+" ,"+internships[i].number_of_week());
        }
    }
}
