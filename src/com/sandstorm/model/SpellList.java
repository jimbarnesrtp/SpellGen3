package com.sandstorm.model;

import java.util.ArrayList;

public class SpellList {
	
	private String name;
	private ArrayList<SpellListEntry> spellList;
	private String inherits;
	
	
	
	public String getInherits() {
		return inherits;
	}
	public void setInherits(String inherits) {
		this.inherits = inherits;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<SpellListEntry> getSpellList() {
		return spellList;
	}
	public void setSpellList(ArrayList<SpellListEntry> spellList) {
		this.spellList = spellList;
	}
	
	

}
