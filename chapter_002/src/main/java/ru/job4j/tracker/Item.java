package ru.job4j.tracker;
/**
* Class for store information about task.
*/
public class Item {
    /**
    * @param id - number of task.
    */
    private String id;
    /**
    * @param name - name of the task.
    */
    private String name;
    /**
    * @param desc - description of the task.
    */
    private String desc;
    /**
    * @param comments - history of the comments.
    */
    private String[] comments;
    /**
    * @param created - time of creation in mls.
    */
    private long created;
    /**
    * Empty constructor.
    */
    public Item() {}
    /**
    * Main constructor.
    * @param name - name.
    * @param desc - description.
    * @param comments - array of comments.
    */
    public Item(String name, String desc, String[] comments) {
	this.name = name;
	this.desc = desc;
	this.comments = comments;
    }
    /**
    * @return id - returns id.
    */
    public String getId() {
	return id;
    }
    /**
    * @return name - returns name.
    */
    public String getName() {
	return name;
    }
    /**
    * @return desc - returns description.
    */
    public String getDesc() {
	return desc;
    }
    /**
    * @return comments - returns array of comments.
    */
    public String[] getComments() {
	return comments;
    }
    /**
    * @return created - returns time of creation.
    */
    public long getCreated() {
	return created;
    }
    /**
    * @param id - set id of the task.
    */
    public void setId(String id) {
	this.id = id;
    }
    /**
    * @param name - set new name of the task.
    */
    public void setName(String name) {
	this.name = name;
    }
    /**
    * @param description - set new description.
    */
    public void setDesc(String desc) {
	this.desc = desc;
    }
    /**
    * @param comments - set new comments.
    */
    public void setComments(String[] comments) {
	this.comments = comments;
    }
    /**
    * @param created - set new time of creation.
    */
    public void setCreated(long created) {
	this.created = created;
    }
}