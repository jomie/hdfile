package test.bean;

/**
 * Grade entity. @author MyEclipse Persistence Tools
 */

public class Grade implements java.io.Serializable {

	// Fields

	private Integer gradeid;
	private File file;
	private User user;
	private Short grade;

	// Constructors

	/** default constructor */
	public Grade() {
	}

	/** full constructor */
	public Grade(Integer gradeid, File file, User user, Short grade) {
		this.gradeid = gradeid;
		this.file = file;
		this.user = user;
		this.grade = grade;
	}

	// Property accessors

	public Integer getGradeid() {
		return this.gradeid;
	}

	public void setGradeid(Integer gradeid) {
		this.gradeid = gradeid;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Short getGrade() {
		return this.grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

}