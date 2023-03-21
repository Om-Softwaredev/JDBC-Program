package in.ineuron.controller;

import java.util.Scanner;

import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

//Controller logic
public class TestApp {

	public static void main(String[] args) {
		
		//Connection connection = DriverManager.getConnection(url.user,password);
		IStudentService studentService = StudentServiceFactory.getStudentService();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter name of the student : ");
		String sname= scanner.next();
		
		System.out.print("Enter age of the student : ");
		int sage=scanner.nextInt();
		
		System.out.print("Enter address of the student : ");
		String saddr= scanner.next();
		
		String msg=studentService.addStudent(sname, sage, saddr);
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record inserted succesfully....");
		} else {
			System.out.println("record insertion failed....");
		}
	}

}
