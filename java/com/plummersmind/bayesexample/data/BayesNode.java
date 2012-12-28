package com.plummersmind.bayesexample.data;

import java.util.ArrayList;

public class BayesNode 
{
	private int displayXLoc;
	private int displayYLoc;
	
	private String name;
	private String title;
	private ArrayList<String> states;
	private ArrayList<BayesNode> parentNodes;
	private ArrayList<CPTableEntry> cpTableEntries;
	private NodeEquation equation;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getStates() {
		return states;
	}
	public void setStates(ArrayList<String> states) {
		this.states = states;
	}
	public ArrayList<BayesNode> getParentNodes() {
		return parentNodes;
	}
	public void setParentNodes(ArrayList<BayesNode> parentNodes) {
		this.parentNodes = parentNodes;
	}
	public ArrayList<CPTableEntry> getCpTableEntries() {
		return cpTableEntries;
	}
	public void setCpTableEntries(ArrayList<CPTableEntry> cpTableEntries) {
		this.cpTableEntries = cpTableEntries;
	}
	public NodeEquation getEquation() {
		return equation;
	}
	public void setEquation(NodeEquation equation) {
		this.equation = equation;
	}
	public int getDisplayXLoc() {
		return displayXLoc;
	}
	public void setDisplayXLoc(int displayXLoc) {
		this.displayXLoc = displayXLoc;
	}
	public int getDisplayYLoc() {
		return displayYLoc;
	}
	public void setDisplayYLoc(int displayYLoc) {
		this.displayYLoc = displayYLoc;
	}
	
}
