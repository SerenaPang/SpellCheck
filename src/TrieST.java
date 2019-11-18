
/*This class uses an R-way trie to implement a symbol table.
 * 
 * 
 * */

public class TrieST<Value> {

	private static int R = 256; // radix
	private Node root; // root of trie

	private static class Node {
		private Object val;
		private Node[] next = new Node[R];
	}

	/*
	 * Geting the specific string from the trie
	 */
	public String get(String key) {

		Node x = get(root, key, 0);

		if (x == null)
			return null;
		
		return key;
	}

	private Node get(Node x, String key, int d) {
		if (x == null)
			return null;

		if (d == key.length())
			return x;

		char c = key.charAt(d);// use dth key char to identify subtries
		return get(x.next[c], key, d + 1);
	}
	/*
	 * Putting a string to the trie
	 * 
	 */
	public void put(String key, Value val) {
		root = put(root, key, val, 0);
	}

	private Node put(Node x, String key, Value val, int currentIndex) {

		// change value associated with key if in subtries rooted at x.

		if (x == null)
			x = new Node();

		if (currentIndex == key.length()) {

			x.val = val;
			return x;
		}

		char c = key.charAt(currentIndex);// use dth key char to identify subtrie
		x.next[c] = put(x.next[c], key, val, currentIndex + 1);
		return x;

	}

	/*
	 * getting the size of non null values in the trie
	 */
	public int size() {
		return size(root);
	}

	/*
	 * traverse all the nodes in the trie and counting the number having non null
	 * value
	 * 
	 */
	private int size(Node x) {

		if (x == null)
			return 0;

		int cnt = 0;
		if (x.val != null)
			cnt++;

		for (char c = 0; c < R; c++)
			cnt += size(x.next[c]);

		return cnt;

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (String key : keys()) {
			sb.append(key);
			sb.append("\n");
		}

		return sb.toString();
	}

	public Iterable<String> keys() {

		return keysWithPrefix("");
	}

	public Iterable<String> keysWithPrefix(String pre) {
		Queue<String> q = new Queue<String>();
		collect(get(root, pre, 0), pre, q);
		return q;
	}

	private void collect(Node x, String pre, Queue<String> q) {
		if (x == null)
			return;
		if (x.val != null)
			q.enqueue(pre);

		for (char c = 0; c < R; c++)
			collect(x.next[c], pre + c, q);
	}

	public Iterable<String> keysThatMatch(String pat) {
		Queue<String> q = new Queue<String>();
		collect(root, "", pat, q);
		return q;
	}

	public void collect(Node x, String pre, String pat, Queue<String> q) {
		int d = pre.length();
		if (x == null)
			return;
		if (d == pat.length() && x.val != null) {
			System.out.println("TrieST.collect() enqueuing");
			q.enqueue(pre);
		}
		if (d == pat.length())
			return;

		char next = pat.charAt(d);
		for (char c = 0; c < R; c++)
			if (next == '.' || next == c)
				collect(x.next[c], pre + c, pat, q);
	}

	public String longestPrefix(String s) {
		int length = search(root, s, 0, 0);
		return s.substring(0, length);
	}

	private int search(Node x, String s, int d, int length) {
		if (x == null)
			return length;
		if (x.val != null)
			length = d;
		if (d == s.length())
			return length;
		char c = s.charAt(d);

		return search(x.next[c], s, d + 1, length);
	}

	public static void main(String[] args) {

		TrieST<String> mytrie = new TrieST<String>();

		mytrie.put("she", "0");
		mytrie.put("sells", "1");
		mytrie.put("sea", "2");
		mytrie.put("alphabet", "3");
		mytrie.put("alpha", "4");

		String value = mytrie.get("se");
		System.out.println("Getting value for 'sells' " + value);

		int sizeOfTrie = mytrie.size();
		System.out.println("Size of the Trie: " + sizeOfTrie);

		// keys that matches "sh" will be return as a queue of strings
		Iterable<String> keysThatMatch = mytrie.keysThatMatch("sells");
		// assigned that queue to this queue
		System.out.println("Absolute Match: ");
		for (String key : keysThatMatch) {
			System.out.println(key);
		}

		Iterable<String> keysThatMatchPrefix = mytrie.keysWithPrefix("alph");
		System.out.println("Prefix Matches: ");
		for (String key : keysThatMatchPrefix) {
			System.out.println(key);
		}

	}

}
