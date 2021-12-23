package englishDictionary;

public class Node<T> {

	T data;
	int height;
	Node<T> left;
	Node<T> right;

	public Node(T data) {
		this.data = data;
		this.height = 0;
	}
}
