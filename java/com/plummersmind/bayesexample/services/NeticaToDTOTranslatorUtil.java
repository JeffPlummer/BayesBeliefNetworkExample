package com.plummersmind.bayesexample.services;

import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.NodeList;
import norsys.netica.State;

import com.plummersmind.bayesexample.data.BayesNet;
import com.plummersmind.bayesexample.data.BayesNode;
import com.plummersmind.bayesexample.data.CPTableEntry;

public class NeticaToDTOTranslatorUtil 
{

	public static void updateDTOsFromNeticaNet(Net net, BayesNet dtoNet)
	{
		try 
		{
			dtoNet.setName(net.getName());
			
			NodeList nl = net.getNodes();
			
			for (int i=0; i<nl.size(); i++)
			{
				Node n = nl.getNode(i);
				
				//Get corresponding Node DTO.  Create new if necessary
				BayesNode bn = dtoNet.getChildNodeByName(n.getName());
				if(bn == null)
				{
					bn = new BayesNode();
					bn.setName(n.getName());
					dtoNet.getNodes().add(bn);
				}
				
				//Set title
				updateDTONodeTitleFromNeticaNodeTitle(n, bn);
				
				//Set states
				updateDTONodeStatesFromNeticaNodeStates(n, bn);
				
				//Set CPTable Entries
				updateDTOCPEntriesFromNeticaNodeCPEntries(n, bn);
				
				//TODO:  Set Equation - Not Yet Implemented
				
				//Set beliefs
				updateDTOBeliefsFromNeticaNodeBeliefs(n, bn);
			}
			
			//Second Pass to create node relationships
			for (int j=0; j<nl.size(); j++)
			{
				Node n = nl.getNode(j);
				BayesNode bn = dtoNet.getChildNodeByName(n.getName());
				updateDTORelationsFromNeticaNodeRelations(n, bn, dtoNet);	
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	private static void updateDTONodeTitleFromNeticaNodeTitle(Node n, BayesNode bn) throws NeticaException
	{
		bn.setTitle(n.getTitle());
	}
	
	private static void updateDTONodeStatesFromNeticaNodeStates(Node n, BayesNode bn) throws NeticaException
	{
		bn.getStates().clear();
		for (int j=0; j<n.getNumStates(); j++)
		{
			State s = n.state(j);
			bn.getStates().add(j, s.getName());
		}
	}
	
	private static void updateDTOCPEntriesFromNeticaNodeCPEntries(Node n, BayesNode bn) throws NeticaException
	{
		bn.getCpTableEntries().clear();
		
		if(n.getParents().size() == 0)
		{
			float standAloneCPs[] = n.getCPTable(null);
			CPTableEntry cpt = new CPTableEntry(null, standAloneCPs);
			bn.getCpTableEntries().add(cpt);
		}
		else
		{
			for(int k=0; k<n.getParents().size(); k++)
			{
				Node parent = n.getParents().getNode(k);
				for (int ps=0; ps<parent.getNumStates(); ps++)
				{
					String s = parent.state(ps).getName();
					float inputProbs[] = n.getCPTable(s, null);
					
					CPTableEntry cpt = new CPTableEntry(s, inputProbs);
					bn.getCpTableEntries().add(cpt);
				}
			}
		}
	}
	
	private static void updateDTOBeliefsFromNeticaNodeBeliefs(Node n, BayesNode bn) throws NeticaException
	{
		float beliefs[] = n.getBeliefs();
		bn.getBeliefs().clear();
		for(float belief : beliefs)
		{
			bn.getBeliefs().add(belief);
		}
	}
	
	private static void updateDTORelationsFromNeticaNodeRelations(Node n, BayesNode bn, BayesNet dtoNet) throws NeticaException
	{
		bn.getParentNodes().clear();
		
		for(int k=0; k<n.getParents().size(); k++)
		{
			Node p = n.getParents().getNode(k);
			BayesNode pbn = dtoNet.getChildNodeByName(p.getName());
			
			bn.getParentNodes().add(pbn);
		}
	}
}
