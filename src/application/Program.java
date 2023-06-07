package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		List<Employee> list = new ArrayList<>();
		String path = "C:\\dev\\eclipse-workspace\\filtrar-ComparableInterface\\salariosFuncionarios.txt";

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String employee = br.readLine();
			while (employee != null) {
				String[] fields = employee.split(",");
				list.add(new Employee(fields[0], Double.parseDouble(fields[1])));
				employee = br.readLine();
			}
			Collections.sort(list);

			File folderPathStr = new File(path);
			String folderPath = folderPathStr.getParent() + "\\funcionariosEmOrdem.txt";

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(folderPath))) {
				for (Employee emp : list) {
					bw.write(emp.getName() + "," + emp.getSalary());
					bw.newLine();
				}
				System.out.println("Funcionarios ordenados!");
			} catch (IOException e) {
				System.out.println("Erro writing file: " + e.getMessage());
			}

		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}

		finally {
			sc.close();
		}

	}

}
