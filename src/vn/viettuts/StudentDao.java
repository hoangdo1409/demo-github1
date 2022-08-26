package vn.viettuts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
	private String FILE_PATH = "student.txt";
	public void wirteToFile(ArrayList<Student> listStudent) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("student.txt"));
			oos.writeObject(listStudent);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Student> read() {
		ArrayList<Student> listStudent = new ArrayList<Student>();
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(FILE_PATH)));
//			for(int i = 0; i < listStudent.size(); i++) {
//				ois.readObject(listStudent.get(i));
//			}
			listStudent = (ArrayList<Student>) ois.readObject();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(ois);
		}
		return listStudent;
	}
	
	private void closeStream(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	
//	public ArrayList<Student> read() {
//		ArrayList<Student> listStudent = new ArrayList<Student>();
//		FileReader fileReader = null;
//		BufferedReader bufferIn = null;
//		try {
//			fileReader = new FileReader(FILE_PATH);
//			bufferIn = new BufferedReader(fileReader);
//			
//			String input = "";
//			int id = 0;
//			String name = "";
//			int age = 0;
//			String address = "";
//			double gpa = 0;
//			
//			while ((input = bufferIn.readLine()) != null){
//				//for (int i = 0; i < 5; i++) {
//					int temp = input.indexOf('\t');
//					id = Integer.parseInt(input.substring(0, temp));
//					name = input.substring(temp + 1);
//					age = Integer.parseInt(input.substring(temp + 2));
//					address = input.substring(temp + 3);
//					gpa = Double.parseDouble(input.substring(temp + 4));
//				//}
//				
//				listStudent.add(new Student(id, name, age, address, gpa));
//			}
//
//			bufferIn.close();
//		    fileReader.close();
//		} catch (FileNotFoundException e) {
//			System.out.println("Không tìm thấy file!");
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (bufferIn != null)
//					bufferIn.close();
//				if (fileReader != null)
//					fileReader.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		return listStudent;
//    }
}
