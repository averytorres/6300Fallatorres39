package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GradesDB {

	private XSSFWorkbook workbook;
	private HashSet<Student> studentInfoDB;

	public GradesDB(String location) {

		studentInfoDB = new HashSet<Student>();
		DBSetUp(location);

	}

	private void DBSetUp(String location) {
		try {

			FileInputStream file = new FileInputStream(new File(location));

			workbook = new XSSFWorkbook(file);
			setUpStudent();
			setUpTeam();
			setUpAttendance();
			setUpAssignments();
			setUpContribs();
			setUpTeamGrades();

		} catch (FileNotFoundException e) {

			System.out.println("Failed To Find The File!");

		} catch (IOException e) {

			System.out.println("Failed To Create Workbook!");
		}
	}

	private void setUpTeamGrades() {

		XSSFSheet sheet = workbook.getSheetAt(5);

		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			if (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String tempTeamName = cell.getStringCellValue();

				ArrayList<String> tempProjectGrades = new ArrayList<String>();
				while (cellIterator.hasNext()) {

					cell = cellIterator.next();
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String tempProjectGrade = cell.getStringCellValue();
					if (!tempProjectGrade.equals("")) {
						tempProjectGrades.add(tempProjectGrade);

					}
				}

				for (Student s : studentInfoDB) {
					if (s.getTeam().equals(tempTeamName)) {
						s.setTeamGrades(tempProjectGrades);

					}
				}

			}
		}

	}

	private void setUpContribs() {

		XSSFSheet sheet = workbook.getSheetAt(4);

		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			if (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String tempPerson = cell.getStringCellValue();
				ArrayList<String> tempContribs = new ArrayList<String>();
				while (cellIterator.hasNext()) {

					cell = cellIterator.next();
					cell.setCellType(Cell.CELL_TYPE_STRING);
					String tempContrib = cell.getStringCellValue();
					if (!tempContrib.equals("")) {
						tempContribs.add(tempContrib);
					}
				}

				for (Student s : studentInfoDB) {
					if (s.getName().equals(tempPerson)) {
						s.setContribs(tempContribs);
					}
				}
			}
		}

	}

	private void setUpAssignments() {

		XSSFSheet sheet = workbook.getSheetAt(3);

		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();

		for (Student s : studentInfoDB) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			ArrayList<String> studentAssignments = new ArrayList<String>();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String tempAssignment = cell.getStringCellValue();
				studentAssignments.add(tempAssignment);
			}
			s.setAssignments(studentAssignments);

		}

	}

	private void setUpAttendance() {

		XSSFSheet sheet = workbook.getSheetAt(2);

		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			Cell cell = cellIterator.next();
			String tempPerson = cell.getStringCellValue();

			cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String tempAtt = cell.getStringCellValue();

			for (Student s : studentInfoDB) {
				if (s.getName().equals(tempPerson)) {
					s.setAttendance(tempAtt);
				}
			}

		}

	}

	private void setUpTeam() {

		XSSFSheet sheet = workbook.getSheetAt(1);

		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			Cell cell = cellIterator.next();
			String tempTeam = cell.getStringCellValue();

			while (cellIterator.hasNext()) {

				cell = cellIterator.next();
				String tempName = cell.getStringCellValue();

				for (Student s : studentInfoDB) {
					if (s.getName().equals(tempName)) {
						s.setTeam(tempTeam);
					}
				}
			}
		}

	}

	private void setUpStudent() {
		XSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();
		rowIterator.next();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();

			Cell cell = cellIterator.next();
			Student temp = new Student();
			temp.setName(cell.getStringCellValue());
			cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			temp.setGtid(cell.getStringCellValue());
			cell = cellIterator.next();
			temp.setEmail(cell.getStringCellValue());
			cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			temp.setC(cell.getStringCellValue());
			cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			temp.setCpp(cell.getStringCellValue());
			cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			temp.setJava(cell.getStringCellValue());

			if (cellIterator.hasNext()) {
				cell = cellIterator.next();
				cell.setCellType(Cell.CELL_TYPE_STRING);
				temp.setCSJobEx(cell.getStringCellValue());

			} else {
				temp.setCSJobEx("");
			}
			studentInfoDB.add(temp);
		}
	}

	public int getNumStudents() {
		return studentInfoDB.size();
	}

	public int getNumAssignments() {
		int assignments = 0;

		for (Student s : studentInfoDB) {
			ArrayList<String> assigns = s.getAssignments();
			if (assigns.size() > assignments) {
				assignments = assigns.size();
			}
		}
		return assignments - 1;
	}

	public int getNumProjects() {
		int projects = 0;

		for (Student s : studentInfoDB) {
			ArrayList<String> projs = s.getTeamGrades();

			if (projs.size() > projects) {
				projects = projs.size();
			}
		}
		return projects;
	}

	public HashSet<Student> getStudents() {

		return studentInfoDB;
	}

	public Student getStudentByName(String string) {
		Student student = new Student();

		for (Student s : studentInfoDB) {
			if (s.getName().equals(string)) {
				student = s;
			}
		}
		return student;
	}

	public Student getStudentByID(String string) {
		Student student = new Student();

		for (Student s : studentInfoDB) {
			if (s.getGtid().equals(string)) {
				student = s;
			}
		}
		return student;
	}

	public static void main(String args[]) {
		GradesDB grades = new GradesDB("DB/GradesDatabase6300.xlsx");
		HashSet<Student> Students = grades.getStudents();
		String bar = getBarLength(Students);
		System.out.println(bar);
		printHeaderToScreen(bar);
		String NOS = "Number of Students:";
		printStudentsNumToScreen(Students, bar, NOS);
		printSpacerToScreen(bar);
		String NOA = "Number of Assignments:";
		printAssignmentsNumToScreen(grades, bar, NOA);
		printSpacerToScreen(bar);
		String NOP = "Number of Projects:";
		printProjectNumToScreen(grades, bar, NOP);
		System.out.println(bar);
		printStudentHeaderToScreen(bar, Students);
		System.out.println(bar);
		
		printStudentIntoToScreen(Students, bar);
		System.out.println(bar);
		System.out.println(bar);
	}

	private static void printStudentIntoToScreen(HashSet<Student> Students,
			String bar) {
		int maxStudentName = findMaxStudentName(Students);
		int maxGTID = findMAXGTID(Students);
		int maxEmail = findMaxEmail(Students);
		int maxAtt = findMaxAttendance(Students);
		
		for (Student s : Students) {
			System.out.print("*  ");
			System.out.print(s.getName());
			for(int i=0;i<maxStudentName-s.getName().length();i++){
				System.out.print(" ");
			}
			System.out.print("*  ");
			
			System.out.print(s.getGtid());
			for(int i=0;i<maxGTID-s.getGtid().length();i++){
				System.out.print(" ");
			}
			System.out.print("*   ");
			
			System.out.print(s.getEmail());
			for(int i=0;i<maxEmail-s.getEmail().length();i++){
				System.out.print(" ");
			}
			System.out.print("*               ");
			
			System.out.print(s.getAttendance());
			for(int i=0;i<bar.length()-maxAtt-maxEmail-maxGTID-maxStudentName-String.valueOf(s.getAttendance()).length()-24;i++){
				System.out.print(" ");
			}
			System.out.print("*");
			System.out.println();
		}
	}

	private static String getBarLength(HashSet<Student> students) {
		int a = findMaxStudentName(students);
		int b = findMAXGTID(students);
		int c = findMaxEmail(students);
		int d = findMaxAttendance(students);
		String bar ="";
		for(int i=0;i<a+b+c+d+40;i++){
			bar=bar+"*";
		}
		return bar;
	}

	private static int findMaxAttendance(HashSet<Student> students) {
		String attendance = "";
		for (Student s : students) {
			if (String.valueOf(s.getAttendance()).length() > attendance.length()) {
				attendance = String.valueOf(s.getAttendance());
			}
		}
		return attendance.length();
	}

	private static int findMaxEmail(HashSet<Student> students) {
		String email = "";
		for (Student s : students) {
			if (s.getEmail().length() > email.length()) {
				email = s.getName();
			}
		}
		return email.length();
	}

	private static void printHeaderToScreen(String bar) {
		System.out.println(bar);
		for (int i = 0; i <= bar.length() - 17; i++) {
			if (i == bar.length() / 3) {
				System.out.print("Grades Calculator");
			} else if (i == 0 || i == bar.length() - 17) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}

		}
		System.out.println();
		System.out.println(bar);
	}

	private static void printStudentsNumToScreen(HashSet<Student> Students,
			String bar, String NOS) {
		for (int i = 0; i <= bar.length() - NOS.length(); i++) {
			if (i == bar.length() / 3) {
				System.out.print(NOS + Students.size());
			} else if (i == 0
					|| i == bar.length() - NOS.length()
							- String.valueOf(Students.size()).length()) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}

		}
		System.out.println();

	}

	private static void printAssignmentsNumToScreen(GradesDB grades,
			String bar, String NOA) {
		for (int i = 0; i <= bar.length() - NOA.length(); i++) {
			if (i == bar.length() / 3) {
				System.out.print(NOA + grades.getNumAssignments());
			} else if (i == 0
					|| i == bar.length()
							- NOA.length()
							- String.valueOf(grades.getNumAssignments())
									.length()) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}

		}
		System.out.println();
	}

	private static void printProjectNumToScreen(GradesDB grades, String bar,
			String message) {
		for (int i = 0; i <= bar.length() - message.length(); i++) {
			if (i == bar.length() / 3) {
				System.out.print(message + grades.getNumProjects());
			} else if (i == 0
					|| i == bar.length() - message.length()
							- String.valueOf(grades.getNumProjects()).length()) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}

		}
		System.out.println();
	}

	private static void printSpacerToScreen(String bar) {
		for (int i = 0; i < bar.length(); i++) {
			if (i == 0 || i == bar.length() - 1) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	private static void printStudentHeaderToScreen(String bar, HashSet<Student> students){
		int maxStudentName = findMaxStudentName(students);
		int maxGTID = findMAXGTID(students);
		int maxEmail = findMaxEmail(students);
		int maxAtt = findMaxAttendance(students);
		for(int i=0;i<=bar.length();i++){
			if(i==0||i==bar.length()-20 || i==maxStudentName || i==maxStudentName+maxGTID || i==maxStudentName+maxGTID+maxEmail){
				System.out.print("*");
			}
			else if(i==maxStudentName/2){
				System.out.print("Name");
			}
			else if(i==(maxGTID/2)+maxStudentName){
				System.out.print("GTID");
			}
			else if(i==(maxEmail/2)+maxStudentName+maxGTID){
				System.out.print("Email");
			}
			else if(i==(maxAtt/2)+maxEmail+maxGTID+maxStudentName+10){
				System.out.print("Attendance");
			}
			else{
				System.out.print(" ");
			}
		}
		System.out.println();
	}

	private static int findMAXGTID(HashSet<Student> students) {
		String GTID = "";
		for (Student s : students) {
			if (s.getGtid().length() > GTID.length()) {
				GTID = s.getName();
			}
		}
		return GTID.length();
	}

	private static int findMaxStudentName(HashSet<Student> students) {
		String name = "";
		for (Student s : students) {
			if (s.getName().length() > name.length()) {
				name = s.getName();
			}
		}
		return name.length();
	}

}
