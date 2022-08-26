package vn.viettuts;

import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
	public static Scanner scanner = new Scanner(System.in);
	private ArrayList<Student> listStudent = new ArrayList<Student>();
	
	private StudentDao StudentDao;
	
	public StudentManager() {
        StudentDao = new StudentDao();
        listStudent = StudentDao.read();
    }
	
	public void addStudent() {
		Student s = new Student();
		
		s.setId(enterId());
		
		s.setName(enterName());
		s.setAge(enterAge());
		
		s.setAddress(enterAddress());

		s.setGpa(enterGpa());
		
		listStudent.add(s);
		StudentDao.wirteToFile(listStudent);
		
		
	}
	
	public void editStudent() {
		System.out.println("Enter the student id to edit: ");
		int id = Integer.parseInt(scanner.nextLine());
		if (isExisted(listStudent, id)) {
			for(int i = 0; i < listStudent.size(); i++) {
				if (listStudent.get(i).getId() == id) {
					listStudent.get(i).setName(enterName());
					listStudent.get(i).setAge(enterAge());
					listStudent.get(i).setAddress(enterAddress());
					listStudent.get(i).setGpa(enterGpa());
					System.out.println("Successfully!!!");
					break;
				}
			}
		} else {
			System.out.println("ID does not exist. Try add a new student.");
		}
	}
	
	public void deleteStudent() {
		System.out.println("Enter the student id to delete: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		if (isExisted(listStudent, id)) {
			for(int i = 0; i < listStudent.size(); i++) {
				if (listStudent.get(i).getId() == id) {
					listStudent.remove(i);
					overWrite();
					StudentDao.wirteToFile(listStudent);
					System.out.println("Successfully Deleted!!!");
					break;
				}
			}
		} else {
			System.out.println("ID does not exist.");
		}
	}
	
	private int enterId() {
		int id;
		while(true) {
			try {
				System.out.print("Enter student id: ");
				id = Integer.parseInt(scanner.nextLine());
			
				if (isExisted(listStudent, id) == true) {
					System.out.println("ID was existed. Re-enter ID.");
				} else break;
			} catch (Exception e) {
				System.out.println("Invalid ID. Please Re-Enter ID.");
			}
		}
		return id;
	}
	
	private String enterName() {
		String name = "";
		System.out.print("Enter student name: ");
		name = scanner.nextLine();
		return name;
	}
	
	private int enterAge() {
		int age;
		while(true) {
			try {
				System.out.print("Enter student age: ");
				age = Integer.parseInt(scanner.nextLine());
				break;
			} catch (Exception e) {
				System.out.println("Invalid number. Please Re-Enter Age.");
			}
		}
		return age;
	}
	
	private String enterAddress() {
		String address = "";
		System.out.print("Enter student address: ");
		address = scanner.nextLine();
		return address;
	} 
	
	private double enterGpa() {
		System.out.print("Enter student gpa: ");
		double gpa;
		while(true) {
			try {
				gpa = Double.parseDouble((scanner.nextLine()));
				if (gpa < 0.0 || gpa > 10.0) {
					System.out.println("Gpa was wrong! Re-enter GPA.");
					continue;
				} else break;
			} catch (Exception e) {
				System.out.println("Invalid number. Please Re-Enter Age.");
			}
		}
		return gpa;
	}

	public boolean isExisted(List<Student> student, int id) {
		for(int i = 0; i < student.size(); i++) {
			Student s = student.get(i);
			if (s.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	public void sortStudentByName() {
		Collections.sort(listStudent, new SortStudentByName());
		show();
	}
	
	public void sortStudentByGPA() {
		Collections.sort(listStudent, new SortStudentByGPA());
		show();
	}
	
	public void show() {
		
		for (Student student : listStudent) {
            System.out.format("%5d | ", student.getId());
            System.out.format("%20s | ", student.getName());
            System.out.format("%5d | ", student.getAge());
            System.out.format("%20s | ", student.getAddress());
            System.out.format("%10.1f%n", student.getGpa());
        }
	}
	
	public void overWrite() {
		BufferedWriter bw = null;
        FileWriter fw = null;
 
        try {
            String data = "";
            File file = new File("C:\\Users\\Admin\\eclipse-workspace\\QLSV\\student.txt");
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // true = append file
            fw = new FileWriter(file.getAbsoluteFile(), false);
            bw = new BufferedWriter(fw);
            bw.write(data);
            System.out.println("Success...");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
}
