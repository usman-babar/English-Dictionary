package englishDictionary;

import java.util.Scanner;

public class Main {

	static AVL<DataSet> ds = new AVL<DataSet>();

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		welcome();// welcome menu
		ds.readFile();// read file

		System.out.println("\n\n\t\t\t********************************************");
		System.out.println("      \t\t\t   Dictionary Words Count = " + AVL.count);
		System.out.println("\t\t\t********************************************\n\n");
		
		int choice;

		do {

			menu();
			choice = in.nextInt();
			in.nextLine();

			if (choice == 1) {// search

				System.out.println("Enter the word: ");
				String word = in.nextLine();

				ds.search(ds.root, word);
			} else if (choice == 2) {// delete
				try {
					System.out.println("Enter the word: ");
					String word = in.nextLine();

					ds.deleteNode(ds.root, word);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (choice == 3) {// preorder
				System.out.println();
				ds.preOrder();
				System.out.println();
			} else if (choice == 4) {// inorder
				System.out.println();
				ds.inOrder();
				System.out.println();
			} else if (choice == 5) {// postorder
				System.out.println();
				ds.postOrder();
				System.out.println();
			}
		} while (choice != 0);

		in.close();

	}

	public static void welcome() {
		System.out.println("**********************************************");
		System.out.println("|                   Welcome                  |");
		System.out.println("**********************************************");
	}

	public static void menu() {
		System.out.println("\n|*************************************|");
		System.out.println("| Press:     1. Search a Word.        |");
		System.out.println("|            2. Delete a Word.        |");
		System.out.println("|            3. Pre-Order.            |");
		System.out.println("|            4. In-Order.             |");
		System.out.println("|            5. Post-Order.           |");
		System.out.println("|                                     |");
		System.out.println("|            0. Exit.                 |");
		System.out.println("***************************************\n");

	}
}
