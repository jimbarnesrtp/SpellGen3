package com.sandstorm.model;

public class SpellListEntry implements Comparable<SpellListEntry>{
	private String name;
	private int level;
	private String reference;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	@Override
	public int compareTo(SpellListEntry o) {
		if(level < o.level) {
			return -1;
		} else if(level > o.level){
			return 1;
		} else {
			return name.compareTo(o.name);
		}
	}
	
	
	 

}
