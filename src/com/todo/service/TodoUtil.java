package com.todo.service;

import java.util.*;
import java.io.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 추가]\n"
				+ "제목 > ");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("같은 내용의 제목이 존재합니다!!");
			return;
		}
			sc.nextLine();
			System.out.println("내용 > ");
			desc = sc.nextLine().trim();
			
			TodoItem t = new TodoItem(title, desc);
			list.addItem(t);
			
		
		
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\n"
				+ "[항목 삭제]\n"
				+ "삭제할 항목의 제목을 입력하시오 > "
				);
		String title = sc.next();
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.print("삭제되었습니다.");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "[항목 수정]\n"
				+ "수정할 항목의 제목을 입력하시오 > "
				+ "\n");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("없는 제목입니다ㅜㅠ");
			return;
		}

		System.out.println("새 제목 > ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다!");
			return;
		}
		sc.nextLine();  
		System.out.println("새 내용 > ");
		String new_description = sc.nextLine().trim();

		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다!!!");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록]");
		for (TodoItem item : l.getList()) {
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
		String title, desc, current_date;
		
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			
			
			
			int i;
			for(i = 1; (line = br.readLine()) != null; i++){
				
			StringTokenizer st = new StringTokenizer(line, "##", false);
			
				while(st.hasMoreTokens()) {
					title = st.nextToken();
					desc = st.nextToken();
					current_date= st.nextToken();
					TodoItem t = new TodoItem(title, desc, current_date);
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
}
