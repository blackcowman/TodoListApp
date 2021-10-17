package com.todo.service;
import java.sql.PreparedStatement;
import java.util.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
public static void createItem(TodoList list) {
		
		String title, desc, category, due_date, day, must_do;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�׸��߰�\n" + "[����] : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("�ߺ� �Ұ�!");
			return;
		}
		sc.nextLine();
		System.out.println("[����] : ");
		day = sc.nextLine();
		
		System.out.print("[ī�װ�] : ");
		category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("[����] : ");
		desc = sc.nextLine().trim();
		
		System.out.println("[�ʼ� ����]");
		must_do = sc.next();
		
		System.out.print("[��������] : ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, day, desc, category, must_do, due_date);

		if (list.addItem(t) > 0)
			System.out.println("�߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > "
				);
		int index = sc.nextInt();
		
		if(l.deleteItem(index)>0)
			System.out.println("�����Ǿ����ϴ�.");
	}
	
	public static void completeItem(TodoList l, int num) {
		
		Scanner sc = new Scanner(System.in);
		
		if(l.completeItem(num)>0)
			System.out.println("�Ϸ�üũ�Ͽ����ϴ�.");
	}
	
	public static void multicompleteItem(TodoList l, int num) {
		
		Scanner sc = new Scanner(System.in);
		
		l.completeItem(num);
			
	}



	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date, new_day, new_must_do;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > "
				+ "\n");
		int index = sc.nextInt();
		System.out.print("�� ���� > ");
		new_day = sc.next().trim();
		System.out.print("�� ���� > ");
		new_title = sc.next().trim();
//		if (l.isDuplicate(new_title)) {
//			System.out.println("������ �ߺ��˴ϴ�!");
//			return;
//		}
		System.out.print("�� ī�װ� > ");
		 new_category = sc.next();
		sc.nextLine();  
		System.out.print("�� ���� > ");
		new_desc = sc.nextLine().trim();
		System.out.println("�ʼ� ���� > ");
		new_must_do = sc.next();
		System.out.print("�� �������� > ");
		new_due_date = sc.nextLine().trim();

		
		TodoItem t = new TodoItem(new_title, new_day, new_desc, new_category, new_must_do, new_due_date);
		
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("�����Ǿ����ϴ�.");
		

	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[��ü ���, �� %d��]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader("todolist.txt"));
//			
//			String oneline;
//			while((oneline = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(oneline, "##");
//				String category = st.nextToken();
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				String due_date = st.nextToken();
//				
//				TodoItem t = new TodoItem(title, desc, category, due_date);
//				l.addItem(t);
//			}
//			br.close();
//			System.out.println("�ε� �Ϸ��Ͽ����ϴ�.");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
//	public static void saveList(TodoList l, String filename) {
//		try { l
//			Writer w = new FileWriter("todolist.txt");
//			for(TodoItem item : l.getList()) {
//				w.write(item.toSaveString());
//			}
//			
//			System.out.println("�����͸� �����Ͽ����ϴ�.");
//			w.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for(String item : l.getCategories()) {
			System.out.println(item + "");
			count++;
		}
		System.out.printf("\n�� %d���� ī�װ��� ��ϵǾ� �ֽ��ϴ�.\n", count);
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);

	}	
	
	public static void findCompList(TodoList l, int keyword) {
		int count = 0;
		for(TodoItem item : l.getComp(keyword)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);

	}
	
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void findMustdoList(TodoList l, String mustdo) {
		int count = 0;
		for(TodoItem item : l.getListMustdo(mustdo)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	
	public static void findNextDayList(TodoList l, String day) {
		int count = 0;
		String nextDay = null;
		if(day.equals("������")) nextDay = "ȭ����";
		if(day.equals("ȭ����")) nextDay = "������";
		if(day.equals("������")) nextDay = "�����";
		if(day.equals("�����")) nextDay = "�ݿ���";
		if(day.equals("�ݿ���")) nextDay = "�����";
		if(day.equals("�����")) nextDay = "�Ͽ���";
		if(day.equals("�Ͽ���")) nextDay = "������";
		
		for(TodoItem item : l.getListDay(nextDay)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("�� %d���� �׸��� ã�ҽ��ϴ�.\n", count);
	}
	

	
}

	
