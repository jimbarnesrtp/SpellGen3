package com.sandstorm.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Campaign {
	private int maxSpellLevel;
	private String saveDCExp;
	private int minStat;
	private int maxStat;
	private ArrayList<String> stats;
	private LinkedHashMap<Integer, StatModifier> modifiers;
	private LinkedHashMap<Integer, ArrayList<Integer>> bonusSpells;
	public int getMaxSpellLevel() {
		return maxSpellLevel;
	}
	public void setMaxSpellLevel(int maxSpellLevel) {
		this.maxSpellLevel = maxSpellLevel;
	}
	public String getSaveDCExp() {
		return saveDCExp;
	}
	public void setSaveDCExp(String saveDCExp) {
		this.saveDCExp = saveDCExp;
	}
	public int getMinStat() {
		return minStat;
	}
	public void setMinStat(int minStat) {
		this.minStat = minStat;
	}
	public int getMaxStat() {
		return maxStat;
	}
	public void setMaxStat(int maxStat) {
		this.maxStat = maxStat;
	}
	public ArrayList<String> getStats() {
		return stats;
	}
	public void setStats(ArrayList<String> stats) {
		this.stats = stats;
	}
	public LinkedHashMap<Integer, StatModifier> getModifiers() {
		return modifiers;
	}
	public void setModifiers(LinkedHashMap<Integer, StatModifier> modifiers) {
		this.modifiers = modifiers;
	}
	public LinkedHashMap<Integer, ArrayList<Integer>> getBonusSpells() {
		return bonusSpells;
	}
	public void setBonusSpells(LinkedHashMap<Integer, ArrayList<Integer>> bonusSpells) {
		this.bonusSpells = bonusSpells;
	}
	
	
	

}
