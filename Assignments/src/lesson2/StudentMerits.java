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
 * The object of this program is to write a program to process a set of student
 * marks by reading them from an input file. The program should write its output
 * to the standard output. Each line of the input file follows this format, a name
 * followed by a mark.
 */
public class StudentMerits {
	public static void main(String[] args) {
		//TODO read names and marks from an input file
		String fileName = "text.txt";
		File file = new File(fileName);
		
		//TODO TEST write the output to the standard output.
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
		//TODO Output in alphabetic order
		System.out.println();
		System.out.println("Alpha order");
		
		//create a new hashmap with names mapped to student objects.
		HashMap<String, Student> group = new HashMap<String, Student>();
		try {
			Scanner inputStream = new Scanner(file);
			while (inputStream.hasNextLine()) { //while the stream has next line
				String name = inputStream.next();         // grab name
				double merit = inputStream.nextDouble();  //grab merit
				if (!group.keySet().contains(name)) {        //if not in group 
					Student s = new Student(name, 1, merit); // add new student
					group.put(name, s);
				} else {   //already in the group
					Student s = group.get(name);
					s.setNum(s.getNum()+1);   //increment number of merits
					s.setMerit(s.getMerit()+merit);  //add merits
				}
			}
			inputStream.close();
		} catch (Exception e) {
			System.out.println("error");
		}
		
		// Creats arraylist of students (to be sorted)
		ArrayList<Student> sortedGroup = new ArrayList<Student>();
		// builds the list
		for(String s : group.keySet()) {
			sortedGroup.add(group.get(s));
		}
		
		//Sorts by comparator (student name - alphabetically)
		Collections.sort(sortedGroup, Student.StudentNameComparator);
		for(Student s: sortedGroup) {
			System.out.println(s.getName() + " " + s.getNum() + " " + s.getAverageMerit());
		}
		System.out.println();
		
		//Merit order - rank using collections.sort - CompareTo()
		System.out.println("Merit order");
		if (sortedGroup.size() > 0) {
			Collections.sort(sortedGroup);  //sort 
			int rank = 1;           //initial rank
			Student first = sortedGroup.get(0);  //uses first student to compare
			for (int i = 0; i < sortedGroup.size();i++) {
				Student s = sortedGroup.get(i);
				if (s.compareTo(first) == 0) {
					first = s;
				} else {
					rank++;    //increment the rank only if they are not equal
				}
				System.out.println(rank + " " + s.getName() + " " + s.getNum() + " " + s.getAverageMerit());
			}
		}

		System.out.println();
		int numStudents = group.size();  //number of students
		System.out.println("Number of students: " + numStudents);
		double totalMerit = 0;
		for(Student s : sortedGroup) {
			totalMerit += s.getAverageMerit();
		}
		double totalAverageMerit = totalMerit/numStudents;  //average merit
		//rounded average merit
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
