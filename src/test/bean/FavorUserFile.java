package test.bean;

/**
 * FavorUserFile entity. @author MyEclipse Persistence Tools
 */

public class FavorUserFile implements java.io.Serializable {

	// Fields

	private Integer fufid;
	private File file;
	private User user;

	// Constructors

	/** default constructor */
	public FavorUserFile() {
	}

	/** full constructor */
	public FavorUserFile(Integer fufid, File file, User user) {
		this.fufid = fufid;
		this.file = file;
		this.user = user;
	}

	// Property accessors

	public Integer getFufid() {
		return this.fufid;
	}

	public void setFufid(Integer fufid) {
		this.fufid = fufid;
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