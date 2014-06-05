package test.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User implements java.io.Serializable {

	// Fields

	private Integer userid;
	private String username;
	private String password;
	private String email;
	private Set favorUserFiles = new HashSet(0);
	private Set comments = new HashSet(0);
	private Set downloads = new HashSet(0);
	private Set uploads = new HashSet(0);
	private Set grades = new HashSet(0);
	private Set fileDeleteds = new HashSet(0);

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

	/** full constructor */
	public User(String username, String password, String email,
			Set favorUserFiles, Set comments, Set downloads, Set uploads,
			Set grades, Set fileDeleteds) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.favorUserFiles = favorUserFiles;
		this.comments = comments;
		this.downloads = downloads;
		this.uploads = uploads;
		this.grades = grades;
		this.fileDeleteds = fileDeleteds;
	}

	// Property accessors

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

	public Set getFavorUserFiles() {
		return this.favorUserFiles;
	}

	public void setFavorUserFiles(Set favorUserFiles) {
		this.favorUserFiles = favorUserFiles;
	}

	public Set getComments() {
		return this.comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}

	public Set getDownloads() {
		return this.downloads;
	}

	public void setDownloads(Set downloads) {
		this.downloads = downloads;
	}

	public Set getUploads() {
		return this.uploads;
	}

	public void setUploads(Set uploads) {
		this.uploads = uploads;
	}

	public Set getGrades() {
		return this.grades;
	}

	public void setGrades(Set grades) {
		this.grades = grades;
	}

	public Set getFileDeleteds() {
		return this.fileDeleteds;
	}

	public void setFileDeleteds(Set fileDeleteds) {
		this.fileDeleteds = fileDeleteds;
	}

}