package ru.job4j.profession;
/**
* Class for doctores.
*/
public class Doctor extends Profession {
    /**
    * @param grade - current grade.
    */
    private String grade;
    /**
    * @param practice - where did he practice.
    */
    private String practice;
    /**
    * Empty constructor, stub.
    */
    public Doctor() {}
    /**
    * Main constructor.
    */
    public Doctor(String name, int experience, String diploma, String speciality) {
	super(name, experience, diploma, speciality);
    }
    /**
    * @return grade - get current grade.
    */
    public String getGrade() {
	return this.grade;
    }
    /**
    * @return practice - get practice info.
    */
    public String getPractice() {
	return this.practice;
    }
    /**
    * @param practice - set practice info.
    */
    public void setPractice(String practice) {
	this.practice = practice;
    }
    /**
    * @param grade - set current grade.
    */
    public void incraseGrade(String grade) {
	this.grade = grade;
    }
    /**
    * @param pacient - cure pacient.
    */
    public void heal(Pacient pacient) {
	System.out.println("Doctor " + getName() + " heals " + pacient.getName());
    }
}