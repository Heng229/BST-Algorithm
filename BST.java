import java.util.Random;
import java.util.Scanner;

public class BSTApp{
	
	Node root;
	
	//Insert Node Start---
	public void insertNode(int data) {
		//Create new node with value parse in.
		Node newNode = new Node(data);
		
		if(root == null) {
			//if root is null, assign the new node as root 
			root = newNode;
		}else {
			//else insert based on BST rules.
			Node targeted = root;
			Node parent;
			//looping to determine whether to insert into left or right.
			while(true){
				//Go to left.
				parent = targeted;
				if(data < targeted.data) {
					targeted = targeted.left;
					//set the parent.left to newNode (new value insert). Then new node will have new left and right child with null value.
					if(targeted == null) {
						parent.left = newNode;
						return;
					}
				//Go to right
				}else {
					targeted = targeted.right;
					//set the parent.right child to newNode (new value insert). Then new node will have new left and right child with null value.
					if(targeted == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}
	//Insert Node End---
	
	//Delete Node Start---
	public boolean deleteNode(int data) {
		Node targeted = root;
		Node parent = root;
		boolean isLeft = true;
		
		if (root == null){
			return false;
		}
		
		//Keep loop to search for the targeted node.
		while(targeted.data != data) {
			parent = targeted;
			
			if(data < targeted.data) {
				isLeft = true;
				targeted = targeted.left; 
			}else {
				isLeft = false;
				targeted = targeted.right;
			}
			
			if(targeted == null) {
				return false;
			}
			
		}	
		//Once found out not null and located the node, determine child existence
		//No child
		if(targeted.left == null && targeted.right == null) {
			if(targeted == root) {
				root = null;
			}else if(isLeft){
				parent.left = null;
			}else {
				parent.right = null;
			}
		//Left child only
		}else if(targeted.right == null) {
			if(targeted == root) {
				root = targeted.left;
			}else if(isLeft) {
				parent.left = targeted.left;
			}else {
				parent.right = targeted.left;
			}	
		//Right child only
		}else if(targeted.left == null) {
			if(targeted == root) {
				root = targeted.right;
			}else if(isLeft) {
				parent.left = targeted.right;
			}else {
				parent.right = targeted.right;
			}
		//With 2 Child
		}else {
			Node successor = getSuccessor(targeted);
			if(targeted == root) {
				root = successor;
			}else if(isLeft) {
				parent.left = successor;
			}else {
				parent.right = successor;
			}
			successor.left = targeted.left;
		}
		return true;
	}
	//Delete End------
	
	//Get successor
		public Node getSuccessor(Node nodetodel) {
			Node newSuccessor = nodetodel;
			Node successor = nodetodel;
			//always choose right child, find smallest in right subtree
			Node targeted = nodetodel.right;
			
			//While no more left child 
			while(targeted != null) {
				newSuccessor = successor;
				successor = targeted;
				targeted = targeted.left;
			}
			
			if(successor != nodetodel.right) {
				newSuccessor.left = successor.right;
				successor.right = nodetodel.right;
			}
			
			return successor;
		}
	//---get successor end
	
	//Search Start------
	public boolean searchNode(int data) {
		Node targeted = root;
		
		//root is null return false
		if (root == null){
			return false;
		}
		
		//When not null loop through data & compare, at the end if is null then return false indicate not found. If targeted not null proceed to return true.
		while(targeted.data != data){
			
			if(data < targeted.data) {
				targeted = targeted.left; 
			}else {
				
				targeted = targeted.right;
			}
			
			if(targeted == null) {
				return false;
			}
		}
		return true;
	}
	//Search end---
	
	//Display Inorder
	public String inOrderTraversal(Node node) {
		if(node != null) {
			inOrderTraversal(node.left);
			System.out.println(node);
			inOrderTraversal(node.right);
			return "Inorder traversal";
		}else {
			return "Node is empty";
		}
	}
	
	//Display Preoder
	public String preOrderTraversal(Node node) {
		if(node != null) {
			System.out.println(node);
			preOrderTraversal(node.left);
			preOrderTraversal(node.right);
			return "Preorder traversal";
		}else {
			return "Node is empty";
		}
	}
	
	//Display Postorder
	public String postOrderTraversal(Node node) {
		if(node != null) {
			postOrderTraversal(node.left);
			postOrderTraversal(node.right);
			System.out.println(node);
			return "Postorder traversal";
		}else {
			return "Node is empty";
		}
	}
	
	//For check input error
	public boolean detectError(String inputStr){
		int input = 0;
		boolean errorCase = false;
		try {
			input = Integer.parseInt(inputStr);
			errorCase = false;
		}catch(Exception e) {
			errorCase = true;
			System.out.println("Please enter an integer only.\n");
		}
		return errorCase;
	}
	
	
	public static void main(String[] args) {
		//Create object for BSTApp name bst
		BSTApp bst = new BSTApp();
		Scanner scan = new Scanner(System.in);
		String optStr = "0";
		int opt = 0;
		boolean error = false;
		
		String inputStr = "0";
		boolean errorCase = true;
		
		do{
			System.out.println("\n\t--------------\n   Binary Search Tree Demo\n\t--------------");
			System.out.println("1. Insert\n2. Delete\n3. Search\n4. Display Node(Inorder Traversal)\n5. Display Node(Preorder Traversal)\n6. Display Node(Postorder Traversal)\n-1. Exit");
			System.out.print("Option : ");
			optStr = scan.nextLine();
			
			error = bst.detectError(optStr);
			
			if(error == false) {
			opt = Integer.parseInt(optStr);	
			switch(opt) {
			case 1:
				boolean case1error = true;
				String case1optStr = "0";
				int case1optNum = 0;
				do {
					//1 to insert manually one by one , 2 to specify a size between 0 - 100 then random number between 0-100 will be generated and insert into tree.
					System.out.println("===Insert Operation===");
					System.out.println("1. Insert Manually\n2. Insert random number between 0-100 automatically.\n");
					System.out.print("Option : ");
					case1optStr = scan.nextLine();
					
					case1error = bst.detectError(case1optStr);
					
					if(case1error == false) {
						case1optNum = Integer.parseInt(case1optStr);
						
						switch(case1optNum) {
						case 1:
							boolean errorCase1 = true;
							boolean quit = true;
							while(quit) {
								do{
									System.out.print("Please enter a key integer value to insert a node, you can enter \"QUIT\" to stop inserting.\n");
									System.out.print("Value to be insert : ");
									inputStr = scan.nextLine();
								
									if(inputStr.equals("QUIT")) {
										System.out.println("You quit inserting.\n");
										quit = false;
										break;
									}
								
									errorCase1 = bst.detectError(inputStr);
								}while(errorCase1);
							
								if(quit) {
									if(bst.searchNode(Integer.parseInt(inputStr))) {
										System.out.println("Node with this key value already exist. No new value inserted.\n");
									}
									else{
										bst.insertNode(Integer.parseInt(inputStr));
										System.out.println("You inserted value : " + Integer.parseInt(inputStr) + "\n");
									}
								}
							}
						break;
						case 2:
							boolean errorCase2 = true;
							do {
								System.out.println("Please enter the number for the size of random value(in between 0 and 100) to be inserted, size maximum 100 only.");
								System.out.print("Size : ");
								inputStr = scan.nextLine();
						
								errorCase2 = bst.detectError(inputStr);
								
								if(errorCase2 != true) {
									int size = Integer.parseInt(inputStr);
								
									if(size <= 0 || size > 100){
										System.out.println("Please enter valid size. 0-100\n");
										errorCase2 = true;
									}else {
										Random ranNum = new Random();
										for(int i = 0; i < size ; i++) {
											int temp = ranNum.nextInt(100);
											if(bst.searchNode(temp)==false){
												bst.insertNode(temp);
											}
										}
										System.out.println(size + " random numbers generated and inserted into tree.");
									}
								}
								
							}while(errorCase2);
							
						break;
						default:
							System.out.println("Please enter a valid option.\n");
							case1error = true;
						break;
						}
					}	
				}while(case1error);
				break;
			case 2:
				boolean quit = true;
				if(bst.root == null) {
					System.out.println("Node is empty");
				}else {
					System.out.println("===Delete Operation===");
					do {
					System.out.println("Please enter a key value to delete a node, or you can enter \"QUIT\" to stop delete.");
					System.out.print("Value : ");
					inputStr = scan.nextLine();
					if(inputStr.equals("QUIT")) {
						System.out.println("You quit delete.");
						quit = false;
						break;
					}
					errorCase = bst.detectError(inputStr);
					
						if(quit != errorCase) {
							if(bst.deleteNode(Integer.parseInt(inputStr))) {
								System.out.println("You deleted node with key value : " + Integer.parseInt(inputStr) + "\n");
								errorCase = true;
							}else {
								System.out.println("No node deleted, key value : " + Integer.parseInt(inputStr)+ " not found.\n");
								errorCase = true;
							}
						}
					}while(errorCase);
				}
				break;
			case 3:
				boolean quitCase3 = true;
				if(bst.root == null) {
					System.out.println("Node is empty.\n");
				}
				else {
					System.out.println("===Search Operation===");
					do {
						System.out.print("Please enter a key value to search a node, you can stop searching by enter \"QUIT\".\n");
						System.out.print("Value : ");
						inputStr = scan.nextLine();
						
						if(inputStr.equals("QUIT")){
							System.out.println("You quit search.\n");
							quit = false;
							break;
						}
						
						errorCase = bst.detectError(inputStr);
					
						
						if(quitCase3 != errorCase) {
							if(bst.searchNode(Integer.parseInt(inputStr))) {
								System.out.println("Node with key value : " + Integer.parseInt(inputStr) + " exist.\n");
								errorCase = true;
							}else{
								System.out.println("Node with key value : " + Integer.parseInt(inputStr)+ " not exist.\n");
								errorCase = true;
							}
						}
					}while(errorCase);
				}
				break;
			case 4:
				if(bst.root != null){
				System.out.println("The root is node with " + bst.root);
				}
				System.out.println(bst.inOrderTraversal(bst.root));
				break;
			case 5:
				if(bst.root != null){
				System.out.println("The root is node with " + bst.root);
				}
				System.out.println(bst.preOrderTraversal(bst.root));
				break;
			case 6:
				if(bst.root != null){
				System.out.println("The root is node with " + bst.root);
				}
				System.out.println(bst.postOrderTraversal(bst.root));
				break;
			case -1:
				System.out.println("You exited the program.\n");
				System.exit(0);
				break;
			default:
				System.out.println("Please enter a valid option.\n");
				break;
			}
			}
			error = true;
		}while(error);
	}
}

//Node class to create node object
class Node{
	int data;
	Node left;
	Node right;
	
	Node(int data){
		this.data = data;
	}
	
	public String toString() {
		return "key value : " + data;
	}
}