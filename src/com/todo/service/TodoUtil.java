package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[�׸� �߰�]\n"
				+ "���� > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("���� ������ ������ �����մϴ�!!");
			return;
		}
		
		
		System.out.print("ī�װ� > ");
		category = sc.next();
		
		sc.nextLine();
			System.out.print("���� > ");
			desc = sc.nextLine().trim();
			
			System.out.print("�������� > ");
			due_date = sc.next();
			
			TodoItem t = new TodoItem(title, desc, category, due_date);
			list.addItem(t);
			
		
		
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > "
				);
		int num = sc.nextInt();
		
		if (l.lengthList()<num) {
			System.out.println("�ش� ��ȣ�� �������� �ʽ��ϴ�.");
			return;
		}
		

		
		int i=1;
		
		for (TodoItem item : l.getList()) {
			
			if (i==num) {
				l.deleteItem(item);
				System.out.println("�����Ǿ����ϴ�.");
			}
			i++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > "
				+ "\n");
		int num = sc.nextInt();
		if (l.lengthList()<num) {
			System.out.println("�ش� ��ȣ�� �������� �ʽ��ϴ�.");
			return;
		}

		System.out.print("�� ���� > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��˴ϴ�!");
			return;
		}
		System.out.print("�� ī�װ� > ");
		String new_category = sc.next();
		
		sc.nextLine();  
		System.out.print("�� ���� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("�� �������� > ");
		String new_due_date = sc.next();
		int i=1;
		
		for (TodoItem item : l.getList()) {
			
			if (i==num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("�����Ǿ����ϴ�!!!");
			}
			i++;
		}

	}

	public static void listAll(TodoList l) {
		int size = l.lengthList();
		System.out.println("[��ü ��� �� " + size + "��]");
		int i=0;
		for (TodoItem item : l.getList()) {
			System.out.print(++i+ ". ");
			System.out.println(item.toString());
			System.out.println("");
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try (FileWriter fo = new FileWriter(filename)) {
			
			for (TodoItem item : l.getList()) {
				fo.write(item.toSaveString());
				fo.write("\n");
			}
			
			System.out.print("��� �����͸� �����߽��ϴ�\n");
		} catch (Exception e) {			
			System.out.println("����ó��");
		}

	}
	
	public static void LoadList(TodoList l, String filename){
		// TODO Auto-generated method stub
		String title, desc, current_date, category, due_date;
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			
			
			
			int i;
			for(i = 1; (line = br.readLine()) != null; i++){
				
			StringTokenizer st = new StringTokenizer(line, "##", false);
			
				while(st.hasMoreTokens()) {
					category = st.nextToken();
					title = st.nextToken();
					desc = st.nextToken();
					due_date = st.nextToken();
					current_date= st.nextToken();
					TodoItem t = new TodoItem(title, desc, current_date, category, due_date);
					l.addItem(t);
				}
					
					
				
			}
			if(i==1) {
				System.out.println("�ҷ��� ������ �������� �ʽ��ϴ�");
				
			}else {
				System.out.println((i-1)+"���� �����͸� �ҷ����ϴ�.");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
	
	public static void FindList(TodoList l, String filename) {
		
		
		
		
			l.isFind(filename);
			
			
	
	}
}
	
