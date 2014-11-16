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

	
}
