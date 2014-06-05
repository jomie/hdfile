package test.bean;

import java.util.Date;

/**
 * Download entity. @author MyEclipse Persistence Tools
 */

public class Download implements java.io.Serializable {

	// Fields

	private Integer downid;
	private File file;
	private User user;
	private Date downdate;

	// Constructors

	/** default constructor */
	public Download() {
	}

	/** full constructor */
	public Download(File file, User user, Date downdate) {
		this.file = file;
		this.user = user;
		this.downdate = downdate;
	}

	// Property accessors

	public Integer getDownid() {
		return this.downid;
	}

	public void setDownid(Integer downid) {
		this.downid = downid;
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

	public Date getDowndate() {
		return this.downdate;
	}

	public void setDowndate(Date downdate) {
		this.downdate = downdate;
	}

}