/**
 * @author Raymond Yang
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.text.*;
import java.lang.Math;

public class DecisionTree implements Serializable {

	DTNode rootDTNode;
	int minSizeDatalist; //minimum number of datapoints that should be present in the dataset so as to initiate a split
	//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.

	public static final long serialVersionUID = 343L;
	public DecisionTree(ArrayList<Datum> datalist , int min) {
		minSizeDatalist = min;
		rootDTNode = (new DTNode()).fillDTNode(datalist);
	}

	class DTNode implements Serializable{
		//Mention the serialVersionUID explicitly in order to avoid getting errors while deserializing.
		public static final long serialVersionUID = 438L;
		boolean leaf;
		int label = -1;      // only defined if node is a leaf
		int attribute; // only defined if node is not a leaf
		double threshold;  // only defined if node is not a leaf

		DTNode left, right; //the left and right child of a particular node. (null if leaf)

		DTNode() {
			leaf = true;
			threshold = Double.MAX_VALUE;
		}



		// this method takes in a datalist (ArrayList of type datum) and a minSizeInClassification (int) and returns
		// the calling DTNode object as the root of a decision tree trained using the datapoints present in the
		// datalist variable
		// Also, KEEP IN MIND that the left and right child of the node correspond to "less than" and "greater than or equal to" threshold
		DTNode fillDTNode(ArrayList<Datum> datalist) {

			//YOUR CODE HERE
			
			// check if dataset has at least minSizeDatalist items
			if(datalist.size() >= minSizeDatalist) {
				// check if all items have same label
				boolean sameLabel = true;
				int prevLabel = datalist.get(0).y;		// set reference label for loop				
				for(Datum curData: datalist) {
					if(prevLabel != curData.y) {
						sameLabel = false;
						break;
					}
				}
				
				if(sameLabel == true) {
					// all items have same label
					// create leaf node with the label and return it
					DTNode node = new DTNode();
					node.label = prevLabel;
					return node;
				} else {
					// items in datalist have different labels
					
					// find a best split by testing out different splits and choosing the one with lowest entropy
					double bestAvgS = Double.MAX_VALUE;		// best average entropy
					int bestAttribute = -1;					// best attribute
					double bestThreshold = -1;					// best threshold	
					
					// for each attribute in datalist
					for(int i = 0; i < datalist.get(0).x.length; i++) {
						// for each Datum in datalist
						for(int j = 0; j < datalist.size(); j++) {
							// split and compute avg entropy and find best avg entropy
							
							// initialize empty arraylists for split
							ArrayList<Datum> list1 = new ArrayList<Datum>();
							ArrayList<Datum> list2 = new ArrayList<Datum>();
							
							// perform split
							for(int k = 0; k < datalist.size(); k++) {
								// split by comparing values of attributes
								if(datalist.get(k).x[i] < datalist.get(j).x[i]) {
									// if the ith attribute of the kth datum is smaller than the ith attribute of the jth datum
									// put in list 1
									list1.add(datalist.get(k));
								} else {
									// put in list 2
									list2.add(datalist.get(k));
								}
							}
								
							// compute entropy for both splitted lists
							double curAvgS = ((double)list1.size())/(list1.size()+list2.size())*calcEntropy(list1) + ((double)list2.size())/(list1.size()+list2.size())*calcEntropy(list2);

							// check if curAvgS is smaller than bestAvgS
							// adjustment for error
							if(curAvgS < bestAvgS) {
								// update best record
								bestAvgS = curAvgS;
								bestAttribute = i;
								bestThreshold = datalist.get(j).x[i];
							}
						}
					}
					
					// create a new node and return as root node
					DTNode node = new DTNode();
					
					node.attribute = bestAttribute;
					node.threshold = bestThreshold;
					node.leaf = false;
				
					// create the child lists
					ArrayList<Datum> bestList1 = new ArrayList<Datum>();
					ArrayList<Datum> bestList2 = new ArrayList<Datum>();
					
					// perform split with best attributes and threshold
					// we know what attribute to use and what threshold of that attribute
					for(int i = 0; i < datalist.size(); i++) {
						if(datalist.get(i).x[bestAttribute] < bestThreshold) {
							bestList1.add(datalist.get(i));
						} else {
							bestList2.add(datalist.get(i));
						}
					}
					
					// recursively call make decision tree on two splitted child datalists
					node.left = fillDTNode(bestList1);
					node.right = fillDTNode(bestList2);
					
					return node;
				}
			} else {
				// if datalist has size smaller than minSize then do not construct decision tree
				// find majority node
				DTNode tmp = new DTNode();
				tmp.label = this.findMajority(datalist);
				return tmp;
			}
		}



		//This is a helper method. Given a datalist, this method returns the label that has the most
		// occurences. In case of a tie it returns the label with the smallest value (numerically) involved in the tie.
		int findMajority(ArrayList<Datum> datalist)
		{
			int l = datalist.get(0).x.length;
			int [] votes = new int[l];

			//loop through the data and count the occurrences of datapoints of each label
			for (Datum data : datalist)
			{
				votes[data.y]+=1;
			}
			int max = -1;
			int max_index = -1;
			//find the label with the max occurrences
			for (int i = 0 ; i < l ;i++)
			{
				if (max<votes[i])
				{
					max = votes[i];
					max_index = i;
				}
			}
			return max_index;
		}




		// This method takes in a datapoint (excluding the label) in the form of an array of type double (Datum.x) and
		// returns its corresponding label, as determined by the decision tree
		int classifyAtNode(double[] xQuery) {
			//YOUR CODE HERE
			
			// base case
			if(this.leaf == true) {
				return this.label;
			}
			
			// input is datapoint with no label 
			// classify with attribute and threshold
			if(xQuery[this.attribute] < this.threshold) {
				return this.left.classifyAtNode(xQuery);
			} else {
				return this.right.classifyAtNode(xQuery);
			}
		}


		//given another DTNode object, this method checks if the tree rooted at the calling DTNode is equal to the tree rooted
		//at DTNode object passed as the parameter
		public boolean equals(Object dt2)
		{

			//YOUR CODE HERE
			
			// check if object passed is of class DTNode
			if(!(dt2 instanceof DTNode)) {
				return false;
			}
			
			DTNode dtree = (DTNode)dt2;		// dt2 is DTNode class, assign to variable dtree
			boolean tmp;

			// preorder traversal: root -> left -> right
			// approach: whenever we encounter a false, the decision trees are different, exit all if-else statements and return false
			// we first evaluate root node
			if(this.leaf == true && dtree.leaf == true) {
				// if both nodes are leaf nodes
				// evaluate label
				if(this.label == dtree.label) {
					// both leaf nodes have same labels
					// proceed to check child nodes
				}
			} else if (this.leaf == false && dtree.leaf == false) {
				// if both nodes are not leaf
				// evaluate attribute and threshold
				if(this.attribute == dtree.attribute && this.threshold == dtree.threshold) {
					// both attribute and threshold are same for both nodes
					// proceed to check child nodes
				} else {
					// either attribute or threshold is inconsistent between nodes
					return false;
				}
			} else {
				// the two nodes have inconsistent types
				return false;
			}
			
			// check if left child node is present
			if(this.left == null && dtree.left == null) {
				// if both nodes don't have left child nodes
				// proceed to check for right child nodes
			} else if(this.left != null && dtree.left != null) {
				// if both have left child nodes, evaluate
				tmp = (this.left).equals(dtree.left);	
				if(tmp) {
					// if the left child nodes are equal check right child nodes
				} else {
					// if left child nodes not equal return false
					return false;
				}
			} else {
				// one has a left child node and the other doesn't
				return false;
			}
			
			// check if right child node is present
			if(this.right == null && dtree.right == null) {
				// if both nodes don't have right child nodes
				// go back one level up
				return true;
			} else if(this.right != null && dtree.right != null) {
				// if both have right child nodes, evaluate
				return (this.right).equals(dtree.right);	
			} else {
				// one has a right child node and the other doesn't
				return false;
			}
		// end of equals method
		}
	}



	//Given a dataset, this retuns the entropy of the dataset
	double calcEntropy(ArrayList<Datum> datalist)
	{
		double entropy = 0;
		double px = 0;
		float [] counter= new float[2];
		if (datalist.size()==0)
			return 0;
		double num0 = 0.00000001,num1 = 0.000000001;

		//calculates the number of points belonging to each of the labels
		for (Datum d : datalist)
		{
			counter[d.y]+=1;
		}
		//calculates the entropy using the formula specified in the document
		for (int i = 0 ; i< counter.length ; i++)
		{
			if (counter[i]>0)
			{
				px = counter[i]/datalist.size();
				entropy -= (px*Math.log(px)/Math.log(2));
			}
		}

		return entropy;
	}


	// given a datapoint (without the label) calls the DTNode.classifyAtNode() on the this of the calling DecisionTree object
	int classify(double[] xQuery ) {
		DTNode node = this.rootDTNode;
		return node.classifyAtNode( xQuery );
	}

    // Checks the performance of a DecisionTree on a dataset
    //  This method is provided in case you would like to compare your
    //results with the reference values provided in the PDF in the Data
    //section of the PDF

    String checkPerformance( ArrayList<Datum> datalist)
	{
		DecimalFormat df = new DecimalFormat("0.000");
		float total = datalist.size();
		float count = 0;

		for (int s = 0 ; s < datalist.size() ; s++) {
			double[] x = datalist.get(s).x;
			int result = datalist.get(s).y;
			if (classify(x) != result) {
				count = count + 1;
			}
		}

		return df.format((count/total));
	}


	//Given two DecisionTree objects, this method checks if both the trees are equal by
	//calling onto the DTNode.equals() method
	public static boolean equals(DecisionTree dt1,  DecisionTree dt2)
	{
		boolean flag = true;
		flag = dt1.rootDTNode.equals(dt2.rootDTNode);
		return flag;
	}

}
