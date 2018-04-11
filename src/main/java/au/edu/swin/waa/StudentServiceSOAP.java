package au.edu.swin.waa;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;

public class StudentServiceSOAP{

	static Map<Integer, String[]> studentDetailsObject = new HashMap<Integer, String[]>();
	
	public String addNewStudent(String studentName, Integer studentID, Integer secretPin) {
		String returnStatement = "You are already registered as a student.";
		studentDetailsObject = SupportingClass.getDataFromFile();
		//all code to add a new student
		if (!studentDetailsObject.keySet().contains(studentID)){
			String[] studentInfoStrings = {studentName, studentID.toString(), secretPin.toString()};
			studentDetailsObject.put(studentID, studentInfoStrings);
			returnStatement = "Student "+studentInfoStrings[0]+" with student id "+studentInfoStrings[1]+" has been successfully added.";
			System.out.println("added succesfully.");
			SupportingClass.makeDataPersistent(studentDetailsObject);
		}
		else {
			System.out.println("is aleady present");
		}
		return returnStatement;
	}
	
	public String deleteAStudent(Integer studentID){
		String resultString ="No student is found with given detail.";
		studentDetailsObject = SupportingClass.getDataFromFile();
		if (studentDetailsObject.containsKey(studentID)){
			studentDetailsObject.remove(studentID);
			SupportingClass.makeDataPersistent(studentDetailsObject);
			Gson objGson = new Gson();
			resultString= objGson.toJson(studentDetailsObject); 
		}
		return resultString;
	}
	
	public String viewAllStudents(){
		studentDetailsObject = SupportingClass.getDataFromFile();
		Gson objGson = new Gson();
		String jsonString = objGson.toJson(studentDetailsObject);
		return jsonString;
	}
}