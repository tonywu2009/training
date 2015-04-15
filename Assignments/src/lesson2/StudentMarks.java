/** 
 *  StudentMarks.java
 *  @author Tony Wu - tonywu2009@gmail.com
 *  Created on Apr 14, 2015
 */
package lesson2;

import java.util.Scanner;

/**
 * @author Tony
 * The objet of this program is to write a program to process a set of student
 * marks by reading them from an input file. The program should write its output
 * to the standard output. Each line of the input file follows this format, a name
 * followed by a mark.
 */
public class StudentMarks {
	public static void main(String[] args) {
		//TODO read names and marks from an input file
		String fileName = "testFile.txt";
		File file = new File(fileName);
		
		//TODO write the output to the standard output.
		try {
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNextLine()) {
				System.out.println(inputStream.nextLine());
		}
		//TODO standard system out console. List in alphabetic order
		//TODO output to merit order (also print the ranking)
		//For each student, in each list, print out the number of marks and the average
		//Each list is preceded by a title
		
		//TODO print the number of students, the average of the student averages
		// and the standard deviation of the student averages
		//TODO floats must be printed to 1 decimal place.
	}
}
