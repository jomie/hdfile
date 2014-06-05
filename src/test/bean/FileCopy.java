//package test.bean;
//
//import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * File entity. @author MyEclipse Persistence Tools
// */
//
//public class FileCopy implements java.io.Serializable {
//
//	// Fields
//
//	private Integer fileid;
//	private Cate2 cate2;
//	private String filename;
//	private Date editionyear;
//	private Date sharedate;
//	private Short grade;
//	private String picpath;
//	private String intro;
//	private String filepath;
//	private Short isPrivate;
//	private Short isPrivateOrigin;
//	private Set fileTags = new HashSet(0);
//	private Set favorUserFiles = new HashSet(0);
//	private Set grades = new HashSet(0);
//	private Set uploads = new HashSet(0);
//	private Set comments = new HashSet(0);
//	//self
//	private boolean isCurUserFavor = false;
//	private boolean hasGraded = false;
//	// Constructors
//
//	/** default constructor */
//	public FileCopy() {
//	}
//
//	/** minimal constructor */
//	public FileCopy(String filename, Date sharedate, String filepath,
//			Short isPrivate, Short isPrivateOrigin) {
//		this.filename = filename;
//		this.sharedate = sharedate;
//		this.filepath = filepath;
//		this.isPrivate = isPrivate;
//		this.isPrivateOrigin = isPrivateOrigin;
//	}
//
//	/** full constructor */
//	public FileCopy(Cate2 cate2, String filename, Date editionyear, Date sharedate,
//			Short grade, String picpath, String intro, String filepath,
//			Short isPrivate, Short isPrivateOrigin, Set fileTags,
//			Set favorUserFiles, Set grades, Set uploads, Set comments) {
//		this.cate2 = cate2;
//		this.filename = filename;
//		this.editionyear = editionyear;
//		this.sharedate = sharedate;
//		this.grade = grade;
//		this.picpath = picpath;
//		this.intro = intro;
//		this.filepath = filepath;
//		this.isPrivate = isPrivate;
//		this.isPrivateOrigin = isPrivateOrigin;
//		this.fileTags = fileTags;
//		this.favorUserFiles = favorUserFiles;
//		this.grades = grades;
//		this.uploads = uploads;
//		this.comments = comments;
//	}
//
//	// Property accessors
//
//	public Integer getFileid() {
//		return this.fileid;
//	}
//
//	public void setFileid(Integer fileid) {
//		this.fileid = fileid;
//	}
//
//	public Cate2 getCate2() {
//		return this.cate2;
//	}
//
//	public void setCate2(Cate2 cate2) {
//		this.cate2 = cate2;
//	}
//
//	public String getFilename() {
//		return this.filename;
//	}
//
//	public void setFilename(String filename) {
//		this.filename = filename;
//	}
//
//	public Date getEditionyear() {
//		return this.editionyear;
//	}
//
//	public void setEditionyear(Date editionyear) {
//		this.editionyear = editionyear;
//	}
//
//	public Date getSharedate() {
//		return this.sharedate;
//	}
//
//	public void setSharedate(Date sharedate) {
//		this.sharedate = sharedate;
//	}
//
//	public Short getGrade() {
//		return this.grade;
//	}
//
//	public void setGrade(Short grade) {
//		this.grade = grade;
//	}
//
//	public String getPicpath() {
//		return this.picpath;
//	}
//
//	public void setPicpath(String picpath) {
//		this.picpath = picpath;
//	}
//
//	public String getIntro() {
//		return this.intro;
//	}
//
//	public void setIntro(String intro) {
//		this.intro = intro;
//	}
//
//	public String getFilepath() {
//		return this.filepath;
//	}
//
//	public void setFilepath(String filepath) {
//		this.filepath = filepath;
//	}
//
//	public Short getIsPrivate() {
//		return this.isPrivate;
//	}
//
//	public void setIsPrivate(Short isPrivate) {
//		this.isPrivate = isPrivate;
//	}
//
//	public Short getIsPrivateOrigin() {
//		return this.isPrivateOrigin;
//	}
//
//	public void setIsPrivateOrigin(Short isPrivateOrigin) {
//		this.isPrivateOrigin = isPrivateOrigin;
//	}
//
//	public Set getFileTags() {
//		return this.fileTags;
//	}
//
//	public void setFileTags(Set fileTags) {
//		this.fileTags = fileTags;
//	}
//
//	public Set getFavorUserFiles() {
//		return this.favorUserFiles;
//	}
//
//	public void setFavorUserFiles(Set favorUserFiles) {
//		this.favorUserFiles = favorUserFiles;
//	}
//
//	public Set getGrades() {
//		return this.grades;
//	}
//
//	public void setGrades(Set grades) {
//		this.grades = grades;
//	}
//
//	public Set getUploads() {
//		return this.uploads;
//	}
//
//	public void setUploads(Set uploads) {
//		this.uploads = uploads;
//	}
//
//	public Set getComments() {
//		return this.comments;
//	}
//
//	public void setComments(Set comments) {
//		this.comments = comments;
//	}
//
//	public boolean isCurUserFavor() {
//		return isCurUserFavor;
//	}
//
//	public void setCurUserFavor(boolean isCurUserFavor) {
//		this.isCurUserFavor = isCurUserFavor;
//	}
//
//	public boolean isHasGraded() {
//		return hasGraded;
//	}
//
//	public void setHasGraded(boolean hasGraded) {
//		this.hasGraded = hasGraded;
//	}
//
//}