package com.todo.dao;

import java.util.Date;
import java.text.SimpleDateFormat;

public class TodoItem {
    private String title;
    private String desc;
    private String current_date;
    private String category; 
    private String due_date;
    private SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private int id;
    private String must_do;
    private String day;
    private int is_completed;

    public TodoItem(String title, String day, String desc, String category, String must_do, String due_date){
        this.title=title;
        this.desc=desc;
        this.category=category;
        this.due_date=due_date;
        this.day = day;
        this.must_do = must_do;
        this.current_date= f.format(new Date());
        this.is_completed = 0;
    }
    
    public TodoItem(String title, String desc, String current_date, String category, String due_date){
        this.title=title;
        this.desc=desc;
        this.current_date= current_date ;
        this.category = category;
        this.due_date = due_date;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
  
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }
    public void setCategory(String category) {
		this.category = category;
	}
    
    public String getCategory() {
		return category;
	}
    
    public int getId() {
    	return id;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
   
    public String getDue_date() {
		return due_date;
	}
    public void setDue_date(String due_date) {
		this.due_date = due_date;
	}
    
    public String getDay() {
		return day;
	}
    public void setDay(String day) {
		this.day = day;
	}
    public String getMust_do() {
		return must_do;
	}
    public void setMust_do(String must_do) {
		this.must_do = must_do;
	}
    
    public void setIs_completed(int is_completed) {
		this.is_completed = is_completed;
	}
    public int getIs_completed() {
		return is_completed;
	}
    
    
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub 
    	 	if(is_completed==0) return id + ". [" + title  + "]" + " [" + category  + "] " + day + " - " + desc + " - " + " - " + due_date + " - "+ current_date+" ["+ must_do +"]";
    	 	else  return id + ". [" + title  + "]" + " [" + category  + "] " + day + " [V] " + " - " + desc + " - " + " - " + due_date + " - "+ current_date+" ["+ must_do +"]";

    }
    
    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    public String toSaveString() {
        return category + "##" + title + "##" + desc + "##" + due_date + "##" + current_date;
    }
    
}
