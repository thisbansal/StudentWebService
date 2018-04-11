package au.edu.swin.waa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class SupportingClass {
	
	public static void makeDataPersistent(Map<Integer, String[]> studentDetailsObject){
		Gson objGson = new Gson();
		String jsonString = objGson.toJson(studentDetailsObject);
		writeToFile(jsonString);
	}
	
	private static void writeToFile(String jsonString) {
		try {
			FileWriter writer = new FileWriter("studentoutput.json");
			writer.write(jsonString);
			writer.close();
		}
		catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	static public Map<Integer, String[]> getDataFromFile() {
  		File newFile = new File("studentoutput.json");
		StringBuilder contentBuilder = new StringBuilder();
		String resultString = "";
		Map<Integer, String[]> studentDetailsObject = new HashMap<Integer, String[]>();
		if (newFile.exists()){
			try (Stream<String> stream = Files.lines(Paths.get(newFile.getName()),
					StandardCharsets.UTF_8)) {
				stream.forEach(s -> contentBuilder.append(s).append("\n"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			resultString =  contentBuilder.toString();
		}
		if (!resultString.isEmpty()){
			Type type = new TypeToken<Map<Integer, String[]>>(){}.getType();
			studentDetailsObject = new Gson().fromJson(resultString, type);
			
		}
		return studentDetailsObject;
	}

}
