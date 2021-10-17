package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	Connection conn;
	
	private List<TodoItem> list;

	public TodoList() {
		this.conn =  DbConnect.getConnection();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (day, title, is_completed, memo, category, must_do, current_date, due_date)" + "values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getDay());
			pstmt.setString(2, t.getTitle());
			pstmt.setInt(3, t.getIs_completed());
			pstmt.setString(4, t.getDesc());
			pstmt.setString(5, t.getCategory());
			pstmt.setString(6, t.getMust_do());
			pstmt.setString(7, t.getCurrent_date());
			pstmt.setString(8, t.getDue_date());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}


	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int completeItem(int index) {
		String sql = "update list set is_completed=1 " + "where id=?;";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int lengthList() {
		return list.size();
	}
	
	public int updateItem(TodoItem t) {
			String sql = "update list set title=?, is_completed=?, memo=?, category=?, current_date=?, due_date=?, must_do=?, day=? " + " where id = ?;";
			PreparedStatement pstmt;
			int count = 0;
			try {
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, t.getTitle());
				pstmt.setInt(2, t.getIs_completed());
				pstmt.setString(3, t.getDesc());
				pstmt.setString(4, t.getCategory());
				pstmt.setString(5, t.getCurrent_date());
				pstmt.setString(6, t.getDue_date());
				pstmt.setString(7, t.getMust_do());
				pstmt.setString(8, t.getDay());
				pstmt.setInt(9, t.getId());
				count = pstmt.executeUpdate();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();	
			}
			return count;
	}

//	void editItem(TodoItem t, TodoItem updated) {
//		int index = list.indexOf(t);
//		list.remove(index);
//		list.add(updated);
//		
//	}
 
	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, keyword);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String day = rs.getString("day");
					String category = rs.getString("category");
					String title = rs.getString("title");
					String memo = rs.getString("memo");
					int is_completed = rs.getInt("is_completed");
					String must_do = rs.getString("must_do");
					String due_date = rs.getString("due_date");
					String current_date = rs.getString("current_date");
					TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
					t.setId(id);
					t.setIs_completed(is_completed);
					t.setCurrent_date(current_date);
					list.add(t);
					
				}
				pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}
	
	public ArrayList<TodoItem> getComp(int keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;

		try {
			
			String sql = "SELECT * FROM list WHERE is_completed like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, keyword);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					int id = rs.getInt("id");
					String day = rs.getString("day");
					String category = rs.getString("category");
					String title = rs.getString("title");
					String memo = rs.getString("memo");
					int is_completed = rs.getInt("is_completed");
					String must_do = rs.getString("must_do");
					String due_date = rs.getString("due_date");
					String current_date = rs.getString("current_date");
					TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
					t.setId(id);
					t.setIs_completed(is_completed);
					t.setCurrent_date(current_date);
					list.add(t);
					
				}
				pstmt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	
		return list;
	}
	

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}
	public void listAll() {
		for (TodoItem myitem : list) {
			System.out.println(myitem.toString());
		}
	}

	public ArrayList<TodoItem> getOrderedList(String orderby,int ordering) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "select * from list order by "+ orderby;
			if (ordering == 0) {
				sql+=" desc";
			}
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String day = rs.getString("day");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String must_do = rs.getString("must_do");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
				t.setId(id);
				t.setCurrent_date(current_date);
				t.setIs_completed(is_completed);
				list.add(t);
				
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String day = rs.getString("day");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String must_do = rs.getString("must_do");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
				t.setId(id);
				t.setIs_completed(is_completed);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getListMustdo(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		String mustdo = "%필수%";
		try {
			
			String sql = "SELECT * FROM list WHERE day like ? and must_do like ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, keyword);
				pstmt.setString(2, mustdo);
				ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String day = rs.getString("day");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String must_do = rs.getString("must_do");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
				t.setId(id);
				t.setIs_completed(is_completed);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	//getListMustdo
	public ArrayList<TodoItem> getListDay(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";			
			String sql = "SELECT * FROM list WHERE day like ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String day = rs.getString("day");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String must_do = rs.getString("must_do");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
				t.setId(id);
				t.setIs_completed(is_completed);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			pstmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String category=rs.getString("category");
				System.out.print(category+" ");
				count++;
				
			}
			System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n",count);
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		
		try {
			stmt = conn.createStatement();
			String sql="SELECT * FROM list ";
			ResultSet rs= stmt.executeQuery(sql);
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String day = rs.getString("day");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String must_do = rs.getString("must_do");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
				t.setId(id);
				t.setIs_completed(is_completed);
				t.setCurrent_date(current_date);
				list.add(t);
				
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());

	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public void duplicate(int index) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select title, memo, category ,due_date , current_date, is_completed,percent,difficulty from list where id='" +  index + "'");
			while(rs.next()) {
				String day = rs.getString("day");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String memo = rs.getString("memo");
				String must_do = rs.getString("must_do");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				TodoItem t = new TodoItem(day, title,memo,category, must_do,due_date);
				t.setId(index);
				t.setIs_completed(is_completed);
				t.setCurrent_date(current_date);
				System.out.println(t.toString());
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}



	
	public void isFind(String title) {
		int i=0;
		for (TodoItem item : list) {
			if (item.getTitle().contains(title)) {
				
						System.out.print(++i+ ". ");
						System.out.println(item.toString());
						System.out.println("");
						
	
			}else if (item.getDesc().contains(title)) {
				System.out.print(++i+ ". ");
				System.out.println(item.toString());
				System.out.println("");
				
			}
		}
		
	}
	
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql="insert into list (title,memo,category,current_date,due_date)"
					+ " values (?,?,?,?,?);";
			int records = 0;
			while((line=br.readLine())!= null) {
				StringTokenizer st = new StringTokenizer(line,"##");
				String category= st.nextToken();
				String title= st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,title);
				pstmt.setString(2,description);
				pstmt.setString(3,category);
				pstmt.setString(4,current_date);
				pstmt.setString(5,due_date);
				
				int count = pstmt.executeUpdate();
				if (count>0) records++;
				pstmt .close();
				
			}
			System.out.println(records+" records read!!");
			br.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public boolean isDuplicate(String duplicate) {
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE title = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, duplicate);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String title = rs.getString("title");
				if(title.equals(duplicate))
					return true;
			}
			pstmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
