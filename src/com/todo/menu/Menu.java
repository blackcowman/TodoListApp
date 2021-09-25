package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. 새로운 항목 추가 ( add )");
        System.out.println("2. 기존 항목 제거 ( del )");
        System.out.println("3. 항목 수정 ( edit )");
        System.out.println("4. 목록 확인 ( ls )");
        System.out.println("5. 이름순 정렬 ( ls_name_asc )");
        System.out.println("6. 이름역순 정렬 ( ls_name_desc )");
        System.out.println("7. 날짜순 정렬 ( ls_date )");
        System.out.println("8. 문자열 찾기 ( find )");
        System.out.println("9. 나가기 (Or press escape key to exit)");
    }
    
    public static void prompt()
    {
    	System.out.print("Command >");
    }
}
