/**
 * Generated by Gas3 v2.3.2 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (BayesNet.as).
 */

package com.plummersmind.bayesexample.data {

    import com.plummersmind.bayesexample.entities.AbstractEntity;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import mx.collections.ListCollectionView;
    import org.granite.tide.IPropertyHolder;

    [Bindable]
    public class BayesNetBase extends AbstractEntity {

        private var _name:String;
        private var _nodes:ListCollectionView;

        public function set name(value:String):void {
            _name = value;
        }
        public function get name():String {
            return _name;
        }

        public function set nodes(value:ListCollectionView):void {
            _nodes = value;
        }
        public function get nodes():ListCollectionView {
            return _nodes;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _name = input.readObject() as String;
            _nodes = input.readObject() as ListCollectionView;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject((_name is IPropertyHolder) ? IPropertyHolder(_name).object : _name);
            output.writeObject((_nodes is IPropertyHolder) ? IPropertyHolder(_nodes).object : _nodes);
        }
    }
}