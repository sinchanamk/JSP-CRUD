package model;

public class student
{
	private int usn;
	private String name;
	private String branch;
	private String college;
	
	
	public student(int usn, String name, String branch, String college) {
		super();
		this.usn = usn;
		this.name = name;
		this.branch = branch;
		this.college = college;
	}
	
	public student(String name, String branch, String college) {
		super();
		this.name = name;
		this.branch = branch;
		this.college = college;
	}

	public int getUsn() {
		return usn;
	}
	public void setUsn(int usn) {
		this.usn = usn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	

}
