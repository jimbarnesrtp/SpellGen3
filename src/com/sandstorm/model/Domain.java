package com.sandstorm.model;

import java.util.ArrayList;

public class Domain implements Comparable<Domain>{
	private String name;
	private String abbrev;
	private String power;
	private ArrayList<SpellListEntry> entries;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	public String getPower() {
		return power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public ArrayList<SpellListEntry> getEntries() {
		return entries;
	}
	public void setEntries(ArrayList<SpellListEntry> entries) {
		this.entries = entries;
	}
	@Override
	public int compareTo(Domain o) {
		return name.compareTo(o.getName());
	}
	
	

}
