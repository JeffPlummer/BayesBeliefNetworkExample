package com.plummersmind.bayesexample.views.editor
{
	import com.plummersmind.bayesexample.data.BayesNet;
	import com.plummersmind.bayesexample.data.BayesNode;
	import com.plummersmind.bayesexample.views.BayesNodeLinkView;
	import com.plummersmind.bayesexample.views.BayesNodeView;
	import com.plummersmind.bayesexample.views.InitializeViewControllersEvent;
	
	/**
	 * Controller adds/removes node/link views based on the data.
	 **/
	[Bindable]
	[Name("bayesEditorDataToViewCtrl")]
	public class BayesEditorDataToViewCtrl
	{
		private var _currentBayesNet:BayesNet;
		
		[Inject]
		public var editorView:BayesEditorView;
		
		public function BayesEditorDataToViewCtrl()
		{
		}
		
		[Inject]
		public function set bayesNetworkToView(net:BayesNet):void
		{
			_currentBayesNet = net;
			if( (_currentBayesNet != null) && (editorView != null) )
			{
				rebuildBayesNetDisplay();				
			}
		}
		
		public function get bayesNetworkToView():BayesNet
		{
			return _currentBayesNet;	
		}
		
		private function rebuildBayesNetDisplay():void
		{
			editorView.resetEditorView();
			rebuildAllViewNodes();
			rebuildAllViewLinks();
		}
		
		private function rebuildAllViewNodes():void
		{
			if(bayesNetworkToView != null)
			{
				for each (var bn:BayesNode in bayesNetworkToView.nodes)
				{
					addNewBayesNodeViewForNode(bn);
				}
			}
		}
		
		private function addNewBayesNodeViewForNode(bn:BayesNode):void
		{
			var newNodeView:BayesNodeView = new BayesNodeView();
			newNodeView.bayesNodeToView = bn;
			editorView.addBayesNodeView(newNodeView);
		}
		
		private function rebuildAllViewLinks():void
		{
			for each (var bn:BayesNode in bayesNetworkToView.nodes)
			{
				addNewNodeLinkViewForNode(bn);
			}
		}
		
		private function addNewNodeLinkViewForNode(bn:BayesNode):void
		{
			var viewNode:BayesNodeView = bn.bayesNodeView;
			var newNodeLinkView:BayesNodeLinkView = new BayesNodeLinkView();
			
		}
		
		[Observer]
		public function appInitialize(event:InitializeViewControllersEvent):void
		{
			rebuildBayesNetDisplay();
		}
	}
}