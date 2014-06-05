package test.bean;

/**
 * UserDeleted entity. @author MyEclipse Persistence Tools
 */

public class UserDeleted implements java.io.Serializable {

	// Fields

	private Integer delid;
	private Integer userid;
	private String username;
	private String password;
	private String email;

	// Constructors

	/** default constructor */
	public UserDeleted() {
	}

	/** full constructor */
	public UserDeleted(Integer delid, Integer userid, String username,
			String password, String email) {
		this.delid = delid;
		this.userid = userid;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	// Property accessors

	public Integer getDelid() {
		return this.delid;
	}

	public void setDelid(Integer delid) {
		this.delid = delid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}