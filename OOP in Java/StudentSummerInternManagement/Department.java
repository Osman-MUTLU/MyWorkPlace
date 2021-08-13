public class Department {
    String code;
    String name;
    Instructor[] instructors;
    Training[] trainings;
    public static class Training{
        //Training inner class
        String type;
        String week;

        public Training(String type, String week) {
            this.type = type;
            this.week = week;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }
    }
    public static class Instructor{
        //Instructor inner class
        String title;
        String instructor;

        public Instructor(String title, String instructor) {
            this.title = title;
            this.instructor = instructor;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInstructor() {
            return instructor;
        }

        public void setInstructor(String instructor) {
            this.instructor = instructor;
        }
    }
    //Constructors

    public Department(String code, String name, Instructor[] instructors, Training[] trainings) {
        this.code = code;
        this.name = name;
        this.instructors = instructors;
        this.trainings = trainings;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instructor[] getInstructors() {
        return instructors;
    }

    public void setInstructors(Instructor[] instructors) {
        this.instructors = instructors;
    }

    public Training[] getTrainings() {
        return trainings;
    }

    public void setTrainings(Training[] trainings) {
        this.trainings = trainings;
    }

    public void Display(){

    }
}
