package com.sandstorm.model;

import java.util.ArrayList;

public class Spell implements Comparable<Spell> {
	private String name;
	private String school;
	private ArrayList<String> descriptors;
	private String alignment;
	private String properties;
	private String castingTime;
	private String range;
	private String duration;
	private String area;
	private ArrayList<String> components;
	private String save;
	private int saveDCMod;
	private boolean isSR;
	private String description;
	private int page = 0;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public ArrayList<String> getDescriptors() {
		return descriptors;
	}
	public void setDescriptors(ArrayList<String> descriptors) {
		this.descriptors = descriptors;
	}
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	public String getProperties() {
		return properties;
	}
	public void setProperties(String properties) {
		this.properties = properties;
	}
	public String getCastingTime() {
		return castingTime;
	}
	public void setCastingTime(String castingTime) {
		this.castingTime = castingTime;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public ArrayList<String> getComponents() {
		return components;
	}
	public void setComponents(ArrayList<String> components) {
		this.components = components;
	}
	public String getSave() {
		return save;
	}
	public void setSave(String save) {
		this.save = save;
	}
	public int getSaveDCMod() {
		return saveDCMod;
	}
	public void setSaveDCMod(int saveDCMod) {
		this.saveDCMod = saveDCMod;
	}
	public boolean isSR() {
		return isSR;
	}
	public void setSR(boolean isSR) {
		this.isSR = isSR;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public int compareTo(Spell o) {
		return name.compareTo(o.name);
	
	}
	
	
	
	
    
    
	
	
	

}
