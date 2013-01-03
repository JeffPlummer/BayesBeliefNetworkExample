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
				return dtoNet;
			}
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
			InputStream is2 = context.getResourceAsStream("/BreastCancer.dne");
			Net  net = new Net(new Streamer(is2, "bcStream", env));
			return net;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
