package com.plummersmind.bayesexample.services;

import java.io.File;
import java.io.InputStream;

import norsys.netica.Environ;
import norsys.netica.Node;
import norsys.neticaEx.aliases.Net;
//import norsys.neticaEx.aliases.Node;
import norsys.netica.Streamer;

import org.granite.messaging.service.annotations.RemoteDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.*;

@Service("bayesNetworkService")
@RemoteDestination(id="bayesNetworkService", source="bayesNetworkService")
public class BayesNetworkService implements IBayesNetworkService 
{
	@Autowired
	ServletContext  context;

	private Environ env;
	
	public String test()
	{
		try 
		{
			Node.setConstructorClass ("norsys.neticaEx.aliases.Node");
			env = new Environ (null);
			
			Net testNet = createTestNet();
			if(testNet != null)
			{
				testNet.compile();
				
				Node visitAsia = testNet.getNode("VisitAsia");
				Node tuberculosis = testNet.getNode ("Tuberculosis");
				Node xRay = testNet.getNode ("XRay");
				
				double belief = tuberculosis.getBelief ("present");
				System.out.println ("\nThe probability of tuberculosis is " + belief);
				
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
		try {
			
			InputStream is2 = context.getResourceAsStream("/ChestClinic.dne");
			
			Net  net = new Net(new Streamer(is2, "chestStream", env));
			
//			env.finalize();
			return net;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

//	private void inferNet()
//	{
//		try {
//			Environ env = new Environ (null);
//			// Read in the net created by the BuildNet.java example program.
//			Net net = new Net (new Streamer ("Data Files/ChestClinicBuilt.dne"));
//			Node visitAsia = net.getNode ("VisitAsia");
//			Node tuberculosis = net.getNode ("Tuberculosis");
//			Node xRay = net.getNode ("XRay");
//			net.compile();
//			double belief = tuberculosis.getBelief ("present");
//			System.out.println ("\nThe probability of tuberculosis is " + belief);
//			xRay.finding().enterState ("abnormal");
//			belief = tuberculosis.getBelief ("present");
//			System.out.println ("\nGiven an abnormal X-ray,\n" +
//					"the probability of tuberculosis is " + belief);
//			visitAsia.finding().enterState ("visit");
//			belief = tuberculosis.getBelief ("present");
//			System.out.println ("\nGiven an abnormal X-ray and a visit to Asia,\n" +
//					"the probability of tuberculosis is " + belief + "\n");
//			net.finalize();
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//	}
}
