package edu.gatech.seclass.gradescalc;

import java.util.ArrayList;

public class Student {

	private String name;
	private String Gtid;
	private String Email;
	private String attendance;
	private String C;
	private String Cpp;
	private String Java;
	private String CSJobEx;
	private String Team;
	private ArrayList<String> Assignments;
	private ArrayList<String> Contribs;
	private ArrayList<String> TeamGrades;
	
	public Student(){
		Assignments = new ArrayList<String>();
	}

	
	public Student(String string, String string2, GradesDB db) {
		Student temp = db.getStudentByName(string);
		this.name = temp.getName();
		this.Gtid = temp.getGtid();
		this.Email = temp.getEmail();
		this.attendance = String.valueOf(temp.getAttendance());
		this.C = temp.getC();
		this.Cpp = temp.getCpp();
		this.Java = temp.getJava();
		this.CSJobEx = temp.getCSJobEx();
		this.Team = temp.getTeam();
		this.Assignments = temp.getAssignments();
		this.Contribs = temp.getContribs();
		this.TeamGrades = temp.getTeamGrades();
	}


	public Student(String string, String string2, String string3,
			String string4, String string5, String string6, String string7,
			GradesDB db) {
		// TODO Auto-generated constructor stub
	}


	public ArrayList<String> getAssignments() {
		return Assignments;
	}


	public void setAssignments(ArrayList<String> assignments) {
		Assignments = assignments;
	}


	public String getTeam() {
		return Team;
	}

	public void setTeam(String team) {
		Team = team;
	}

	public String getC() {
		return C;
	}

	public String getCpp() {
		return Cpp;
	}

	public String getJava() {
		return Java;
	}

	public String getCSJobEx() {
		return CSJobEx;
	}

	public void setC(String c) {
		C = c;
	}

	public void setCpp(String cpp) {
		Cpp = cpp;
	}

	public void setJava(String java) {
		Java = java;
	}

	public void setCSJobEx(String cSJobEx) {
		CSJobEx = cSJobEx;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getGtid() {
		return Gtid;
	}
	
	public void setGtid(String d) {
		Gtid = d;
	}
	
	public String getEmail() {
		return Email;
	}
	
	public void setEmail(String email) {
		Email = email;
	}
	
	public int getAttendance() {
		int i = (int) Math.ceil(Double.parseDouble(attendance));
		return i;
		
	}
	
	public void setAttendance(String attendance) {
		this.attendance = attendance;
	}


	public ArrayList<String> getContribs() {
		return Contribs;
	}


	public void setContribs(ArrayList<String> contribs) {
		Contribs = contribs;
	}


	public ArrayList<String> getTeamGrades() {
		return TeamGrades;
	}


	public void setTeamGrades(ArrayList<String> teamGrades) {
		TeamGrades = teamGrades;
	}
	
}
