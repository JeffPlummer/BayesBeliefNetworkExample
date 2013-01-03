/**
 * Generated by Gas3 v2.3.2 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (BayesNode.as).
 */

package com.plummersmind.bayesexample.data {

    import com.plummersmind.bayesexample.entities.AbstractEntity;
    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import mx.collections.ListCollectionView;
    import org.granite.tide.IPropertyHolder;

    [Bindable]
    public class BayesNodeBase extends AbstractEntity {

        private var _beliefs:ListCollectionView;
        private var _cpTableEntries:ListCollectionView;
        private var _displayXLoc:int;
        private var _displayYLoc:int;
        private var _equation:NodeEquation;
        private var _name:String;
        private var _parentNodes:ListCollectionView;
        private var _states:ListCollectionView;
        private var _title:String;

        public function set beliefs(value:ListCollectionView):void {
            _beliefs = value;
        }
        public function get beliefs():ListCollectionView {
            return _beliefs;
        }

        public function set cpTableEntries(value:ListCollectionView):void {
            _cpTableEntries = value;
        }
        public function get cpTableEntries():ListCollectionView {
            return _cpTableEntries;
        }

        public function set displayXLoc(value:int):void {
            _displayXLoc = value;
        }
        public function get displayXLoc():int {
            return _displayXLoc;
        }

        public function set displayYLoc(value:int):void {
            _displayYLoc = value;
        }
        public function get displayYLoc():int {
            return _displayYLoc;
        }

        public function set equation(value:NodeEquation):void {
            _equation = value;
        }
        public function get equation():NodeEquation {
            return _equation;
        }

        public function set name(value:String):void {
            _name = value;
        }
        public function get name():String {
            return _name;
        }

        public function set parentNodes(value:ListCollectionView):void {
            _parentNodes = value;
        }
        public function get parentNodes():ListCollectionView {
            return _parentNodes;
        }

        public function set states(value:ListCollectionView):void {
            _states = value;
        }
        public function get states():ListCollectionView {
            return _states;
        }

        public function set title(value:String):void {
            _title = value;
        }
        public function get title():String {
            return _title;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _beliefs = input.readObject() as ListCollectionView;
            _cpTableEntries = input.readObject() as ListCollectionView;
            _displayXLoc = input.readObject() as int;
            _displayYLoc = input.readObject() as int;
            _equation = input.readObject() as NodeEquation;
            _name = input.readObject() as String;
            _parentNodes = input.readObject() as ListCollectionView;
            _states = input.readObject() as ListCollectionView;
            _title = input.readObject() as String;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject((_beliefs is IPropertyHolder) ? IPropertyHolder(_beliefs).object : _beliefs);
            output.writeObject((_cpTableEntries is IPropertyHolder) ? IPropertyHolder(_cpTableEntries).object : _cpTableEntries);
            output.writeObject((_displayXLoc is IPropertyHolder) ? IPropertyHolder(_displayXLoc).object : _displayXLoc);
            output.writeObject((_displayYLoc is IPropertyHolder) ? IPropertyHolder(_displayYLoc).object : _displayYLoc);
            output.writeObject((_equation is IPropertyHolder) ? IPropertyHolder(_equation).object : _equation);
            output.writeObject((_name is IPropertyHolder) ? IPropertyHolder(_name).object : _name);
            output.writeObject((_parentNodes is IPropertyHolder) ? IPropertyHolder(_parentNodes).object : _parentNodes);
            output.writeObject((_states is IPropertyHolder) ? IPropertyHolder(_states).object : _states);
            output.writeObject((_title is IPropertyHolder) ? IPropertyHolder(_title).object : _title);
        }
    }
}