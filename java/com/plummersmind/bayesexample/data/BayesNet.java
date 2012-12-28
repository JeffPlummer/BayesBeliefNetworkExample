package com.plummersmind.bayesexample.data;

import java.util.List;

public class BayesNet 
{
	private String name;
	private List<BayesNode> nodes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<BayesNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<BayesNode> nodes) {
		this.nodes = nodes;
	}
	
	

}
