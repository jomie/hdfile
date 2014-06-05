package test.bean;

import java.util.Date;

/**
 * FileDeleted entity. @author MyEclipse Persistence Tools
 */

public class FileDeleted implements java.io.Serializable {

	// Fields

	private FileDeletedId id;
	private Integer fileid;
	private Short cateid;
	private String filename;
	private Date editionyear;
	private Date sharedate;
	private Short grade;
	private String picpath;
	private String intro;
	private String pdfpath;

	// Constructors

	/** default constructor */
	public FileDeleted() {
	}

	/** minimal constructor */
	public FileDeleted(FileDeletedId id, Integer fileid, Short cateid,
			String filename, Date editionyear, Date sharedate, String picpath,
			String intro, String pdfpath) {
		this.id = id;
		this.fileid = fileid;
		this.cateid = cateid;
		this.filename = filename;
		this.editionyear = editionyear;
		this.sharedate = sharedate;
		this.picpath = picpath;
		this.intro = intro;
		this.pdfpath = pdfpath;
	}

	/** full constructor */
	public FileDeleted(FileDeletedId id, Integer fileid, Short cateid,
			String filename, Date editionyear, Date sharedate, Short grade,
			String picpath, String intro, String pdfpath) {
		this.id = id;
		this.fileid = fileid;
		this.cateid = cateid;
		this.filename = filename;
		this.editionyear = editionyear;
		this.sharedate = sharedate;
		this.grade = grade;
		this.picpath = picpath;
		this.intro = intro;
		this.pdfpath = pdfpath;
	}

	// Property accessors

	public FileDeletedId getId() {
		return this.id;
	}

	public void setId(FileDeletedId id) {
		this.id = id;
	}

	public Integer getFileid() {
		return this.fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}

	public Short getCateid() {
		return this.cateid;
	}

	public void setCateid(Short cateid) {
		this.cateid = cateid;
	}

	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
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