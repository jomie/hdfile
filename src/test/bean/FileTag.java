package test.bean;

/**
 * FileTag entity. @author MyEclipse Persistence Tools
 */

public class FileTag implements java.io.Serializable {

	// Fields

	private Integer ftid;
	private File file;
	private Tags tags;

	// Constructors

	/** default constructor */
	public FileTag() {
	}

	/** full constructor */
	public FileTag(Integer ftid, File file, Tags tags) {
		this.ftid = ftid;
		this.file = file;
		this.tags = tags;
	}

	// Property accessors

	public Integer getFtid() {
		return this.ftid;
	}

	public void setFtid(Integer ftid) {
		this.ftid = ftid;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Tags getTags() {
		return this.tags;
	}

	public void setTags(Tags tags) {
		this.tags = tags;
	}

}