package test.bean;

/**
 * Upload entity. @author MyEclipse Persistence Tools
 */

public class Upload implements java.io.Serializable {

	// Fields

	private Integer upid;
	private File file;
	private User user;

	// Constructors

	/** default constructor */
	public Upload() {
	}

	/** full constructor */
	public Upload(Integer upid, File file, User user) {
		this.upid = upid;
		this.file = file;
		this.user = user;
	}

	// Property accessors

	public Integer getUpid() {
		return this.upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
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

}