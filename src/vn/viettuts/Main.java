package vn.viettuts;

import java.util.Scanner;

public class Main {
	public static void main(String[] argv) {
		menu();
		Scanner scanner = new Scanner(System.in);
		StudentManager studentManager = new StudentManager();
		boolean exit = false;
		while(true) {
			System.out.print("Please choose: ");
			int choose = scanner.nextInt();
			scanner.nextLine();
			switch (choose) {
			case 1:
				studentManager.addStudent();
				break;
			case 2:
				studentManager.editStudent();
				break;
			case 3:
				studentManager.deleteStudent();
				break;
			case 4:
				studentManager.sortStudentByGPA();
				break;
			case 5:
				studentManager.sortStudentByName();
				break;
			case 6:
				studentManager.show();
				break;
			case 0: 
				System.out.println("GET OUT!!!");
				exit = true;
				break;
			default: 
				System.out.println("Invalid. Re-Choose: ");
				break;
			}
			
			if (exit) break;
		}
		
		
		
		
	}

	private static void menu() {
		System.out.println("-----------Menu------------");
        System.out.println("1. Add student.");
        System.out.println("2. Edit student by id.");
        System.out.println("3. Delete student by id.");
        System.out.println("4. Sort student by gpa.");
        System.out.println("5. Sort student by name.");
        System.out.println("6. Show student.");
        System.out.println("0. exit.");
        System.out.println("---------------------------");
        
	}
	
}
