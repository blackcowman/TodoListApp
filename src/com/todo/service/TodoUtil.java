package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"
				+ "제목 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.println("같은 내용의 제목이 존재합니다!!");
			return;
		}
		
		
		System.out.print("카테고리 > ");
		category = sc.next();
		
		sc.nextLine();
			System.out.print("내용 > ");
			desc = sc.nextLine().trim();
			
			System.out.print("마감일자 > ");
			due_date = sc.next();
			
			TodoItem t = new TodoItem(title, desc, category, due_date);
			list.addItem(t);
			
		
		
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 번호을 입력하시오 > "
				);
		int num = sc.nextInt();
		
		if (l.lengthList()<num) {
			System.out.println("해당 번호는 존재하지 않습니다.");
			return;
		}
		

		
		int i=1;
		
		for (TodoItem item : l.getList()) {
			
			if (i==num) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다.");
			}
			i++;
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > "
				+ "\n");
		int num = sc.nextInt();
		if (l.lengthList()<num) {
			System.out.println("해당 번호는 존재하지 않습니다.");
			return;
		}

		System.out.print("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		System.out.print("새 카테고리 > ");
		String new_category = sc.next();
		
		sc.nextLine();  
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.print("새 마감일자 > ");
		String new_due_date = sc.next();
		int i=1;
		
		for (TodoItem item : l.getList()) {
			
			if (i==num) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
				l.addItem(t);
				System.out.println("수정되었습니다!!!");
			}
			i++;
		}

	}

	public static void listAll(TodoList l) {
		int size = l.lengthList();
		System.out.println("[전체 목록 총 " + size + "개]");
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
			
			System.out.print("모든 데이터를 저장했습니다\n");
		} catch (Exception e) {			
			System.out.println("예외처리");
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
				System.out.println("불러올 내용이 존재하지 않습니다");
				
			}else {
				System.out.println((i-1)+"개의 데이터를 불렀습니다.");

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
	
