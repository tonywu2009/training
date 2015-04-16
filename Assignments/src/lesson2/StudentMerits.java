/** 
 *  StudentMerits.java
 *  @author Tony Wu - tonywu2009@gmail.com
 *  Created on Apr 14, 2015
 */
package lesson2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author Tony
 * The objet of this program is to write a program to process a set of student
 * marks by reading them from an input file. The program should write its output
 * to the standard output. Each line of the input file follows this format, a name
 * followed by a mark.
 */
public class StudentMerits {
	public static void main(String[] args) {
		//TODO read names and marks from an input file
		String fileName = "text.txt";
		File file = new File(fileName);
		
		//TODO write the output to the standard output.

		System.out.println("File output test");
		try {
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNextLine()) {
				System.out.println(inputStream.nextLine());
			}
			inputStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("File is not found");
		}
		//TODO standard system out console. List in alphabetic order
		//This maps the name as a key to the marks.
		System.out.println();
		System.out.println("Alpha order");
/*		HashMap<String, Double> m = new HashMap<String, Double>();
		HashMap<String, Integer> count = new HashMap<String, Integer>();*/
		
		HashMap<String, Student> group = new HashMap<String, Student>();
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNextLine()) { //while the stream has next line
				String name = inputStream.next();   // name is next token
				double merit = inputStream.nextDouble();  //merit is next token
				if (!group.keySet().contains(name)) {   //if not in the group 
					Student s = new Student(name, 1, merit); // add new student
					group.put(name, s);
				} else {   //already in the group, just increment merit count
					Student s = group.get(name);
					s.setNum(s.getNum()+1);  //increment number of merits
					s.setMerit(s.getMerit()+merit);
				}
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println("error");
		}
		

		ArrayList<Student> sortedGroup = new ArrayList<Student>();
		
		for(String s : group.keySet()) {
			sortedGroup.add(group.get(s));
		}
		
		//Sorts by comparator
		Collections.sort(sortedGroup, Student.StudentNameComparator);
		for(Student s: sortedGroup) {
			System.out.println(s.getName() + " " + s.getNum() + " " + s.getAverageMerit());
		}
		
		System.out.println();
		
		//Merit order sorts by merit using CompareTo()
		System.out.println("Merit order");
		Collections.sort(sortedGroup);
		int rank = 1;
		Student first = sortedGroup.get(0);
		for (int i = 0; i < sortedGroup.size();i++) {
			Student s = sortedGroup.get(i);
			if (s.compareTo(first) == 0) {
				first = s;
			} else {
				rank++;
			}
			System.out.println(rank + " " + s.getName() + " " + s.getNum() + " " + s.getAverageMerit());
		}
		

		
		System.out.println();
		int numStudents = group.size();
		System.out.println("Number of students: " + numStudents);
		double totalMerit = 0;
		for(Student s : sortedGroup) {
			totalMerit += s.getAverageMerit();
		}
		double totalAverageMerit = totalMerit/numStudents;
		
		System.out.println("average student mark: " + (double)Math.round(totalAverageMerit * 10) / 10); 
		
		
		//TODO Calculate the standard deviation
		//     calculate the deviance (sum of each AverageMerit - Median)
		//     add the squares of the deviances together
		//     divide by n-1
		//     square root and round the total to 1 decimal place
		ArrayList<Double> deviance = new ArrayList<Double>();
		for (Student s : sortedGroup) {
			double dev = s.getAverageMerit() - totalAverageMerit;
			deviance.add(dev);
		}
		double total = 0.0;
		for(double d : deviance) {
			total += Math.pow(d, 2);
		}
		total = total/(sortedGroup.size()-1);
		double standard = Math.sqrt(total);
		double roundedStandard = Math.ceil(standard * 10) / 10;
		System.out.println("Standard deviation: " + roundedStandard);
	}

}
