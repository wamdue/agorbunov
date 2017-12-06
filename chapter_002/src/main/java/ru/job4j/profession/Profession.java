package ru.job4j.profession;
/**
* Main class for all professions.
*/
public class Profession {
    /**
    * @param name - Fio of an expert.
    */
    private String name;
    /**
    * @param speciality - in what area is expert.
    */
    private String speciality;
    /**
    * @param diploma - diploma.
    */
    private String diploma;
    /**
    * @param experience - how long works.
    */
    private int experience;
    /**
    * Empty constructor, stub.
    */
    public Profession() { }

    /**
     * Constructor with fields.
     * @param name - name.
     * @param experience - working time.
     * @param diploma - grade.
     * @param speciality - speciality.
     */
    public Profession(String name, int experience, String diploma, String speciality) {
	this.name = name;
	this.experience = experience;
	this.diploma = diploma;
	this.speciality = speciality;
    }
    /**
    * @return name.
    */
    public String getName() {
	return this.name;
    }
    /**
    * @return diploma.
    */
    public String getDiploma() {
	return this.diploma;
    }
    /**
    * @return speciality.
    */
    public String getSpeciality() {
	return this.speciality;
    }
    /**
    * @return experience.
    */
    public int getExperience() {
	return this.experience;
    }
    /**
    * @param name - set correct or new fio.
    */
    public void setName(String name) {
	this.name = name;
    }
    /**
    * @param speciality - set correct or new speciality.
    */
    public void setSpeciality(String speciality) {
	this.speciality = speciality;
    }
    /**
    * @param diploma - set correct or new diploma.
    */
    public void setDiploma(String diploma) {
	this.diploma = diploma;
    }
    /**
    * @param experience - set correct or new experience.
    */
    public void setExperience(int experience) {
	this.experience = experience;
    }
}