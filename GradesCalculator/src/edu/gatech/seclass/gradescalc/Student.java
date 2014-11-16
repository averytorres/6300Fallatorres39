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
