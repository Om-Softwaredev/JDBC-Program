package in.ineuron.servicefactory;

import in.ineuron.service.IStudentService;
import in.ineuron.service.StudentServiceImpl;

//Abstraction logic for implementation
public class StudentServiceFactory {
	
	// make constructor private to avoid object creation
	private StudentServiceFactory() {
		
	}
	private static IStudentService studentService=null;
	public static IStudentService getStudentService() {
		
		//Singleton pattern
		if (studentService == null) {
			studentService = new StudentServiceImpl();			
		}
		return studentService;
		
		
	}
}
