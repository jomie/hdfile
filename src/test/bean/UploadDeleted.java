package test.bean;

/**
 * UploadDeleted entity. @author MyEclipse Persistence Tools
 */

public class UploadDeleted implements java.io.Serializable {

	// Fields

	private Integer delid;
	private Integer upid;
	private Integer userid;
	private Integer bookid;

	// Constructors

	/** default constructor */
	public UploadDeleted() {
	}

	/** full constructor */
	public UploadDeleted(Integer delid, Integer upid, Integer userid,
			Integer bookid) {
		this.delid = delid;
		this.upid = upid;
		this.userid = userid;
		this.bookid = bookid;
	}

	// Property accessors

	public Integer getDelid() {
		return this.delid;
	}

	public void setDelid(Integer delid) {
		this.delid = delid;
	}

	public Integer getUpid() {
		return this.upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getBookid() {
		return this.bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

}