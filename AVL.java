package englishDictionary;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class AVL<T> implements Comparable<T> {

	static int count  = 0;
	Node<T> root;

	@SuppressWarnings("resource")
	public void readFile() { // start

		try {

			FileReader file = new FileReader("C:\\Users\\USMN BABAR\\OneDrive\\Desktop\\Hold Things\\UnsortedDictionary.txt");

			Scanner readLine = new Scanner(file);

			while (readLine.hasNext()) {
				count++;
				String data = readLine.nextLine();
				String[] array = data.split(":");

				DataSet d = new DataSet();

				d.setWord(array[0]);
				d.setMeaning(array[1]);

				insert(d);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	int height(Node<T> n) {
		if (n == null) {
			return 0;
		} else {
			return n.height;
		}
	}

	int max(int a, int b) {
		if (a > b) {
			return a;
		} else {
			return b;
		}
	}

	Node<T> rightRotate(Node<T> b) {

		Node<T> a = b.left;
		Node<T> c = a.right;

		a.right = b;
		b.left = c;
		b.height = max(height(b.left), height(b.right)) + 1;
		a.height = max(height(a.left), height(a.right)) + 1;

		return a;
	}

	Node<T> leftRotate(Node<T> a) {
		Node<T> b = a.right;
		Node<T> c = b.left;

		b.left = a;
		a.right = c;
		a.height = max(height(a.left), height(a.right)) + 1;
		b.height = max(height(b.left), height(b.right)) + 1;

		return b;
	}

	int getBalanceFactor(Node<T> n) {
		if (n == null) {
			return 0;
		} else {
			return height(n.left) - height(n.right);
		}
	}

	public void insert(DataSet d) {
		Node<T> r = root;
		root = insert(r, d);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	Node<T> insert(Node<T> node, DataSet d) {

		if (node == null) {
			root = new Node(d);
			return root;
		}

		if (((DataSet) node.data).getWord().compareToIgnoreCase(d.getWord()) > 0) {
			node.left = insert(node.left, d);

		}

		else if (((DataSet) node.data).getWord().compareToIgnoreCase(d.getWord()) < 0) {
			node.right = insert(node.right, d);
		}

		else {
			return node;
		}

		node.height = max(height(node.left), height(node.right)) + 1;
		int balanceFactor = getBalanceFactor(node);

		if (balanceFactor > 1) {
//			if (value < node.left.data) // RR
			if (((DataSet) node.left.data).getWord().compareToIgnoreCase(d.getWord()) > 0) // RR
				return rightRotate(node);

//			else if (value > node.left.data)// RL
			else if (((DataSet) node.left.data).getWord().compareToIgnoreCase(d.getWord()) < 0)// RL

				node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		if (balanceFactor < -1) {

//			if (value > node.right.data) // LL
			if (((DataSet) node.right.data).getWord().compareToIgnoreCase(d.getWord()) < 0) // LL
				return leftRotate(node);

//			else if (value < node.right.data)// LR
			else if (((DataSet) node.right.data).getWord().compareToIgnoreCase(d.getWord()) > 0)// LR
				node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	public Node<T> search(Node<T> r, String word) {

		try {

			if (root == null) {
				return r;
			}
			if (((DataSet) r.data).getWord().compareToIgnoreCase(word) == 0) {
				System.out.println(r.data.toString());
				return r;
			}

			if (((DataSet) r.data).getWord().compareToIgnoreCase(word) > 0) {
				r = search(r.left, word);
			}

			else {
				r = search(r.right, word);
			}
		} catch (Exception e) {
			System.out.println("\"" + word + "\"" + " Not Found");
		}
		return r;
	}

	Node<T> minValueNode(Node<T> node) {
		Node<T> current = node;

		while (current.left != null) {
			current = current.left;
		}

		return current;
	}

	Node<T> deleteNode(Node<T> node, String word) {

		if (node == null) {
			return node;
		}

//	        if (word < node.data)
		if (((DataSet) node.data).getWord().compareToIgnoreCase(word) > 0) {
			node.left = deleteNode(node.left, word);
		}

//	    else if (word > node.data)
		else if (((DataSet) node.data).getWord().compareToIgnoreCase(word) < 0) {
			node.right = deleteNode(node.right, word);
		}

		else {

			// node with only one child or no child
			if ((node.left == null) || (node.right == null)) {

				Node<T> temporary = null;
				if (temporary == node.left)
					temporary = node.right;
				else
					temporary = node.left;

				// No child case
				if (temporary == null) {
					temporary = node;
					node = null;
				}
				// One child case
				else {
					node = temporary;
				}
			}

			// node with two children
			else {
				Node<T> temp = minValueNode(node.right);

				node.data = temp.data;

				node.right = deleteNode(node.right, ((DataSet) temp.data).getWord());
			}
		}

		if (node == null) {
			return node;
		}

		node.height = max(height(node.left), height(node.right)) + 1;

		// this node became unbalanced)
		int balance = getBalanceFactor(node);

		// Right Right Case
		if (balance < -1 && getBalanceFactor(node.right) <= 0) {
			return leftRotate(node);
		}

		// Right Left Case
		if ((balance < -1) && (getBalanceFactor(node.right) > 0)) {
			root.right = rightRotate(node.right);
			return leftRotate(node);
		}

		// Left Left Case
		if (balance > 1 && getBalanceFactor(node.left) >= 0) {
			return rightRotate(node);
		}

		// Left Right Case
		if (balance > 1 && getBalanceFactor(node.left) < 0) {
			root.left = leftRotate(node.left);
			return rightRotate(node);
		}

		return node;
	}

	public void inOrder() {
		inOrder(root);
	}

	public void inOrder(Node<T> root) {

		if (root != null) {

			inOrder(root.left);

			try {

				FileWriter fw = new FileWriter("C:\\Users\\USMN BABAR\\OneDrive\\Desktop\\InOrder.txt", true);
				fw.write(root.data.toString() + "\n");
//				System.out.println("Successfully written");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(root.data);
			inOrder(root.right);
		}
	}

	public void preOrder() {
		preOrder(root);
	}

	public void preOrder(Node<T> root) {

		if (root != null) {

			try {

				FileWriter fw = new FileWriter("C:\\Users\\USMN BABAR\\OneDrive\\Desktop\\PreOrder.txt", true);
				fw.write(root.data.toString() + "\n");
//			System.out.println("Successfully written");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(root.data.toString());
			preOrder(root.left);
			preOrder(root.right);
		}
	}

	public void postOrder() {
		postOrder(root);
	}

	public void postOrder(Node<T> root) {

		if (root != null) {

			postOrder(root.left);
			postOrder(root.right);
			try {

				FileWriter fw = new FileWriter("C:\\Users\\USMN BABAR\\OneDrive\\Desktop\\PostOrder.txt", true);
				fw.write(root.data.toString() + "\n");
//			System.out.println("Successfully written");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(root.data);
		}
	}

	@Override
	public int compareTo(T o) {
		return 0;
	}

}