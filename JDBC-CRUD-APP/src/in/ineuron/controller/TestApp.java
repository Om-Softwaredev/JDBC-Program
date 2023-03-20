package in.ineuron.controller;

import in.ineuron.service.IStudentService;
import in.ineuron.servicefactory.StudentServiceFactory;

//Controller logic
public class TestApp {

	public static void main(String[] args) {
		
		//Connection connection = DriverManager.getConnection(url.user,password);
		IStudentService studentService = StudentServiceFactory.getStudentService();
		String msg=studentService.addStudent("sachin", 49, "MI");
		if (msg.equalsIgnoreCase("success")) {
			System.out.println("record inserted succesfully....");
		} else {
			System.out.println("record insertion failed....");
		}
	}

}
