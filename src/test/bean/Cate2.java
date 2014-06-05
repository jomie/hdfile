package test.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Cate2 entity. @author MyEclipse Persistence Tools
 */

public class Cate2 implements java.io.Serializable {

	// Fields

	private Short cateid;
	private Cate1 cate1;
	private String catename;
	private Set files = new HashSet(0);

	// Constructors

	/** default constructor */
	public Cate2() {
	}

	/** minimal constructor */
	public Cate2(Short cateid) {
		this.cateid = cateid;
	}

	/** full constructor */
	public Cate2(Short cateid, Cate1 cate1, String catename, Set files) {
		this.cateid = cateid;
		this.cate1 = cate1;
		this.catename = catename;
		this.files = files;
	}

	// Property accessors

	public Short getCateid() {
		return this.cateid;
	}

	public void setCateid(Short cateid) {
		this.cateid = cateid;
	}

	public Cate1 getCate1() {
		return this.cate1;
	}

	public void setCate1(Cate1 cate1) {
		this.cate1 = cate1;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public Set getFiles() {
		return this.files;
	}

	public void setFiles(Set files) {
		this.files = files;
	}

}