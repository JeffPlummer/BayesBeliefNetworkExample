package com.plummersmind.bayesexample.data;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import com.plummersmind.bayesexample.entities.AbstractEntity;

public class BayesNode extends AbstractEntity
{
	private int displayXLoc;
	private int displayYLoc;
	
	private String name;
	private String title;
	private ArrayList<String> states;
	private ArrayList<BayesNode> parentNodes;
	private ArrayList<CPTableEntry> cpTableEntries;
	private NodeEquation equation;
	private ArrayList<Float> beliefs;
	
	public BayesNode()
	{
		states = new ArrayList<String>();
		parentNodes = new ArrayList<BayesNode>();
		cpTableEntries = new ArrayList<CPTableEntry>();
		beliefs = new ArrayList<Float>();
	}
	
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
	public ArrayList<Float> getBeliefs() {
		return beliefs;
	}
	public void setBeliefs(ArrayList<Float> beliefs) {
		this.beliefs = beliefs;
	}
	
	public float[] getAllCPTables()
	{
		ArrayList<Float> retList = new ArrayList<Float>();
		for(int i=0; i<this.cpTableEntries.size(); i++)
		{
			//System.out.println("Aggregating CPEntries for state: " + cpTableEntries.get(i).getParentState() );
			retList.addAll(cpTableEntries.get(i).getProbabilities());
		}
		
		float[] floatArray = ArrayUtils.toPrimitive(retList.toArray(new Float[0]), 0.0F);
		return floatArray;
	}
	
}
