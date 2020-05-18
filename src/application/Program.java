package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employe;

public class Program {

	public static void main(String[] args) {
		Scanner sc = new Scanner (System.in);
		
		List<Employe> list = new ArrayList<>();
		
		System.out.print("Enter full file path: ");
		String path = sc.nextLine();
		System.out.print("Enter salary: ");
		double salarye = sc.nextDouble();
		
		
		try(BufferedReader br = new BufferedReader(new FileReader(path))){
			String line = br.readLine();
			while(line!= null) {
				String [] lines = line.split(",");
				String name = lines[0];
				String email = lines[1];
				double salary = Double.parseDouble(lines[2]);
				list.add(new Employe(name, email, salary));
				
				line = br.readLine();
			}
			
		}  
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Comparator<String> comp = (s1,s2) -> s1.toUpperCase().compareTo(s2.toUpperCase());
		
		List<String> email = list.stream().filter(p -> p.getSalary() >salarye).map(p -> p.getEmail()).sorted().sorted(comp).collect(Collectors.toList());
		
		double sum = list.stream().filter(p -> p.getName().charAt(0) == 'M').map(p -> p.getSalary()).reduce(0.0, (x,y) -> x+y);
		
		System.out.println("Email of people whose salary is more than 2000.00: ");
		email.forEach(System.out :: println );
		
		System.out.println("Sum of salary of people whose name starts with 'M': " + sum);
		
		
	}

}
