/**
 * Generated by Gas3 v2.3.2 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package com.plummersmind.bayesexample.data {
	import mx.collections.ArrayCollection;

    [Bindable]
    [RemoteClass(alias="com.plummersmind.bayesexample.data.CPTableEntry")]
    public class CPTableEntry extends CPTableEntryBase 
	{
		public function CPTableEntry(parentState:String=null, ... probs)
		{
			this.parentState = parentState;
			this.probabilities = new ArrayCollection();
			
			for (var i:uint; i<probs.length; i++)
			{
				this.probabilities.addItem(probs[i]);
			}
		}
    }
}