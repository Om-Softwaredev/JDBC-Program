package in.ineuron.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import in.ineuron.dto.Student;
import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

//Controller logic
public class TestApp {

	public static void main(String[] args) throws Exception {

//		insertOperation();	
//		selectOperation();
//		deleteRecord();
//		updateRecord();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("1. CREATE");
			System.out.println("2. READ");
			System.out.println("3. UPDATE");
			System.out.println("4. DELETE");
			System.out.println("5. EXIT");
			System.out.print("Enter yout choice: PRESS[1/2/3/4/5] : ");
			String choice = br.readLine();

			switch (choice) {
			case "1":
				insertOperation();
				break;

			case "2":
				selectOperation();
				break;

			case "3":
				updateRecord();
				break;

			case "4":
				deleteRecord();
				break;

			case "5":
				System.out.println("*****Thanks for using the application*****");
				System.exit(0);

			default:
				System.out.println("Invalid operation please try with valid options...");
				break;
			}

		}
	}

	private static void updateRecord() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Enter id of the student to be updated : ");
		String sid = br.readLine();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student student = studentService.searchStudent(Integer.parseInt(sid));

		if (student != null) {

			Student newStudent = new Student();

			System.out.println("Student id is :: " + student.getSid());
			newStudent.setSid(student.getSid());

			System.out.print("Student oldName is :: " + student.getSname() + " Enter newName :: ");
			String newName = br.readLine();
			if (newName.equals("") || newName == "") {
				newStudent.setSname(student.getSname());
			} else {
				newStudent.setSname(newName);
			}

			System.out.print("Student oldAge is :: " + student.getSage() + " Enter newAge :: ");
			String newAge = br.readLine();
			if (newAge.equals("") || newAge == "") {
				newStudent.setSage(student.getSage());
			} else {
				newStudent.setSage(Integer.parseInt(newAge));
			}

			System.out.print("Student oldAddress is :: " + student.getSaddress() + " Enter newAddress :: ");
			String newAddress = br.readLine();
			if (newAddress.equals("") || newAddress == "") {
				newStudent.setSaddress(student.getSaddress());
			} else {
				newStudent.setSaddress(newAddress);
			}

			System.out.println("new Object data is :: " + newStudent);
			System.out.println();

			String status = studentService.updateStudent(newStudent);
			if (status.equalsIgnoreCase("success")) {
				System.out.println("record updated succesfully....");
			} else {
				System.out.println("record updation failed....");
			}

		} else {
			System.out.println("Student Record not available for the given id :: " + sid + " for updation");
		}
	}

	private static void deleteRecord() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter id of the student to be deleted : ");
		int sid = scanner.nextInt();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		String msg = studentService.deleteStudent(sid);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record Deleted succesfully....");
		} else if (msg.equalsIgnoreCase("not found")) {
			System.out.println("record not available to delete for given Id:: " + sid);
		} else {
			System.out.println("record deletion failed....");
		}
	}

	private static void selectOperation() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter id of the student : ");
		int sid = scanner.nextInt();

		IStudentService studentService = StudentServiceFactory.getStudentService();
		Student std = studentService.searchStudent(sid);
		if (std != null) {
			System.out.println(std);
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			System.out.println(std.getSid() + "\t" + std.getSname() + "\t" + std.getSage() + "\t" + std.getSaddress());
		} else {
			System.out.println("Record not available for given student id : " + sid);
		}
	}

	private static void insertOperation() {
		// Connection connection = DriverManager.getConnection(url.user,password);
		IStudentService studentService = StudentServiceFactory.getStudentService();

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter name of the student : ");
		String sname = scanner.next();

		System.out.print("Enter age of the student : ");
		int sage = scanner.nextInt();

		System.out.print("Enter address of the student : ");
		String saddr = scanner.next();

		String msg = studentService.addStudent(sname, sage, saddr);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record inserted succesfully....");
		} else {
			System.out.println("record insertion failed....");
		}
	}
}