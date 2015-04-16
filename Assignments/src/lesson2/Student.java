package lesson2;

import java.util.Comparator;

public class Student implements Comparable<Student> {
	private String name;
	private int num;
	private double merit;
	
	public Student (String name, int num, double merit) {
		this.name= name;
		this.num = num;
		this.merit = merit;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public double getMerit() {
		return merit;
	}

	public void setMerit(double merit) {
		this.merit = merit;
	}
	
	public double getAverageMerit() {
		return this.merit/this.num;
	}

	@Override
	public int compareTo(Student s) {
		if(getAverageMerit() > s.getAverageMerit()) {
			return -1;
		} else if (getAverageMerit() == s.getAverageMerit()) {
			return 0;
		} else {
			return 1;
		}
	}
	
	public static Comparator<Student> StudentNameComparator = new Comparator<Student>() {
		public int compare(Student a, Student b) {
			String student1 = a.getName().toUpperCase();
			String student2 = b.getName().toUpperCase();

			return student1.compareTo(student2);
		}
	};
	
	public String toString() {
		return this.name;
	}
	
}
