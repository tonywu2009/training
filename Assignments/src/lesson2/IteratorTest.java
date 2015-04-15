package lesson2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class IteratorTest {

	public static void main(String[] args) {
		List myList = new ArrayList();
		String fruit1 = "pear", fruit2 = "banana", fruit3 = "tangerine", fruit4 = "strawberry", fruit5 = "blackberry";
		myList.add(fruit1);
		myList.add(fruit2);
		myList.add(fruit3);
		myList.add(fruit4);
		myList.add(fruit5);
		Iterator i = myList.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		
		ListIterator i2 = myList.listIterator();
		while(i2.hasNext()) {
			i2.next();
		}
		while(i2.hasPrevious()) {
			System.out.println(i2.previous());
		}
		while(i2.hasNext()) {
			String temp = i2.next().toString();
			if(temp.equals("tangerine")) {
				i2.add("banana");
			}
		}
		while(i2.hasPrevious()) {
			System.out.println(i2.previous());
		}

	}

}
