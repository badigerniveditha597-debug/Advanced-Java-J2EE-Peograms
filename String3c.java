/*3c. String Exercise progams
	Q6. Write a Java Program for Capitalizing the first letter of each word. using user defined function
	capitalizeWords()
	Q7. Write a Java Program for Shortening a string to a specified length and adds an ellipsis using user
	defined function truncate()
	Q8. Write a Java Program for Verifying if a string contains only numeric characters using user defined
	function isNumeric()

	Q9. Write a Java Program for Creating a random string of a specified length using user defined
	function generateRandomString()
	Q10. Write a Java Program for Counting the number of words in a string using user defined function
	countWords()
	*/
package lab3;

public class String3c {
		//6. Function to capitalize the first letter of each word
		 public static String capitalizeWords(String str) {
		 if (str == null || str.isEmpty()) {
		 return str;
		 }
		 StringBuilder result = new StringBuilder();
		 String[] words = str.split("\\s+");
		 for (String word : words) {
		 if (!word.isEmpty()) {
		 result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase()).append(" ");
		 }
		 }
		 return result.toString().trim();
		 }
		 // 7. Function to truncate a string to a specified length
		 public static String truncate(String str, int maxLength) {
		 if (str == null) {
		 return null;
		 }
		 return str.length() > maxLength ? str.substring(0, maxLength) + "..." : str;
		 }
		 // 8. Function to check if a string contains only digits
		 public static boolean isNumeric(String str) {
		 if (str == null || str.isEmpty()) {
		 return false;
		 }
		 return str.matches("\\d+");
		 }
		 // 9. Function to generate a random string of specified length
		 public static String generateRandomString(int length) {
		 String characters ="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		 StringBuilder random = new StringBuilder();
		 for (int i = 0; i < length; i++) {
		 int index = (int) (Math.random() * characters.length());
		 random.append(characters.charAt(index));
		 }
		 return random.toString();
		 }
		 // 10. Function to count words in a string
		 public static int countWords(String str) {
		 if (str == null || str.trim().isEmpty()) {
		 return 0;
		 }
		 return str.trim().split("\\s+").length;
		 }
		 public static void main(String[] args) {
			 // Example usage of each function
			 System.out.println("6. Capitalize Words: " + capitalizeWords("hello world")); // HelloWorld
			 System.out.println("7. Truncate: " + truncate("Hello World", 5)); // Hello...
			 System.out.println("8. Is Numeric: " + isNumeric("12345")); // true
			 System.out.println("9. Random String: " + generateRandomString(10)); // Random 10-char string
			 System.out.println("10. Word Count: " + countWords("Hello world java")); // 3 }
			}
	}


