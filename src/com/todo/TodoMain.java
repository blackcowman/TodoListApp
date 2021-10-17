package com.todo;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start(){
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		//l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		
		
		
		Menu.displaymenu();  
		
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
		
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;

			case "ls_name":
				System.out.print("��������� �����߽��ϴ�.\n");
				TodoUtil.listAll(l,"title", 1);
				break;

			case "ls_name_desc":
				System.out.print("���񿪼����� �����߽��ϴ�.\n");
				TodoUtil.listAll(l,"title", 0);
				break;
				
			case "ls_date":
				System.out.print("��¥������ �����߽��ϴ�.\n");
				TodoUtil.listAll(l,"due_date", 1);
				break;
				
			case "ls_date_desc":
				System.out.print("��¥�������� �����߽��ϴ�.\n");
				TodoUtil.listAll(l,"due_date", 0);
				break;

			case "exit":
				quit = true;
				System.out.println("����Ǿ����ϴ�.");
//				TodoUtil.saveList(l, "todolist.txt");
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "find":
				String find = sc.next();
				TodoUtil.findList(l, find);
				break;
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "today":
				String day = sc.nextLine().trim();
				TodoUtil.findNextDayList(l,day);
				break;
			
			case "ls_mustdo":
				String mustdo = sc.nextLine().trim();
				TodoUtil.findMustdoList(l,mustdo);
				break;
			
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l,cate);
				break;
			case "comp":
				int comp = sc.nextInt();
				TodoUtil.completeItem(l,comp);
				break;
			case "ls_comp":
				TodoUtil.findCompList(l,1);
				break;
			case "multi_comp":
				System.out.println("��� üũ�Ͻ� �̴ϱ�?");
				int repeat = sc.nextInt();
				int mul_comp;
				System.out.println("�Ϸ��� ��ȣ�� �ۼ����ּ���");
				for(int i = 0; i<repeat; i++) {
					mul_comp = sc.nextInt();
					TodoUtil.completeItem(l,mul_comp);
				}
				System.out.println("üũ�� �Ϸ��߽��ϴ�");
				break;
			default:
				System.out.println("��Ȯ�� ��ɾ �Է��ϼ���. (���� - help)");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
//		TodoUtil.Savelist(l,"todolist.txt");
	}
}
