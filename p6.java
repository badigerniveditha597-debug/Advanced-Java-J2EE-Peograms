/*
Write a Java program to iterate through all elements in a linked list
 starting at the specified position ( 2nd) using iterator
( hint : Iterator iteratorobj = listobj.listIterator(1))
*/
package lab1;
	import java.util.*;
	public class p6 {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			List<String>	ls = new LinkedList<>();
			ls.add("Orange");
			ls.add("geen");
			ls.add("pink");
			ls.add("red");
			ls.add("Blue");
			Iterator iterator = ls.listIterator(2);
			while (iterator.hasNext()) {
			System.out.println(iterator.next());
			}
		}

	}



