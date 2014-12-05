package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GradesDB {

	private XSSFWorkbook workbook;
	private HashSet<Student> studentInfoDB;
	private String location;
	private String formula;

	// public GradesDB(String location) {
	// this.location = location;
	// studentInfoDB = new HashSet<Student>();
	// DBSetUp(location);
	//
	// }

	public GradesDB() {
		String location = "DB" + File.separator + "GradesDatabase6300.xlsx";
		this.formula = "AT * 0.2 + AS * 0.4 + PR * 0.4";
		loadSpreadsheet(location);
	}

	public void loadSpreadsheet(String gradesDb) {
		this.location = gradesDb;
		studentInfoDB = new HashSet<Student>();
		DBSetUp(location);

	}

	private void DBSetUp(String location) {
		try {
			this.location = location;

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
			Cell cell = cellIterator.next();
			cell.setCellType(Cell.CELL_TYPE_STRING);
			String tempStudent = cell.getStringCellValue();
			s = this.getStudentByName(tempStudent);
			while (cellIterator.hasNext()) {
				cell = cellIterator.next();
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
			temp.setDB(this);
			studentInfoDB.add(temp);
		}
	}

	public int getNumStudents() {
		return studentInfoDB.size();
	}

	public int getNumAssignments() {

		XSSFSheet sheet = workbook.getSheetAt(3);
		Row r = sheet.getRow(0);
		// System.out.println();
		// int assignments = 0;
		//
		// for (Student s : studentInfoDB) {
		// ArrayList<String> assigns = s.getAssignments();
		// if (assigns.size() > assignments) {
		// assignments = assigns.size();
		// }
		// }
		return r.getLastCellNum() - 1;
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

	public void addAssignment(String string) {
		InputStream inp;
		try {
			FileInputStream file = new FileInputStream(new File(location));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(3);
			Cell cell = null;

			// Update the value of cell
			Row row = sheet.getRow(0);
			row.createCell(this.getNumAssignments() + 1);
			cell = row.getCell(this.getNumAssignments() + 1);
			cell.setCellValue(string);

			file.close();

			FileOutputStream outFile = new FileOutputStream(new File(location));
			workbook.write(outFile);
			outFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades) {
		InputStream inp;
		try {

			Iterator<Map.Entry<Student, Integer>> entries = grades.entrySet()
					.iterator();
			while (entries.hasNext()) {
				Map.Entry<Student, Integer> entry = entries.next();

				FileInputStream file = new FileInputStream(new File(location));

				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheetAt(3);
				Cell cell = null;

				// Update the value of cell
				Row row = sheet.getRow(0);
				int location = 0;
				for (int i = 0; i < row.getLastCellNum(); i++) {
					if (row.getCell(i).getStringCellValue()
							.equals(assignmentName)) {
						location = i;
					}
				}

				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				int studentRow = 0;
				while (rowIterator.hasNext()) {

					Row row2 = rowIterator.next();
					if (row2.getCell(0).getStringCellValue()
							.equals(entry.getKey().getName())) {
						studentRow = row2.getRowNum();
						break;
					}

				}

				Row row3 = sheet.getRow(studentRow);

				row3.createCell(location);
				Cell cell3 = row3.getCell(location);
				cell3.setCellType(Cell.CELL_TYPE_STRING);
				cell3.setCellValue((int) Math.round(entry.getValue()));

				file.close();
				FileOutputStream outFile = new FileOutputStream(new File(
						this.location));
				workbook.write(outFile);
				outFile.close();
				// for (Student s : studentInfoDB) {
				// if (s.getName().equals(entry.getKey().getName())) {
				// ArrayList<String> assigns = s.getAssignments();
				// assigns.add(String.valueOf(entry.getValue()));
				// entry.getKey().setAssignments(assigns);
				// s.setAssignments(assigns);
				// }
				// }
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}

	public int getAverageAssignmentsGrade(Student student1) {

		ArrayList<String> assignments = student1.getAssignments();
		int sum = 0;
		ArrayList<String> contribs = new ArrayList<String>();

		InputStream inp;
		try {

			FileInputStream file = new FileInputStream(new File(location));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(3);
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getCell(0).getStringCellValue()
						.equals(student1.getName())) {
					Iterator<Cell> cellIterator = row.cellIterator();
					cellIterator.next();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						contribs.add(cell.getStringCellValue());
					}
				}
			}

		} catch (Exception e) {
			System.out.println("HM");
		}

		for (String assign : contribs) {
			sum = sum + (int) Math.round(Double.parseDouble(assign));
		}
		int avg = sum / contribs.size();

		return avg;
	}

	public int getAverageProjectsGrade(Student student) {

		double sum = 0;
		ArrayList<String> contribs = new ArrayList<String>();

		InputStream inp;
		try {

			FileInputStream file = new FileInputStream(new File(location));

			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(4);
			Iterator<Row> rowIterator = sheet.iterator();
			boolean found = false;
			while (rowIterator.hasNext() && !found) {
				Row row = rowIterator.next();
				if (row.getCell(0).getStringCellValue()
						.equals(student.getName())) {
					found = true;
					Iterator<Cell> cellIterator = row.cellIterator();
					cellIterator.next();
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						cell.setCellType(Cell.CELL_TYPE_STRING);
						if (!cell.getStringCellValue().equals(""))
							contribs.add(cell.getStringCellValue());
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		for (Student s : studentInfoDB) {
			if (s.getName().equals(student.getName())) {
				ArrayList<String> indivContribs = s.getContribs();

				ArrayList<String> teamGrades = s.getTeamGrades();

				for (int i = 0; i < teamGrades.size(); i++) {
					sum = sum
							+ (Integer.parseInt(teamGrades.get(i)) * (Double
									.parseDouble(contribs.get(i)) / 100));
				}

				double avg = sum / (teamGrades.size());

				return (int) Math.round(avg);

			}
		}
		return -1;
	}

	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		InputStream inp;
		try {

			Iterator<Map.Entry<Student, Integer>> entries = contributions1
					.entrySet().iterator();
			while (entries.hasNext()) {
				Map.Entry<Student, Integer> entry = entries.next();

				FileInputStream file = new FileInputStream(new File(location));

				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet sheet = workbook.getSheetAt(4);
				Cell cell = null;

				// Update the value of cell
				Row row = sheet.getRow(0);
				int location = 0;
				for (int i = 0; i < row.getLastCellNum(); i++) {
					if (row.getCell(i).getStringCellValue()
							.equals(projectName1)) {
						location = i;
					}
				}

				Iterator<Row> rowIterator = sheet.iterator();
				rowIterator.next();
				int studentRow = 0;
				while (rowIterator.hasNext()) {

					Row row2 = rowIterator.next();
					if (row2.getCell(0).getStringCellValue()
							.equals(entry.getKey().getName())) {
						studentRow = row2.getRowNum();
						break;
					}

				}

				Row row3 = sheet.getRow(studentRow);

				row3.createCell(location);
				Cell cell3 = row3.getCell(location);
				cell3.setCellType(Cell.CELL_TYPE_STRING);
				cell3.setCellValue((int) Math.round(entry.getValue()));

				file.close();
				FileOutputStream outFile = new FileOutputStream(new File(
						this.location));
				workbook.write(outFile);
				outFile.close();
				// for (Student s : studentInfoDB) {
				// if (s.getName().equals(entry.getKey().getName())) {
				// ArrayList<String> assigns = s.getContribs();
				// assigns.add(String.valueOf(entry.getValue()));
				// entry.getKey().setAssignments(assigns);
				// s.setContribs(assigns);
				// }
				// }
			}
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}

	public void addStudent(Student student) {
		// TODO Auto-generated method stub

	}

	public void addProject(String string) {
		// TODO Auto-generated method stub

	}

	public void addTeamGrades(String projectName1,
			HashMap<String, Integer> teamGrades) {
		// TODO Auto-generated method stub

	}

	public String getFormula() {
		// TODO Auto-generated method stub
		return this.formula;
	}

	public void setFormula(String text) {
		this.formula = text;

	}

	public int getOverallGrade(Student student) throws GradeFormulaException {
		// +,-,*,/
		// AT*.2+AS*.4+PR*.4
		// AT
		// AS
		// PR
		int Var1 = 0;
		int Var2 = 0;
		int Var3 = 0;

		if (formula.charAt(0) == 'A' || formula.charAt(0) == 'P') {

			return parseFormula(student);
		} else {
			throw new GradeFormulaException("Incorrect Formula Entered!");
		}

	}

	private int parseFormula(Student student) {
		try {
			double ret1 = -99;
			double ret2 = -99;
			double ret3 = -99;
			for (int i = 1; i < formula.length(); i++) {

				if (formula.charAt(i - 1) == 'A' && formula.charAt(i) == 'T') {
					ret1 = determineAttendence(student, ret1, i);
				}
				// AT * 0.2 + AS * 0.4 + PR * 0.4
				else if (formula.charAt(i - 1) == 'A'
						&& formula.charAt(i) == 'S') {
					ret2 = determineAssignments(student, ret2, i);

				}
				// AT * 0.2 + AS * 0.4 + PR * 0.4
				else if (formula.charAt(i - 1) == 'P'
						&& formula.charAt(i) == 'R') {
					ret3 = determineProjects(student, ret3, i);

				}
			}

			return calculateGrade(ret1, ret2, ret3);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GradeFormulaException("Incorrect Formula Entered!");
		}
	}

	private int calculateGrade(double ret1, double ret2, double ret3)
			throws Exception {
		if (ret1 != -99 && ret2 != -99 && ret3 != -99) {
			String a = Math.round(ret1 + ret2 + ret3) + "";
			return Integer.parseInt(a);
		} else if (ret1 == -99 && ret2 == -99 && ret3 != -99) {
			String a = Math.round(ret3) + "";
			return Integer.parseInt(a);
		} else {
			throw new Exception();
		}
	}

	private double determineProjects(Student student, double ret3, int i)
			throws Exception {
		String op1 = "";
		double num = -99;
		if (formula.charAt(i + 2) == '+' || formula.charAt(i + 2) == '-'
				|| formula.charAt(i + 2) == '*' || formula.charAt(i + 2) == '/') {

			op1 = formula.charAt(i + 2) + "";

			if (Character.isDigit(formula.charAt(i + 4))
					&& formula.charAt(i + 4) != '0') {

				num = Double.parseDouble(formula.substring(i + 3, i + 5));

			} else if (formula.charAt(i + 5) == '.') {

				num = Double.parseDouble(formula.substring(i + 3, i + 7));

			}

			else {
				throw new Exception();
			}

		} else {
			throw new Exception();
		}

		int pr = Integer.parseInt(student.getAverageProjectsGrade());
		switch (op1) {

		case "+":
			ret3 = pr + num;
			break;
		case "-":
			ret3 = pr - num;
			break;
		case "/":
			ret3 = pr / num;
			break;
		case "*":
			ret3 = pr * num;
			break;
		default:
			throw new Exception();

		}
		return ret3;
	}

	private double determineAssignments(Student student, double ret2, int i)
			throws Exception {
		String op1 = "";
		double num = -99;
		if (formula.charAt(i + 2) == '+' || formula.charAt(i + 2) == '-'
				|| formula.charAt(i + 2) == '*' || formula.charAt(i + 2) == '/') {

			op1 = formula.charAt(i + 2) + "";

			if (formula.charAt(i + 5) == '.') {

				num = Double.parseDouble(formula.substring(i + 3, i + 7));

			} else if (Character.isDigit(formula.charAt(i + 4))) {

				num = Double.parseDouble(formula.substring(i + 3, i + 5));

			} else {
				throw new Exception();
			}

		} else {
			throw new Exception();
		}

		int as = Integer.parseInt(student.getAverageAssignmentsGrade());
		switch (op1) {

		case "+":
			ret2 = as + num;
			break;
		case "-":
			ret2 = as - num;
			break;
		case "/":
			ret2 = as / num;
			break;
		case "*":
			ret2 = as * num;
			break;
		default:
			throw new Exception();

		}
		return ret2;
	}

	private double determineAttendence(Student student, double ret1, int i)
			throws Exception {
		String op1 = "";
		double num = -99;
		if (formula.charAt(i + 2) == '+' || formula.charAt(i + 2) == '-'
				|| formula.charAt(i + 2) == '*' || formula.charAt(i + 2) == '/') {

			op1 = formula.charAt(i + 2) + "";

			if (formula.charAt(i + 5) == '.') {

				num = Double.parseDouble(formula.substring(i + 3, i + 7));

			} else if (Character.isDigit(formula.charAt(i + 4))) {

				num = Double.parseDouble(formula.substring(i + 3, i + 5));

			} else {
				throw new Exception();
			}

			int att = student.getAttendance();
			switch (op1) {

			case "+":
				ret1 = att + num;
				break;
			case "-":
				ret1 = att - num;
				break;
			case "/":
				ret1 = att / num;
				break;
			case "*":
				ret1 = att * num;
				break;
			default:
				throw new Exception();

			}

		} else {
			throw new Exception();
		}
		return ret1;
	}

}
