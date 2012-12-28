package com.plummersmind.bayesexample.services;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import edu.cmu.javabayes.InferenceGraphs.InferenceGraph;

public class BayesNetworkService implements IBayesNetworkService 
{

	public String test()
	{
			try 
			{
				System.out.println("Working Directory = " +
			              System.getProperty("user.dir"));
				
				InferenceGraph ig = new InferenceGraph("dog-problem.bif");
				
				ByteArrayOutputStream bstream = new ByteArrayOutputStream();
			    PrintStream pstream = new PrintStream(bstream);
			    
			    //ig.print_bayes_net(pstream);
			    
			    ig.print_full_explanation(pstream, true);
			    
			    System.out.println(bstream.toString());
			    
			    bstream.close();
		        pstream.close();
			    
			    
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			} 
		
		
	    return null;
	}
}
