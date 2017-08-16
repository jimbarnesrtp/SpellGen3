package com.sandstorm.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PlayerCharacter {
	
	private boolean showDupes = false;
	private int str = 0;
	private int dex = 0;
	private int con = 0;
	private int wiz = 0;
	private int intel = 0;
	private int cha = 0;
	private String name = "";
	private CasterClass casterClass = null;
	private int casterLevel = 0;
	private LinkedHashMap<Integer, ArrayList<SpellListEntry>> spellBook = null;
	private LinkedHashMap<Integer, ArrayList<SpellListEntry>> memorizedSpells = null;
	private ArrayList<Domain> domains = new ArrayList<Domain>();
	
	
	public ArrayList<Domain> getDomains() {
		return domains;
	}
	public void setDomains(ArrayList<Domain> domains) {
		this.domains = domains;
	}
	public LinkedHashMap<Integer, ArrayList<SpellListEntry>> getSpellBook() {
		return spellBook;
	}
	public void setSpellBook(
			LinkedHashMap<Integer, ArrayList<SpellListEntry>> spellBook) {
		this.spellBook = spellBook;
	}
	public LinkedHashMap<Integer, ArrayList<SpellListEntry>> getMemorizedSpells() {
		return memorizedSpells;
	}
	public void setMemorizedSpells(
			LinkedHashMap<Integer, ArrayList<SpellListEntry>> memorizedSpells) {
		this.memorizedSpells = memorizedSpells;
	}
	public boolean isShowDupes() {
		return showDupes;
	}
	public void setShowDupes(boolean showDupes) {
		this.showDupes = showDupes;
	}
	public int getStr() {
		return str;
	}
	public void setStr(int str) {
		this.str = str;
	}
	public int getDex() {
		return dex;
	}
	public void setDex(int dex) {
		this.dex = dex;
	}
	public int getCon() {
		return con;
	}
	public void setCon(int con) {
		this.con = con;
	}
	public int getWiz() {
		return wiz;
	}
	public void setWiz(int wiz) {
		this.wiz = wiz;
	}
	public int getIntel() {
		return intel;
	}
	public void setIntel(int intel) {
		this.intel = intel;
	}
	public int getCha() {
		return cha;
	}
	public void setCha(int cha) {
		this.cha = cha;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CasterClass getCasterClass() {
		return casterClass;
	}
	public void setCasterClass(CasterClass casterClass) {
		this.casterClass = casterClass;
	}
	public int getCasterLevel() {
		return casterLevel;
	}
	public void setCasterLevel(int casterLeve) {
		this.casterLevel = casterLeve;
	}
	
	

}
