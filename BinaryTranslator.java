import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * User inputs a postive number and states
 * whether they want to convert it from
 * binary to decimal, or from decimal to binary.
 * Inputs come from a file or the console.
 * Author: Andy Fleischer
 * Date: 9-10-2019
 */
public class BinaryTranslator {
	
	// Constructor for Binary Translator
	public BinaryTranslator() {
		
		boolean running = true;
		Scanner scanner = new Scanner(System.in);
		String input;
		
		while (running) {
			System.out.println("Please enter \"file\" to enter a file or \"input\" to use the console.");
			input = scanner.nextLine();
			String numberInput = "";
			
			if (input.equals("file")) {  // Input from file
				try {
					System.out.println("Enter the file name:");
					input = scanner.nextLine();
					Scanner fileScanner = new Scanner(new File(input));
					numberInput = fileScanner.nextLine();
				} catch (IOException ex) {
					
					System.out.println("File could not be found.");
					scanner.close();
					System.exit(1);
				}
			}
			else {
				numberInput = scanner.nextLine();
			}
			
			System.out.println("To convert from decimal to binary, type \"d2b\".");
			System.out.println("To convert from binary to decimal, type \"b2d\".");
			input = scanner.nextLine();
			
			// Need to use long not int, because int cannot go to 16 digits (required for converting 65535 to binary)
			long finalAnswer = 0;
			
			if (input.equals("d2b")) { //decimal to binary
				String answer = "";
				int number = Integer.parseInt(numberInput);
				while (number > 0) {
					//Divides by 2, takes the remainder and puts that in front of "answer"
					if (number % 2 == 1) {
						answer = "1" + answer;
					}
					else {
						answer = "0" + answer;
					}
					number /= 2;
				}
				//Convert to int and store answer to variable outside the if
				finalAnswer = Long.parseLong(answer);
			}
			else if (input.equals("b2d")) { //binary to decimal
				
				int answer = 0;
				int i = 0;
				for (int a = numberInput.length() - 1; a >= 0; a--) {
					if (numberInput.charAt(a) == '1') {
						answer += (int)(Math.pow(2, i));
					}
					else if (numberInput.charAt(a) == '0') {
						answer += 0;
					}
					else {
						System.out.println("Unfortunately that number is not in binary to begin with.");
						answer = -1;
						break;
					}
					i++;
				}
				//Store answer to outside variable
				finalAnswer = (long)(answer);
			}
			else {
				System.out.println("I don't know what that means.");
			}
			
			if (finalAnswer != -1) {
				System.out.println("Badabing badaboom, your magically converted number: " + finalAnswer);
			}
			
			System.out.println("Would you like to try another number?");
			String yesno = scanner.nextLine().toLowerCase();
			
			if (yesno.equals("yes") || yesno.equals("y")) {
				System.out.println("");
				running = true;
			}
			else {
				running = false;
			}
		}
		scanner.close();
	}
	
	//Main method to start program
	public static void main(String[] args) {
		new BinaryTranslator();
	}

}