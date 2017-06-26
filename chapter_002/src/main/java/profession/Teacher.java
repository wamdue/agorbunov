package profession;
/**
* Class for teachers.
*/
public class Teacher extends Profession {
    /**
    * @param college - where ge studied.
    */
    private String college;
    /**
    * @param grade;
    */
    private String grade;
    /**
    * @param age.
    */
    private int age;
    /**
    * Empty constructor, stub;
    */
    public Teacher() {}
    /**
    * Main constructor.
    */
    public Teacher(String name, int experience, String diploma, String speciality) {
	super(name, experience, diploma, speciality);
    }
    /**
    * @return college - college name.
    */
    public String getCollege() {
	return this.college;
    }
    /**
    * @return grade - current grade.
    */
    public String getGrade() {
	return this.grade;
    }
    /**
    * @return age - current age.
    */
    public int getAge() {
	return this.age;
    }
    /**
    * @param college - set current college.
    */
    public void setCollege(String college) {
	this.college = college;
    }
    /**
    * @param age - set current age.
    */
    public void setAge(int age) {
	this.age = age;
    }
    /**
    * @param student - current student.
    */
    public void teach(Student student) {
	System.out.println(getName() + " teaching " + student.getName());
    }
    /**
    * @param grade - set current grade.
    */
    public void incraseGrade(String grade) {
	this.grade = grade;
    }
}