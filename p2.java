/*Write a java program for getting different colors
 *  through ArrayList interface and remove the 2nd element and color "Blue" from the ArrayList (using remove by index and remove by object)
 */


package lab1;

	import java.util.*;
	public class p2 {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			List<String> ls = new ArrayList<>();
			ls.add("Orange");
			ls.add("geen");
			ls.add("pink");
			ls.add("red");
			ls.add("Blue");
			System.out.println(ls);
			ls.remove(2);
			System.out.println(ls);
			ls.remove("Blue");
			System.out.println(ls);
		}

	}



