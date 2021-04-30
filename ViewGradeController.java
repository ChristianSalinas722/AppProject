package sample;


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
    private TextField classField;

    @FXML
    void addGrade(ActionEvent event) throws IOException {		//function to add grades to selected class
        String ass = assignment.getText().toString();			//Initializing strings
        String gra = grade.getText().toString();
        String clas = classField.getText().toString();
        
        clas = clas + ".txt";						//picking class file to read from

        grade.clear();							//When grade is submitted, clear the fields	
        assignment.clear();

        updateGradeList(ass, gra, clas);				//Call updateGradesList

        double result = calculateAverage(clas);				//sends the file to calculate average
        average.setText(Double.toString(result));			//store result from calcAvg to avg text field
    }

    //function to calculate average, was thinking we can just pass in path to text file, load text file in function, parse grades and calculate average, then return average

    public double calculateAverage(String clas) throws NumberFormatException, IOException {		//Calculate the average
        double average = 0;								//initialize variables
        double total = 0;

        File fileToBeModified = new File(clas);						//create new file to ope (passed from addGrade)
        BufferedReader br = null;							//create new buffered reader and filereader to read through file
        br = new BufferedReader(new FileReader(fileToBeModified));			//Check if file is empty
        if(fileToBeModified.length() <= 1)
        {
            System.out.println("File is empty");
            return 0;
        }
        FileWriter fw = null;								//Create a new filewriter
        String line = "";								//Initialize strings to be used to read
        String currentLine = "";
        String assign = "";
        String grad = "";
        int lineNum = -2;
        int result = 0;

        String patern = "(.*)([1-9]{2}$)";						//regex Pattern to identify grades entered
        Pattern r = Pattern.compile(patern);
        Matcher m = r.matcher(currentLine);

        if(m.find()){									//pattern match was found, store in a variable
            grad = m.group(2);
        }

        while (( line = br.readLine()) != null) {					//read to end of file

            currentLine = line + "\n";							//store the first line in currentLine
            patern = "(.*)([1-9]{2}$)";							//regex pattern
            r = Pattern.compile(patern);
            m = r.matcher(currentLine);

            if(m.find()){								//if pattern was found in the line being read then assign them to variables
                assign = m.group(1);
                grad = m.group(2);
            
            total += Double.parseDouble(grad);						//add the grade variable to a total value
            //System.out.println(assign);
           // System.out.println(grad);
            }

            lineNum++;									//keep track of line num to divie total by
            if(total != 0) {
                result = 1;								//if a grade is present, give result a successful value
            }
        }
        

        //System.out.println("Line Number total: " + lineNum);				//Print out lines read
        if(result == 1) {								//if a value was found, average it 
            average = total / lineNum;



            return average;								//return
        }
        return 0;									//if no values are found return 0. thats the average i guess
    }

    //We will need to make this method be called when this FXML loads and then call it again every time addGrade button is pressed. Possible parameter is a text file and then it parses/loops
    //through the txt file and then outputs them in particular format to gradesList
    public void updateGradeList(String assignment, String grade, String clas) throws IOException {


        File f = new File(clas);												//open file
        PrintWriter pw = new PrintWriter(new FileWriter(f,true)); 					//Create PrintWriter to write to file with filewriter attached to users.txt
        FileReader fr = new FileReader(f);								//create filereader and buffered reader to parse file
        BufferedReader br = new BufferedReader(new FileReader(f));
        String formatStr = "%-20s %-12s";								//Create format to print out to, so everything looks neat

        //if(assignment.length() < 5) {

        pw.write(assignment + " " + grade + "\n");							//write assignment and grade to file then flush
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
        gradeList.getChildren().clear();								//wrap everything up
        assignList.getChildren().clear();
        pw.close();
        initGradeList(clas);



    }


    //read through the file and put all current grades to gradesList with format "assignment \t grade\n"
    public void initGradeList(String clas) throws IOException {
        File fileToBeModified = new File(clas);						//Open new file
        BufferedReader br = null;							//create readers
        br = new BufferedReader(new FileReader(fileToBeModified));
        FileWriter fw = null;								//create writer
        String line = "";								//initialize strings to be used
        String currentLine = "";
        String assign = "";
        String grad = "";


        String patern = "(.*)([1-9]{2}$)";						//Create patterns to be used.
        Pattern r = Pattern.compile(patern);						//Reads through the exact same way as calculateAverage() but just does something different in while loop
        Matcher m = r.matcher(currentLine);						//explained at next comment

        if(m.find()){									
            assign = m.group(1);
            grad = m.group(2);
        }

        String formatStr = "%-20s %-15s";						
        while (( line = br.readLine()) != null) {					

            currentLine = line + "\n";
            patern = "(.*)([1-9]{2}$)";
            r = Pattern.compile(patern);
            m = r.matcher(currentLine);

            if(m.find()){
                assign = m.group(1);
                grad = m.group(2);
            }

            //System.out.println(assign);
            //System.out.println(grad);

            //	System.out.println(currentLine);
            Text text_1 = new Text(assign + "\n");					//stores assignment & grade found reading through the file and stored in variables from pattern matching
            assignList.getChildren().add(text_1);					//into the respective TextFlow Boxes. 
            Text text_2 = new Text(grad + "\n");
            gradeList.getChildren().add(text_2);

        }
    }


}
