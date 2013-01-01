package net.jeffplummer.bayesexample
{
	import com.plummersmind.bayesexample.data.BayesNet;
	import com.plummersmind.bayesexample.data.BayesNode;
	import com.plummersmind.bayesexample.data.CPTableEntry;
	import com.plummersmind.bayesexample.data.NodeEquation;
	
	import mx.collections.ArrayCollection;

	public class TestDataGenerator
	{
		public function TestDataGenerator()
		{
		}
		
		public static function createTestNetwork():BayesNet
		{
			var net1:BayesNet = new BayesNet();
			net1.name = "ChestClinic";

			var visitAsia:BayesNode = new BayesNode("VisitAsia", "Visit to Asia", "visit", "no_visit");
			visitAsia.displayXLoc = 0;
			visitAsia.displayYLoc = 0;
			net1.nodes.addItem(visitAsia);
			
			var tuberculosis:BayesNode = new  BayesNode("Tuberculosis", "Has Tuberculosis", "present", "absent");
			tuberculosis.displayXLoc = 0;
			tuberculosis.displayYLoc = 150;
			net1.nodes.addItem(tuberculosis);
			
			var  smoking:BayesNode = new  BayesNode("Smoking", "Is a Smoker", "smoker", "nonsmoker");
			smoking.displayXLoc = 600;
			smoking.displayYLoc = 0;
			net1.nodes.addItem(smoking);

			var cancer:BayesNode = new  BayesNode("Cancer", "Lung Cancer", "present", "absent");
			cancer.displayXLoc = 400;
			cancer.displayYLoc = 150;
			net1.nodes.addItem(cancer);

			var tbOrCa:BayesNode = new  BayesNode("TbOrCa", "Tuberculosis or Cancer", "true", "false");
			tbOrCa.displayXLoc = 200;
			tbOrCa.displayYLoc = 300;
			net1.nodes.addItem(tbOrCa);

			var xRay:BayesNode = new  BayesNode("XRay", "XRay Result", "abnormal", "normal");
			xRay.displayXLoc = 0;
			xRay.displayYLoc = 450;
			net1.nodes.addItem(xRay);

			tuberculosis.parentNodes.addItem(visitAsia);       // puts link from visitAsia to tuberculosis
			cancer.parentNodes.addItem(smoking);
			tbOrCa.parentNodes.addItem(tuberculosis);
			tbOrCa.parentNodes.addItem(cancer);
			xRay.parentNodes.addItem(tbOrCa);
			
			
			//Parentless nodes
			visitAsia.cpTableEntries.addItem( new CPTableEntry(null, 0.01, 0.99));
			ArrayCollection(visitAsia.beliefs).source = [0.01, 0.99];
			
			smoking.cpTableEntries.addItem(new CPTableEntry(null, 0.5,  0.5));
			ArrayCollection(smoking.beliefs).source = [0.5, 0.5];
			
			// VisitAsia present absent
			tuberculosis.cpTableEntries.addItem(new CPTableEntry("visit", 0.05, 0.95));
			tuberculosis.cpTableEntries.addItem(new CPTableEntry ("no_visit", 0.01, 0.99));
			ArrayCollection(tuberculosis.beliefs).source = [0.07, 0.93];
			
			// Smoking present absent
			cancer.cpTableEntries.addItem(new CPTableEntry("smoker", 0.1, 0.9));
			cancer.cpTableEntries.addItem(new CPTableEntry("nonsmoker", 0.01, 0.99));
			ArrayCollection(cancer.beliefs).source = [0.05, 0.95];
			
			// TbOrCa abnormal normal
			xRay.cpTableEntries.addItem(new CPTableEntry ("true", 0.98, 0.02));
			xRay.cpTableEntries.addItem(new CPTableEntry ("false", 0.05, 0.95));
			ArrayCollection(xRay.beliefs).source = [0.11, 0.89];
			
			tbOrCa.equation = new NodeEquation("TbOrCa (Tuberculosis, Cancer) = Tuberculosis || Cancer",
				1, false, false);
			ArrayCollection(tbOrCa.beliefs).source = [0.06, 0.93];
			
			return net1;
		}
	}
}