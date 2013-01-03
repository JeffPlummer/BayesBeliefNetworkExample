package com.plummersmind.bayesexample.services;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import norsys.netica.Environ;
import norsys.netica.NeticaException;
import norsys.netica.Node;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Net;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.plummersmind.bayesexample.data.BayesNet;
import com.plummersmind.bayesexample.data.BayesNode;
import com.plummersmind.bayesexample.data.CPTableEntry;

public class NeticaToDTOTranslatorUtilTest 
{
	private Environ env;
	private Net  net;

	@Before
	public void setUp() throws Exception 
	{
		try 
		{
			Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
			env = new Environ (null);

			InputStream is2 = new FileInputStream("BreastCancer.dne");
			net = new Net(new Streamer(is2, "chestStream", env));
			net.compile();
		}
		catch (Exception e) 
		{
			fail("setup fail");
			e.printStackTrace();
		} 
	}

	@After
	public void tearDown() throws Exception 
	{
		env.finalize();
	}

	@Test
	public void testUpdateDTOsFromNeticaNet() 
	{
		try 
		{
			BayesNet dtoNet = new BayesNet();
			NeticaToDTOTranslatorUtil.updateDTOsFromNeticaNet(net, dtoNet);
			assertEquals(dtoNet.getName(), net.getName());
			assertEquals(dtoNet.getNodes().size(), net.getNodes().size());
			
			for (int i=0; i<net.getNodes().size(); i++)
			{
				Node n = (Node) net.getNodes().get(i);
				BayesNode bn = dtoNet.getNodes().get(i);
				
				assertNeticaNodeEqualsDTONode(n, bn);
			}
		} 
		catch (NeticaException e) 
		{
			e.printStackTrace();
			fail("");
		}
	}
	
	private void assertNeticaNodeEqualsDTONode(Node n, BayesNode bn) throws NeticaException
	{
		//Test node names
		assertNeticaNodeNameEqualsDTONodeName(n, bn);
		
		//Test node states
		assertNeticaNodeStatesEqualsDTONodeStates(n, bn);
		
		//Test cpTableEntries
		assertNeticaNodeCPTablesEqualsDTONodeCPTables(n, bn);
		
		//Test beliefs
		assertNeticaNodeBeliefsEqualsDTONodeBeliefs(n, bn);
		
		//Test parentNodes
		assertNeticaNodeRelationsEqualsDTONodeRelations(n, bn);
	}
	
	private void assertNeticaNodeNameEqualsDTONodeName(Node n, BayesNode bn) throws NeticaException
	{
		assertEquals(bn.getName(), n.getName());
		System.out.println("Node:  " + bn.getName());
	}
	
	private void assertNeticaNodeStatesEqualsDTONodeStates(Node n, BayesNode bn) throws NeticaException
	{
		for (int j=0; j<n.getNumStates(); j++)
		{
			assertEquals(bn.getStates().get(j), n.state(j).getName());
			System.out.println("    State: " + bn.getStates().get(j));
		}
	}
	
	private void assertNeticaNodeCPTablesEqualsDTONodeCPTables(Node n, BayesNode bn) throws NeticaException
	{
		//Test states at the aggregate level
		float bnTables[] = bn.getAllCPTables();
		float nTables[] = n.getCPTable(null);
		assertEquals(bnTables.length, nTables.length);
		for (int i=0; i<nTables.length; i++)
		{
//			System.out.println("    CPTable:  Comparing " + bnTables[i] + 
//					" to " + nTables[i]);
			assertEquals( Float.compare(bnTables[i], nTables[i]), 0);
		}
		
		//Test parent state name and values line up
		for(CPTableEntry cpt : bn.getCpTableEntries() )
		{
			String pStateName = cpt.getParentState();
			float nStateTable[] = n.getCPTable(pStateName, null);
			float bnStateTable[] = ArrayUtils.toPrimitive(cpt.getProbabilities().toArray(new Float[0]), 0.0F);
			
			System.out.println("    CPTable:  Comparing pState: " + pStateName + 
					" (" + Arrays.toString(bnStateTable) + ", " + Arrays.toString(nStateTable) + ")");
			
			assertEquals(nStateTable.length, bnStateTable.length);
			
			for(int j=0; j<nStateTable.length; j++)
			{
				assertEquals(Float.compare(nStateTable[j], bnStateTable[j]), 0);
			}
		}
	}
	
	private void assertNeticaNodeBeliefsEqualsDTONodeBeliefs(Node n, BayesNode bn) throws NeticaException
	{
		float nBeliefs[] = n.getBeliefs();
		float bnBeliefs[] = ArrayUtils.toPrimitive(bn.getBeliefs().toArray(new Float[0]), 0.0F);
		
		assertEquals(nBeliefs.length, bnBeliefs.length);
		
		System.out.println("    Beliefs for " + bn.getStates() + ": " + Arrays.toString(nBeliefs) +
				" == " + Arrays.toString(bnBeliefs));
		
		for(int i=0; i<nBeliefs.length; i++)
		{
			assertEquals(Float.compare(nBeliefs[i], bnBeliefs[i]), 0);
		}
	}
	
	private void assertNeticaNodeRelationsEqualsDTONodeRelations(Node n, BayesNode bn) throws NeticaException
	{
		for (int i=0; i<n.getParents().size(); i++)
		{
			Node p = n.getParents().getNode(i);
			BayesNode pbn = bn.getParentNodes().get(i);
			
			System.out.println("    Node: " + n.getName() + " has parent " + p.getName());
			assertEquals(p.getName(), pbn.getName());
		}
	}

}
