package lesson2;

import java.util.*; 

public class SetDemo {     
	public static void main( String [] args ) {         // Create a set called mySet
		Set mySet = new HashSet();         // Ensure that this set contains an interesting selection of fruit           
		String fruit1 = "pear", fruit2 = "banana", fruit3 = "tangerine",                                                         
				fruit4 = "strawberry", fruit5 = "blackberry";         
		mySet.add( fruit1 );         
		mySet.add( fruit2 );         
		mySet.add( fruit3 );         
		mySet.add( fruit2 );         
		mySet.add( fruit4 );         
		mySet.add( fruit5 );         
		// Display contents of mySet         
		System.out.println( "mySet now contains:" );         
		System.out.println( mySet );
		System.out.println("There are " + mySet.size() + " number of elements");
		mySet.remove("blackberry");
		mySet.remove("Strawberry");
		System.out.println(mySet);
		mySet.clear();
		System.out.println(mySet.isEmpty());
	} 
}

