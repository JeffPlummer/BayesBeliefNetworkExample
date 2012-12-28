package com.plummersmind.bayesexample.data;

public class NodeEquation 
{
	private String equation;
	private int equationToTable_NumSamples;
	private boolean equationToTable_IncludeSamplingUncertainty;
	private boolean equationToTable_addExisting;
	
	public String getEquation() {
		return equation;
	}
	public void setEquation(String equation) {
		this.equation = equation;
	}
	public int getEquationToTable_NumSamples() {
		return equationToTable_NumSamples;
	}
	public void setEquationToTable_NumSamples(int equationToTable_NumSamples) {
		this.equationToTable_NumSamples = equationToTable_NumSamples;
	}
	public boolean isEquationToTable_IncludeSamplingUncertainty() {
		return equationToTable_IncludeSamplingUncertainty;
	}
	public void setEquationToTable_IncludeSamplingUncertainty(
			boolean equationToTable_IncludeSamplingUncertainty) {
		this.equationToTable_IncludeSamplingUncertainty = equationToTable_IncludeSamplingUncertainty;
	}
	public boolean isEquationToTable_addExisting() {
		return equationToTable_addExisting;
	}
	public void setEquationToTable_addExisting(boolean equationToTable_addExisting) {
		this.equationToTable_addExisting = equationToTable_addExisting;
	}

}
