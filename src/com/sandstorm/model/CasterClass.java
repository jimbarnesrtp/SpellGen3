package com.sandstorm.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class CasterClass {
	private String name;
	private String abbrev;
	private String spellList;
	private String casterType;
	private boolean hasCantrips;
	private int maxSpellLevel;
	private int maxCasterLevel;
	private String castinStat;
	private String bonusSpellStat;
	private String saveDCStat;
	private boolean knowAllSpells;
	private boolean knowAllCantrips;
	private boolean spellsKnown;
	private double casterLevelMultiplier;
	private LinkedHashMap<Integer, ArrayList<Integer>> spellSlots;
	private LinkedHashMap<Integer, ArrayList<Integer>> KnownSpellSlots;
	
	
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
	public String getSpellList() {
		return spellList;
	}
	public void setSpellList(String spellList) {
		this.spellList = spellList;
	}
	public String getCasterType() {
		return casterType;
	}
	public void setCasterType(String casterType) {
		this.casterType = casterType;
	}
	public boolean isHasCantrips() {
		return hasCantrips;
	}
	public void setHasCantrips(boolean hasCantrips) {
		this.hasCantrips = hasCantrips;
	}
	public int getMaxSpellLevel() {
		return maxSpellLevel;
	}
	public void setMaxSpellLevel(int maxSpellLevel) {
		this.maxSpellLevel = maxSpellLevel;
	}
	public int getMaxCasterLevel() {
		return maxCasterLevel;
	}
	public void setMaxCasterLevel(int maxCasterLevel) {
		this.maxCasterLevel = maxCasterLevel;
	}
	public String getCastinStat() {
		return castinStat;
	}
	public void setCastinStat(String castinStat) {
		this.castinStat = castinStat;
	}
	public String getBonusSpellStat() {
		return bonusSpellStat;
	}
	public void setBonusSpellStat(String bonusSpellStat) {
		this.bonusSpellStat = bonusSpellStat;
	}
	public String getSaveDCStat() {
		return saveDCStat;
	}
	public void setSaveDCStat(String saveDCStat) {
		this.saveDCStat = saveDCStat;
	}
	public boolean isKnowAllSpells() {
		return knowAllSpells;
	}
	public void setKnowAllSpells(boolean knowAllSpells) {
		this.knowAllSpells = knowAllSpells;
	}
	public boolean isKnowAllCantrips() {
		return knowAllCantrips;
	}
	public void setKnowAllCantrips(boolean knowAllCantrips) {
		this.knowAllCantrips = knowAllCantrips;
	}
	public boolean isSpellsKnown() {
		return spellsKnown;
	}
	public void setSpellsKnown(boolean spellsKnown) {
		this.spellsKnown = spellsKnown;
	}
	public double getCasterLevelMultiplier() {
		return casterLevelMultiplier;
	}
	public void setCasterLevelMultiplier(double casterLevelMultiplier) {
		this.casterLevelMultiplier = casterLevelMultiplier;
	}
	public LinkedHashMap<Integer, ArrayList<Integer>> getSpellSlots() {
		return spellSlots;
	}
	public void setSpellSlots(LinkedHashMap<Integer, ArrayList<Integer>> spellSlots) {
		this.spellSlots = spellSlots;
	}
	public LinkedHashMap<Integer, ArrayList<Integer>> getKnownSpellSlots() {
		return KnownSpellSlots;
	}
	public void setKnownSpellSlots(
			LinkedHashMap<Integer, ArrayList<Integer>> knownSpellSlots) {
		KnownSpellSlots = knownSpellSlots;
	}

	
}
