package com.plummersmind.bayesexample.services;

import java.io.InputStream;

import javax.servlet.ServletContext;

import norsys.netica.Environ;
import norsys.netica.Node;
import norsys.netica.Streamer;
import norsys.neticaEx.aliases.Net;

import org.granite.messaging.service.annotations.RemoteDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plummersmind.bayesexample.data.BayesNet;
import com.plummersmind.bayesexample.data.BayesNode;

@Service("bayesNetworkService")
@RemoteDestination(id="bayesNetworkService", source="bayesNetworkService")
public class BayesNetworkService implements IBayesNetworkService 
{
	@Autowired
	protected ServletContext  context;

	private Environ env;
	
	public BayesNet getTestBayesNetwork()
	{
		try 
		{
			Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
			env = new Environ (null);
			
			Net testNet = createTestNet();
			if(testNet != null)
			{
				testNet.compile();
				BayesNet dtoNet = new BayesNet();
				NeticaToDTOTranslatorUtil.updateDTOsFromNeticaNet(testNet, dtoNet);
				setWindowXYPositionsForTestNetwork(dtoNet);
				env.finalize();
				return dtoNet;
			}
			env.finalize();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 

		
		return null;
	}

	private Net createTestNet()
	{
		try 
		{
			InputStream is2 = context.getResourceAsStream("/ChestClinic2.dne");
			Net  net = new Net(new Streamer(is2, "bcStream", env));
			return net;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/** Holy cow is this a hack.  But I got other projects to work on **/
	private void setWindowXYPositionsForTestNetwork(BayesNet bnet)
	{
		for (BayesNode bn : bnet.getNodes() )
		{
			String name = bn.getName();
			
			if(name.equals("VisitAsia"))
				bn.setDisplayXYLoc(0, 0);
			else if (name.equals("Tuberculosis"))
				bn.setDisplayXYLoc(0, 150);
			else if (name.equals("Smoking"))
				bn.setDisplayXYLoc(400, 0);
			else if (name.equals("Cancer"))
				bn.setDisplayXYLoc(400, 150);
			else if (name.equals("TbOrCa"))
				bn.setDisplayXYLoc(200, 300);
			else if (name.equals("XRay"))
				bn.setDisplayXYLoc(0, 450);
			else if (name.equals("Bronchitis"))
				bn.setDisplayXYLoc(800, 150);
		}
	}
}
