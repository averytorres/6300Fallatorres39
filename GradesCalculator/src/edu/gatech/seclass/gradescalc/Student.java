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
	private GradesDB db;

	public Student() {
		Assignments = new ArrayList<String>();
	}

	public Student(String string, String string2, GradesDB db) {
		Student temp = db.getStudentByName(string);
		this.db =db;
		this.name = temp.name;
		this.Gtid = temp.Gtid;
		this.Email = temp.Email;
		this.attendance = String.valueOf(temp.attendance);
		this.C = temp.C;
		this.Cpp = temp.Cpp;
		this.Java = temp.Java;
		this.CSJobEx = temp.CSJobEx;
		this.Team = temp.Team;
		this.Assignments = temp.Assignments;
		this.Contribs = temp.Contribs;
		this.TeamGrades = temp.TeamGrades;
	}

	@Override
	public boolean equals(Object compare) {
		return this.name.equals(((Student) compare).getName());
	}

	@Override
	public int hashCode() {

		return Integer.valueOf(name.charAt(0));
	}

	public Student(String string, String string2, String string3,
			String string4, String string5, String string6, String string7,
			GradesDB db) {
		Student temp = db.getStudentByName(string);
		this.db =db;
		this.name = temp.name;
		this.Gtid = temp.Gtid;
		this.Email = temp.Email;
		this.attendance = String.valueOf(temp.attendance);
		this.C = temp.C;
		this.Cpp = temp.Cpp;
		this.Java = temp.Java;
		this.CSJobEx = temp.CSJobEx;
		this.Team = temp.Team;
		this.Assignments = temp.Assignments;
		this.Contribs = temp.Contribs;
		this.TeamGrades = temp.TeamGrades;
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
		int i = (int) Math.round(Double.parseDouble(attendance));
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

	public String getAverageAssignmentsGrade() {
		int sum =0;
		int avg=0;
		for(String a:Assignments){
			sum=sum+Integer.parseInt(a);
		}
		avg=sum/Assignments.size();
		return String.valueOf(avg);
	}

	public String getAverageProjectsGrade() {
		double projGrades[] = new double[Contribs.size()];
		double sum =0;
		double avg=0;
		
		for(int i =0;i<projGrades.length;i++){
			projGrades[i] = (Double.parseDouble(Contribs.get(i))/100)*Integer.parseInt(TeamGrades.get(i));
		}
		for(double a:projGrades){
			sum=sum+a;
		}
		avg=sum/projGrades.length;
		return String.valueOf(Math.round(avg));
	}

	public String getOverallGrade() {

		return String.valueOf(db.getOverallGrade(this));
	}

	public void setDB(GradesDB gradesDB) {
		this.db=gradesDB;
		
	}

}
