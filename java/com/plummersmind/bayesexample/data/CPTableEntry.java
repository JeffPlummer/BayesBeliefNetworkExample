package com.plummersmind.bayesexample.data;

import java.util.ArrayList;



public class CPTableEntry 
{
	private String parentState;
	private ArrayList<Double> probabilities;
	public String getParentState() {
		return parentState;
	}
	public void setParentState(String parentState) {
		this.parentState = parentState;
	}
	public ArrayList<Double> getProbabilities() {
		return probabilities;
	}
	public void setProbabilities(ArrayList<Double> probabilities) {
		this.probabilities = probabilities;
	}

}
