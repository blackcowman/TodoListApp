package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. ���ο� �׸� �߰� ( add )");
        System.out.println("2. ���� �׸� ���� ( del )");
        System.out.println("3. �׸� ���� ( edit )");
        System.out.println("4. ��� Ȯ�� ( ls )");
        System.out.println("5. �̸��� ���� ( ls_name_asc )");
        System.out.println("6. �̸����� ���� ( ls_name_desc )");
        System.out.println("7. ��¥�� ���� ( ls_date )");
        System.out.println("8. ���ڿ� ã�� ( find )");
        System.out.println("9. ������ (Or press escape key to exit)");
    }
    
    public static void prompt()
    {
    	System.out.print("Command >");
    }
}
