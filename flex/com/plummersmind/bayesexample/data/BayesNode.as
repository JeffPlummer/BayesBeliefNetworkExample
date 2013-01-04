/**
 * Generated by Gas3 v2.3.2 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package com.plummersmind.bayesexample.data {
	import com.plummersmind.bayesexample.views.nodeview.BayesNodeView;
	
	import mx.collections.ArrayCollection;

    [Bindable]
    [RemoteClass(alias="com.plummersmind.bayesexample.data.BayesNode")]
    public class BayesNode extends BayesNodeBase 
	{
		private var _bayesNodeView:BayesNodeView;
		
		public function BayesNode(name:String = null, title:String=null, ... possibleStates)
		{
			this.name = name;
			this.title = title;
			
			states = new ArrayCollection();
			parentNodes = new ArrayCollection();
			cpTableEntries = new ArrayCollection();
			beliefs = new ArrayCollection();
			
			for(var i:uint=0; i<possibleStates.length; i++)
			{
				states.addItem(possibleStates[i]);
			}
		}

		public function get bayesNodeView():BayesNodeView
		{
			return _bayesNodeView;
		}

		public function set bayesNodeView(value:BayesNodeView):void
		{
			_bayesNodeView = value;
		}

    }
}