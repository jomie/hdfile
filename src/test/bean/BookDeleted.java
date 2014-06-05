package test.bean;

import java.util.Date;

/**
 * BookDeleted entity. @author MyEclipse Persistence Tools
 */

public class BookDeleted implements java.io.Serializable {

	// Fields

	private Integer delid;
	private Integer bookid;
	private Short cateid;
	private String bookname;
	private Date editionyear;
	private Date sharedate;
	private Short grade;
	private String picpath;
	private String intro;
	private String pdfpath;

	// Constructors

	/** default constructor */
	public BookDeleted() {
	}

	/** minimal constructor */
	public BookDeleted(Integer delid, Integer bookid, Short cateid,
			String bookname, Date editionyear, Date sharedate, String picpath,
			String intro, String pdfpath) {
		this.delid = delid;
		this.bookid = bookid;
		this.cateid = cateid;
		this.bookname = bookname;
		this.editionyear = editionyear;
		this.sharedate = sharedate;
		this.picpath = picpath;
		this.intro = intro;
		this.pdfpath = pdfpath;
	}

	/** full constructor */
	public BookDeleted(Integer delid, Integer bookid, Short cateid,
			String bookname, Date editionyear, Date sharedate, Short grade,
			String picpath, String intro, String pdfpath) {
		this.delid = delid;
		this.bookid = bookid;
		this.cateid = cateid;
		this.bookname = bookname;
		this.editionyear = editionyear;
		this.sharedate = sharedate;
		this.grade = grade;
		this.picpath = picpath;
		this.intro = intro;
		this.pdfpath = pdfpath;
	}

	// Property accessors

	public Integer getDelid() {
		return this.delid;
	}

	public void setDelid(Integer delid) {
		this.delid = delid;
	}

	public Integer getBookid() {
		return this.bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public Short getCateid() {
		return this.cateid;
	}

	public void setCateid(Short cateid) {
		this.cateid = cateid;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public Date getEditionyear() {
		return this.editionyear;
	}

	public void setEditionyear(Date editionyear) {
		this.editionyear = editionyear;
	}

	public Date getSharedate() {
		return this.sharedate;
	}

	public void setSharedate(Date sharedate) {
		this.sharedate = sharedate;
	}

	public Short getGrade() {
		return this.grade;
	}

	public void setGrade(Short grade) {
		this.grade = grade;
	}

	public String getPicpath() {
		return this.picpath;
	}

	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getPdfpath() {
		return this.pdfpath;
	}

	public void setPdfpath(String pdfpath) {
		this.pdfpath = pdfpath;
	}

}