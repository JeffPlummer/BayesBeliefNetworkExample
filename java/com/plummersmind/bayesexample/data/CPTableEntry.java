package com.plummersmind.bayesexample.data;

import java.util.ArrayList;

import com.plummersmind.bayesexample.entities.AbstractEntity;



public class CPTableEntry
{
	private String parentState;
	private ArrayList<Float> probabilities;
	
	public CPTableEntry()
	{
		probabilities = new ArrayList<Float>();
	}
	
	public CPTableEntry(String pState, float probs[])
	{
		parentState = pState;
		probabilities = new ArrayList<Float>();
		for(int i=0; i<probs.length; i++)
		{
			probabilities.add(Float.valueOf( (Float)probs[i]));
		}
	}
	
	public String getParentState() {
		return parentState;
	}
	public void setParentState(String parentState) {
		this.parentState = parentState;
	}
	public ArrayList<Float> getProbabilities() {
		return probabilities;
	}
	public void setProbabilities(ArrayList<Float> probabilities) {
		this.probabilities = probabilities;
	}

}
