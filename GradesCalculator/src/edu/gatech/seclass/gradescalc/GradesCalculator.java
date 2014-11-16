package edu.gatech.seclass.gradescalc;

import java.util.HashSet;
import java.util.Scanner;

public class GradesCalculator {

	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		String in = "Y";
		boolean exit = false;

		do {
			if (in.equalsIgnoreCase("Y")) {
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

				System.out.println();
				System.out.println();
				System.out.println("Would you like to continue? [Y]es or [N]o");
				in = scanner.next();
			} else if (in.equalsIgnoreCase("N")) {
				exit = true;
			} else {
				System.out.println("Would you like to continue? [Y]es or [N]o");
				in = scanner.next();
			}
		} while (!exit);
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
			for (int i = 0; i < maxStudentName - s.getName().length(); i++) {
				System.out.print(" ");
			}
			System.out.print("*  ");

			System.out.print(s.getGtid());
			for (int i = 0; i < maxGTID - s.getGtid().length(); i++) {
				System.out.print(" ");
			}
			System.out.print("*   ");

			System.out.print(s.getEmail());
			for (int i = 0; i < maxEmail - s.getEmail().length(); i++) {
				System.out.print(" ");
			}
			System.out.print("*               ");

			System.out.print(s.getAttendance());
			for (int i = 0; i < bar.length() - maxAtt - maxEmail - maxGTID
					- maxStudentName
					- String.valueOf(s.getAttendance()).length() - 24; i++) {
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
		String bar = "";
		for (int i = 0; i < a + b + c + d + 40; i++) {
			bar = bar + "*";
		}
		return bar;
	}

	private static int findMaxAttendance(HashSet<Student> students) {
		String attendance = "";
		for (Student s : students) {
			if (String.valueOf(s.getAttendance()).length() > attendance
					.length()) {
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

	private static void printStudentHeaderToScreen(String bar,
			HashSet<Student> students) {
		int maxStudentName = findMaxStudentName(students);
		int maxGTID = findMAXGTID(students);
		int maxEmail = findMaxEmail(students);
		int maxAtt = findMaxAttendance(students);
		for (int i = 0; i <= bar.length(); i++) {
			if (i == 0 || i == bar.length() - 20 || i == maxStudentName
					|| i == maxStudentName + maxGTID
					|| i == maxStudentName + maxGTID + maxEmail) {
				System.out.print("*");
			} else if (i == maxStudentName / 2) {
				System.out.print("Name");
			} else if (i == (maxGTID / 2) + maxStudentName) {
				System.out.print("GTID");
			} else if (i == (maxEmail / 2) + maxStudentName + maxGTID) {
				System.out.print("Email");
			} else if (i == (maxAtt / 2) + maxEmail + maxGTID + maxStudentName
					+ 10) {
				System.out.print("Attendance");
			} else {
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
