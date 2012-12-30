package com.plummersmind.bayesexample.views.editor
{
	import com.plummersmind.bayesexample.data.BayesNet;
	import com.plummersmind.bayesexample.data.BayesNode;
	import com.plummersmind.bayesexample.views.BayesNodeView;
	import com.plummersmind.bayesexample.views.InitializeViewControllersEvent;
	
	import flash.events.MouseEvent;
	
	import mx.collections.ListCollectionView;
	import mx.core.DragSource;
	import mx.events.CollectionEvent;
	import mx.events.CollectionEventKind;
	import mx.events.DragEvent;
	import mx.managers.DragManager;
	
	import spark.components.BorderContainer;

	
	/**
	 * Controls handles the drag and drop behavior for the node views in the editor.  i.e.
	 * when you click a node, you can drag it around and place it wherever you want.
	 **/
	[Bindable]
	[Name("bayesEditorMoveNodesCtrl")]
	public class BayesEditorMoveNodesCtrl
	{
		[Inject]
		public var editorView:BayesEditorView;
		
		[In]
		public var visibleBayesNodeViews:ListCollectionView;
		
		
		public function BayesEditorMoveNodesCtrl()
		{
			
		}
		
		[Observer]
		public function appInitialize(event:InitializeViewControllersEvent):void
		{
			visibleBayesNodeViews.addEventListener(CollectionEvent.COLLECTION_CHANGE, onVisibleNodeViewChanged, false, 0, true);
			editorView.addEventListener(DragEvent.DRAG_ENTER, doDragEnter, false, 0, true);
			editorView.addEventListener(DragEvent.DRAG_DROP, doDragDrop, false, 0, true);	
		}
		
		protected function onVisibleNodeViewChanged(event:CollectionEvent):void
		{
			switch(event.kind)
			{
				case CollectionEventKind.ADD:
					BayesNodeView(event.items[0]).titleDisplay.addEventListener(MouseEvent.MOUSE_DOWN, onNodeViewMouseDown, false, 0, true);
					break;
				default:
					throw new Error(event.kind + " not implemented yet");
			}
			
		}		
		
		// Variables used to hold the mouse pointer's location in the title bar.
		// Since the mouse pointer can be anywhere in the title bar, you have to 
		// compensate for it when you drop the panel. 
		public var xOff:Number;
		public var yOff:Number;
		
		// Drag initiator event handler for
		// the title bar's mouseMove event.
		private function onNodeViewMouseDown(event:MouseEvent):void
		{
			var dragInitiator:BayesNodeView=BayesNodeView(event.currentTarget.parentDocument.hostComponent);
			var ds:DragSource = new DragSource();
			ds.addData(event.currentTarget.parent, 'panel'); 
			
			// Update the xOff and yOff variables to show the
			// current mouse location in the Panel.  
			xOff = event.currentTarget.mouseX;
			yOff = event.currentTarget.mouseY;
			
			// Initiate d&d. 
			DragManager.doDrag(dragInitiator, ds, event); 
		}
		
		// Function called by the canvas dragEnter event; enables dropping
		private function doDragEnter(event:DragEvent):void 
		{
			DragManager.acceptDragDrop(BorderContainer(event.target));
		}
		
		// Function called by the Canvas dragDrop event; 
		// Sets the panel's position, 
		// "dropping" it in its new location.
		private function doDragDrop(event:DragEvent):void 
		{
			// Compensate for the mouse pointer's location in the title bar.
			var tempX:int = event.currentTarget.mouseX - xOff;
			event.dragInitiator.x = tempX;
			
			var tempY:int = event.currentTarget.mouseY - yOff;
			event.dragInitiator.y = tempY;
			
			// Put the dragged panel on top of all other components.
			editorView.setElementIndex(BayesNodeView(event.dragInitiator), editorView.numChildren-1);		
		}
	}
}