package test.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * Cate1 entity. @author MyEclipse Persistence Tools
 */

public class Cate1 implements java.io.Serializable {

	// Fields

	private Short cateid;
	private String catename;
	private Set cate2s = new HashSet(0);

	// Constructors

	/** default constructor */
	public Cate1() {
	}

	/** minimal constructor */
	public Cate1(Short cateid, String catename) {
		this.cateid = cateid;
		this.catename = catename;
	}

	/** full constructor */
	public Cate1(Short cateid, String catename, Set cate2s) {
		this.cateid = cateid;
		this.catename = catename;
		this.cate2s = cate2s;
	}

	// Property accessors

	public Short getCateid() {
		return this.cateid;
	}

	public void setCateid(Short cateid) {
		this.cateid = cateid;
	}

	public String getCatename() {
		return this.catename;
	}

	public void setCatename(String catename) {
		this.catename = catename;
	}

	public Set getCate2s() {
		return this.cate2s;
	}

	public void setCate2s(Set cate2s) {
		this.cate2s = cate2s;
	}

}