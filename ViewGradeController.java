package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ViewGradesController {

    @FXML
    private Text average;

    @FXML
    private Button addGrade;

    @FXML
    private TextField assignment;

    @FXML
    private TextField grade;
    
    @FXML
    private TextFlow gradeList;
    
    @FXML
    private TextFlow assignList;

    @FXML
    void addGrade(ActionEvent event) throws IOException {
    	String ass = assignment.getText().toString();
    	String gra = grade.getText().toString();
    	
    	grade.clear();
    	assignment.clear();
    	
    	updateGradeList(ass, gra);
    	
    	double result = calculateAverage();
    	average.setText(Double.toString(result));
    }
    
    //function to calculate average, was thinking we can just pass in path to text file, load text file in function, parse grades and calculate average, then return average
    
    public double calculateAverage() throws NumberFormatException, IOException {
    	double average = 0;
    	double total = 0;
    	
    	File fileToBeModified = new File("Physics.txt");
    	BufferedReader br = null;
    	br = new BufferedReader(new FileReader(fileToBeModified));
    	if(fileToBeModified.length() <= 1)
    	{
    		System.out.println("File is empty");
    		return 0;
    	}
    	FileWriter fw = null;
    	String line = "";
    	String currentLine = "";
    	String assign = "";
    	String grad = "";
    	int lineNum = 0;
    	int result = 0;
    	
    	String patern = "(.*)([1-9]{2}$)";
    	Pattern r = Pattern.compile(patern);
    	Matcher m = r.matcher(currentLine);

    	if(m.find()){
    	grad = m.group(2);
    	} 
    	
            while (( line = br.readLine()) != null) {											//read to end of file
        
            	currentLine = line + "\n";
            	patern = "(.*)([1-9]{2}$)";
            	r = Pattern.compile(patern);
            	m = r.matcher(currentLine);

            	if(m.find()){
            	assign = m.group(1);
            	grad = m.group(2);
            	} 
            	total += Double.parseDouble(grad);
            	System.out.println(assign);
            	System.out.println(grad);
            	
            	
            	lineNum++;
            	if(total != 0) {
            		result = 1;
            	}
            }
            
            System.out.println("Line Number total: " + lineNum);
            if(result == 1) {
            average = total / lineNum;
            
            
    	
    	return average;
            }
            return 0;
    }
    
    //We will need to make this method be called when this FXML loads and then call it again every time addGrade button is pressed. Possible parameter is a text file and then it parses/loops 
    //through the txt file and then outputs them in particular format to gradesList
    public void updateGradeList(String assignment, String grade /*, file argument or something */) throws IOException {
    	
    	
		File f = new File("Physics.txt");												//open file
		PrintWriter pw = new PrintWriter(new FileWriter(f,true)); 					//Create PrintWriter to write to file with filewriter attached to users.txt
		FileReader fr = new FileReader(f);											//create filereader and buffered reader to parse file
		BufferedReader br = new BufferedReader(new FileReader(f));
		String formatStr = "%-20s %-12s";
	
		//if(assignment.length() < 5) {
	
			pw.write(assignment + " " + grade + "\n");
			pw.flush();
		/*}
		else if(assignment.length() >= 5 && assignment.length() < 12) {
			pw.write("\n");
			pw.write(String.format(formatStr, assignment, grade));
			pw.flush();
		
		}
		else if(assignment.length() >= 12 && assignment.length() < 20) {
			pw.write("\n");
			pw.write(String.format(formatStr, assignment, grade));
			pw.flush();
		
		}
		else if(assignment.length() >= 20 && assignment.length() < 28) {
			pw.append("\n" + assignment + "\t" + grade);
			pw.flush();
		
		}*/
    	gradeList.getChildren().clear();
    	assignList.getChildren().clear();
    	pw.close();
    	initGradeList();
    	
    	
 
    }
    
    
    //read through the file and put all current grades to gradesList with format "assignment \t grade\n" 
    public void initGradeList(/*, file argument or something */) throws IOException {
    	File fileToBeModified = new File("Physics.txt");
    	BufferedReader br = null;
    	br = new BufferedReader(new FileReader(fileToBeModified));
    	FileWriter fw = null;
    	String line = "";
    	String currentLine = "";
    	String assign = "";
    	String grad = "";
    	
    	
    	String patern = "(.*)([1-9]{2}$)";
    	Pattern r = Pattern.compile(patern);
    	Matcher m = r.matcher(currentLine);

    	if(m.find()){
    	assign = m.group(1);
    	grad = m.group(2);
    	} 
    	
    	String formatStr = "%-20s %-15s";
            while (( line = br.readLine()) != null) {											//read to end of file

            	currentLine = line + "\n";
            	patern = "(.*)([1-9]{2}$)";
            	r = Pattern.compile(patern);
            	m = r.matcher(currentLine);

            	if(m.find()){
            	assign = m.group(1);
            	grad = m.group(2);
            	} 
            	
            	System.out.println(assign);
            	System.out.println(grad);
            	
            //	System.out.println(currentLine);
            	Text text_1 = new Text(assign + "\n");
            	assignList.getChildren().add(text_1);
            	Text text_2 = new Text(grad + "\n");
            	gradeList.getChildren().add(text_2);
            	
            }
            }


}
