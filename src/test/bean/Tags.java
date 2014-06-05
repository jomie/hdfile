package test.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Tags entity. @author MyEclipse Persistence Tools
 */

public class Tags implements java.io.Serializable {

	// Fields

	private Integer tagid;
	private String tagname;
	private Set fileTags = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tags() {
	}

	/** minimal constructor */
	public Tags(Integer tagid) {
		this.tagid = tagid;
	}

	/** full constructor */
	public Tags(Integer tagid, String tagname, Set fileTags) {
		this.tagid = tagid;
		this.tagname = tagname;
		this.fileTags = fileTags;
	}

	// Property accessors

	public Integer getTagid() {
		return this.tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}

	public String getTagname() {
		return this.tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public Set getFileTags() {
		return this.fileTags;
	}

	public void setFileTags(Set fileTags) {
		this.fileTags = fileTags;
	}

}