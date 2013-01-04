package com.plummersmind.bayesexample.services;

import java.util.Arrays;

import norsys.netica.Net;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.NodeList;
import norsys.netica.State;

import com.plummersmind.bayesexample.data.BayesNet;
import com.plummersmind.bayesexample.data.BayesNode;
import com.plummersmind.bayesexample.data.CPTableEntry;
import com.plummersmind.bayesexample.data.NodeEquation;

public class NeticaToDTOTranslatorUtil 
{

	public static void updateDTOsFromNeticaNet(Net net, BayesNet dtoNet) throws NeticaException
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

			System.out.println("Updating Node: " + bn.getName());

			//Set title
			updateDTONodeTitleFromNeticaNodeTitle(n, bn);

			//Set states
			updateDTONodeStatesFromNeticaNodeStates(n, bn);

			//Set Equation
			String eq = n.getEquation();
			if( (eq != null) && (!eq.equals("")) )
			{
				NodeEquation ne = new NodeEquation();
				ne.setEquation(eq);
				n.equationToTable (1, false, false);
			} 
			
			//Set CPTable Entries
			updateDTOCPEntriesFromNeticaNodeCPEntries(n, bn);
			
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


private static void updateDTONodeTitleFromNeticaNodeTitle(Node n, BayesNode bn) throws NeticaException
{
	if((n.getTitle() != null) && (!n.getTitle().equals("")) )
		bn.setTitle(n.getTitle());
	else
		bn.setTitle(n.getName());
}

private static void updateDTONodeStatesFromNeticaNodeStates(Node n, BayesNode bn) throws NeticaException
{
	bn.getStates().clear();
	for (int j=0; j<n.getNumStates(); j++)
	{
		State s = n.state(j);
		bn.getStates().add(j, s.getName());
		System.out.println("    State: " + s.getName());
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
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("    Getting CPTable for all: " + Arrays.toString(n.getCPTable(null)));
		for(int k=0; k<n.getParents().size(); k++)
		{
			Node parent = n.getParents().getNode(k);
			for (int ps=0; ps<parent.getNumStates(); ps++)
			{
				String s = parent.state(ps).getName();
				
				float inputProbs[] = n.getCPTable(s, null);
				System.out.println("    CPTable for parent state: " + s + 
						" = " + Arrays.toString(inputProbs));
				
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
