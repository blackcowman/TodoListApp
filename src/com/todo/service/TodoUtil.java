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
		
		System.out.println("항목추가\n" + "[제목] : ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("중복 불가!");
			return;
		}
		sc.nextLine();
		System.out.println("[요일] : ");
		day = sc.nextLine();
		
		System.out.print("[카테고리] : ");
		category = sc.next().trim();
		sc.nextLine();
		
		System.out.print("[내용] : ");
		desc = sc.nextLine().trim();
		
		System.out.println("[필수 여부]");
		must_do = sc.next();
		
		System.out.print("[마감일자] : ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(title, day, desc, category, must_do, due_date);

		if (list.addItem(t) > 0)
			System.out.println("추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호을 입력하시오 > "
				);
		int index = sc.nextInt();
		
		if(l.deleteItem(index)>0)
			System.out.println("삭제되었습니다.");
	}
	
	public static void completeItem(TodoList l, int num) {
		
		Scanner sc = new Scanner(System.in);
		
		if(l.completeItem(num)>0)
			System.out.println("완료체크하였습니다.");
	}
	
	public static void multicompleteItem(TodoList l, int num) {
		
		Scanner sc = new Scanner(System.in);
		
		l.completeItem(num);
			
	}



	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date, new_day, new_must_do;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > "
				+ "\n");
		int index = sc.nextInt();
		System.out.print("새 요일 > ");
		new_day = sc.next().trim();
		System.out.print("새 제목 > ");
		new_title = sc.next().trim();
//		if (l.isDuplicate(new_title)) {
//			System.out.println("제목이 중복됩니다!");
//			return;
//		}
		System.out.print("새 카테고리 > ");
		 new_category = sc.next();
		sc.nextLine();  
		System.out.print("새 내용 > ");
		new_desc = sc.nextLine().trim();
		System.out.println("필수 여부 > ");
		new_must_do = sc.next();
		System.out.print("새 마감일자 > ");
		new_due_date = sc.nextLine().trim();

		
		TodoItem t = new TodoItem(new_title, new_day, new_desc, new_category, new_must_do, new_due_date);
		
		t.setId(index);
		if(l.updateItem(t)>0)
			System.out.println("수정되었습니다.");
		

	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
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
//			System.out.println("로딩 완료하였습니다.");
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
//			System.out.println("데이터를 저장하였습니다.");
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
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);

	}	
	
	public static void findCompList(TodoList l, int keyword) {
		int count = 0;
		for(TodoItem item : l.getComp(keyword)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);

	}
	
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void findMustdoList(TodoList l, String mustdo) {
		int count = 0;
		for(TodoItem item : l.getListMustdo(mustdo)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void findNextDayList(TodoList l, String day) {
		int count = 0;
		String nextDay = null;
		if(day.equals("월요일")) nextDay = "화요일";
		if(day.equals("화요일")) nextDay = "수요일";
		if(day.equals("수요일")) nextDay = "목요일";
		if(day.equals("목요일")) nextDay = "금요일";
		if(day.equals("금요일")) nextDay = "토요일";
		if(day.equals("토요일")) nextDay = "일요일";
		if(day.equals("일요일")) nextDay = "월요일";
		
		for(TodoItem item : l.getListDay(nextDay)) {
			System.out.println(item.toString());
			count++;
		}
			System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}
	

	
}

	
