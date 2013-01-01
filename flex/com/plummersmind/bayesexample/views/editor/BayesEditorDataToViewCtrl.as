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
			bn.bayesNodeView = newNodeView;
			editorView.addBayesNodeView(newNodeView);
		}
		
		private function rebuildAllViewLinks():void
		{
			for each (var bn:BayesNode in bayesNetworkToView.nodes)
			{
				addNewNodeLinkViewForAllChildLinks(bn);
			}
		}
		
		private function addNewNodeLinkViewForAllChildLinks(bn:BayesNode):void
		{
			var childViewNode:BayesNodeView = bn.bayesNodeView;
			for each (var parent:BayesNode in bn.parentNodes)
			{
				var parentViewNode:BayesNodeView = parent.bayesNodeView;
				var newNodeLinkView:BayesNodeLinkView = new BayesNodeLinkView();
				
				newNodeLinkView.parentNodeView = parentViewNode;
				parentViewNode.childLinkViews.addItem(newNodeLinkView);
				
				newNodeLinkView.childNodeView = childViewNode;
				childViewNode.parentLinkViews.addItem(newNodeLinkView);
				
				editorView.addBayesNodeLinkView(newNodeLinkView);
			}
		}
		
		[Observer]
		public function appInitialize(event:InitializeViewControllersEvent):void
		{
			rebuildBayesNetDisplay();
		}
	}
}