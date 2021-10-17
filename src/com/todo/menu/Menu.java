package com.todo.menu;
public class Menu {

	 public static void displaymenu()
	    {
	        System.out.println();
	        System.out.println("[ToDoList 명령어]");
	        System.out.println("1. add ( 추가 )");
	        System.out.println("2. del ( 삭제 )");
	        System.out.println("3. edit ( 수정 )");
	        System.out.println("4. ls ( 목록 )");
	        System.out.println("5. ls_name ( 제목순 정렬 )");
	        System.out.println("6. ls_name_desc ( 제목역순 정렬 )");
	        System.out.println("7. ls_date ( 날짜순 정렬 )");
	        System.out.println("8. ls_date_desc (날짜역순 정렬)");
	        System.out.println("9. find ( 검색 )");
	        System.out.println("10. find_cate ( 카테고리 찾기 )");
	        System.out.println("11. ls_cate ( 카테고리 정렬 )");
	        System.out.println("12. comp ( 완료 체크 )");
	        System.out.println("13. ls_comp ( 완료 체크된 것만 출력 )");
	        System.out.println("14. today ( 내일 할 운동 )");
	        System.out.println("15. ls_mustdo ( 오늘 필수로 해야하는 운동 )");
	        System.out.println("16. multi_comp ( 여러 개 완료체크 )");
	        System.out.println("0. exit ( 종료 )");
	 
	    }
    
    public static void prompt()
    {
    	System.out.print("Command >");
    }
}
