package com.plummersmind.bayesexample.data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import com.plummersmind.bayesexample.entities.AbstractEntity;

//@Entity
public class BayesNet extends AbstractEntity
{
	private String name;
	private List<BayesNode> nodes;

	public BayesNet()
	{
		nodes = new ArrayList<BayesNode>();
	}
	
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
	
	
	public BayesNode getChildNodeByName(String name)
	{
		if (nodes != null) for (BayesNode bn : nodes)
		{
			if(bn.getName().equals(name))
				return bn;
		}
		return null;
	}
	
	

}
