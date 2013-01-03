package com.plummersmind.bayesexample.services;


import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileInputStream;

import javax.servlet.ServletContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.plummersmind.bayesexample.data.BayesNet;

public class BayesNetworkServiceTest
{

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testTest() 
	{
		try
		{
			BayesNetworkService bns = new BayesNetworkService();
			ServletContext mockContext = mock(ServletContext.class);
			when(mockContext.getResourceAsStream(anyString())).thenReturn(new FileInputStream("BreastCancer.dne"));
			bns.context = mockContext;
			
			BayesNet testBN = bns.getTestBayesNetwork();
			System.out.println("I am here");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			fail("Failed");
		}
		
	}

}
