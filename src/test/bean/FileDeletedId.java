package test.bean;

/**
 * FileDeletedId entity. @author MyEclipse Persistence Tools
 */

public class FileDeletedId implements java.io.Serializable {

	// Fields

	private Integer delid;
	private User user;

	// Constructors

	/** default constructor */
	public FileDeletedId() {
	}

	/** full constructor */
	public FileDeletedId(Integer delid, User user) {
		this.delid = delid;
		this.user = user;
	}

	// Property accessors

	public Integer getDelid() {
		return this.delid;
	}

	public void setDelid(Integer delid) {
		this.delid = delid;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FileDeletedId))
			return false;
		FileDeletedId castOther = (FileDeletedId) other;

		return ((this.getDelid() == castOther.getDelid()) || (this.getDelid() != null
				&& castOther.getDelid() != null && this.getDelid().equals(
				castOther.getDelid())))
				&& ((this.getUser() == castOther.getUser()) || (this.getUser() != null
						&& castOther.getUser() != null && this.getUser()
						.equals(castOther.getUser())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getDelid() == null ? 0 : this.getDelid().hashCode());
		result = 37 * result
				+ (getUser() == null ? 0 : this.getUser().hashCode());
		return result;
	}

}