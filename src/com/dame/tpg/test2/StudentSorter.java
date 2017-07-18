package com.dame.tpg.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StudentSorter {

	public static void main(String[] args) {
		
		Student student1 = new Student(33, "Tina", 3.68);
		Student student2 = new Student(85, "Louis", 3.85);
		Student student3 = new Student(56, "Samil", 3.75);
		Student student4 = new Student(19, "Samar", 3.75);
		Student student5 = new Student(22, "Lorry", 3.76);
		Student student6 = new Student(32, "Samil", 3.75);
		
		List<Student> studentsBeforeSort = new ArrayList<Student>();
		
		studentsBeforeSort.add(student1);
		studentsBeforeSort.add(student6);
		studentsBeforeSort.add(student5);
		studentsBeforeSort.add(student3);
		studentsBeforeSort.add(student2);
		studentsBeforeSort.add(student4);
		
		Collections.sort(studentsBeforeSort);
		
		System.out.println("After sort : ");
		for (Student student : studentsBeforeSort) {
			System.out.println("id="+student.getId()+", name="+student.getName()+", gpa="+student.getCgpa());
		}
	}

}
