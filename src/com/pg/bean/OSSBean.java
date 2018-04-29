package com.pg.bean;
import java.util.ArrayList;
public class OSSBean {
    private String current_dir_path;
    private String current_url;
    @SuppressWarnings("rawtypes")
	private ArrayList file_list;    
    private String moveup_dir_path;
    private Integer total_count;
	public String getCurrent_dir_path() {
		return current_dir_path;
	}
	public void setCurrent_dir_path(String current_dir_path) {
		this.current_dir_path = current_dir_path;
	}
	public String getCurrent_url() {
		return current_url;
	}
	public void setCurrent_url(String current_url) {
		this.current_url = current_url;
	}

	public String getMoveup_dir_path() {
		return moveup_dir_path;
	}
	public void setMoveup_dir_path(String moveup_dir_path) {
		this.moveup_dir_path = moveup_dir_path;
	}
	public Integer getTotal_count() {
		return total_count;
	}
	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}
	@SuppressWarnings("rawtypes")
	public ArrayList getFile_list() {
		return file_list;
	}
	@SuppressWarnings("rawtypes")
	public void setFile_list(ArrayList file_list) {
		this.file_list = file_list;
	}

}