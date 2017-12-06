package ru.job4j.profession;
/**
* Class for engineer.
*/
public class Engineer extends Profession {
    /**
    * Empty constructor, stub.
    */
    public Engineer() { }

    /**
     * Constructor with parameters.
     * @param name - name.
     * @param experience - working time.
     * @param diploma - grade.
     * @param speciality - speciality.
     */
    public Engineer(String name, int experience, String diploma, String speciality) {
	super(name, experience, diploma, speciality);
    }
    /**
    * @param building what building is he constructing.
    */
    public void build(Building building) {
	System.out.println(getName() + " constructing " + building.getName());
    }
    /**
    * @param item - item that need repairing.
    */
    public void repair(Item item) {
	System.out.println(getName() + " repairing " + item.getName());
    }
    /**
    * @param item - item for make.
    */
    public void construct(Item item) {
	System.out.println(getName() + " making " + item.getName());
    }
}