
import java.util.PriorityQueue;

import org.w3c.dom.Node;

public class Huffman {
	// alphabet size of extended ASCII
	private static final int R = 256;



	// Huffman trie node
	private record Node(char ch, int freq, Huffman.Node left, Huffman.Node right) implements Comparable<Node> {

		// is the node a leaf node?
		private boolean isLeaf() {
			assert ((left == null) && (right == null)) || ((left != null) && (right != null));
			return left == null;
		}

		// compare, based on frequency
		@Override
		public int compareTo(Node that) {
			return this.freq - that.freq;
		}
	}

	/**
	 * Reads a sequence of 8-bit bytes from standard input; compresses them using
	 * Huffman codes with an 8-bit alphabet; and writes the results to standard
	 * output.
	 */
	public static void compress() {
		// this read the input
		
		String s = BinaryStdIn.readString();
		char[] input = s.toCharArray();
		
		//this will tabulate the frequency counts

		int[] freq = new int[R];
		for (int i = 0; i < input.length; i++) {
			freq[input[i]]++;
		}
		
		
		//this builds the huffman trie
		Node root = buildTrie(freq);
		//build the code for the table
		String[] st = new String[R];
		buildCode(st, root, "");
		//print out trie for the decoder
		writeTrie(root);
		//this will print the number of bytes in our original message
		BinaryStdOut.write(input.length);
		//this uses Huffman code to encode the input
		for (int i = 0; i < input.length; i++) {
			String code = st[input[i]];
			for (int j = 0; j < code.length(); j++) {
				if (code.charAt(j) == '0') {
					BinaryStdOut.write(false);
				} else if (code.charAt(j) == '1') {
					BinaryStdOut.write(true);
				} else
					throw new IllegalStateException("Illegal State");
			}
		}

		BinaryStdOut.close();

	}

	// write bitstring-encoded trie to standard output
	public static void writeTrie(Node x) {
		if (x.isLeaf()) {
			BinaryStdOut.write(true);
			BinaryStdOut.write(x.ch, 8);
			return;
		}
		BinaryStdOut.write(false);
		writeTrie(x.left);
		writeTrie(x.right);

	}

	/**
	 * Reads a sequence of bits that represents a Huffman-compressed message from
	 * standard input; expands them; and writes the results to standard output.
	 */
	public static void expand() {
		//read the huffman trie from input stream
		Node root = readTrie();
		//number of bytes to write
		int N = BinaryStdIn.readInt();
		//decode it using the huffman trie
		for (int i = 0; i < N; i++) {
			Node x = root;
			while (!x.isLeaf()) {
				if (!BinaryStdIn.readBoolean())
					x = x.left;
				else
					x = x.right;
			}
			BinaryStdOut.write(x.ch, 8);
		}
		BinaryStdOut.close();
	}

	public static Node readTrie() {
		if (BinaryStdIn.readBoolean()) {
			return new Node(BinaryStdIn.readChar(), -1, null, null);
		}

		return new Node('\0', -1, readTrie(), readTrie());

	}
	
	//this Builds the huffman trie given frequencies

	public static Node buildTrie(int[] freq) {
		//we could also use MiniPQ file here but i decided to use java's priority queue import to reduce file dependencies.
		//here we  initialize the priority queue
		PriorityQueue<Node> pq = new PriorityQueue<Node>();

		for (char i = 0; i < R; i++) {
			if (freq[i] > 0) {
				pq.add(new Node(i, freq[i], null, null));
			}
		}
		
		//just in case we have only one character
		

		if (pq.size() == 1) {
			if (freq['\0'] == 0) {
				pq.add(new Node('\0', 0, null, null));
			} else {
				pq.add(new Node('\1', 0, null, null));
			}
		}
		
		//we merge the two smallest trees

		while (pq.size() > 1) {
			Node x = pq.remove();
			Node y = pq.remove();
			Node parent = new Node('\0', x.freq + y.freq, x, y);
			pq.add(parent);

		}
		return pq.remove();
	}
	//create lookup table of symbols and encodings
	public static void buildCode(String[] st, Node x, String s) {
		if (!x.isLeaf()) {
			buildCode(st, x.left, s + '0');
			buildCode(st, x.right, s + '1');
		} else {
			st[x.ch] = s;
		}
	}
	//receive command line arguments and throw error for illegal  ones.
	public static void main(String[] args) {
		if (args[0].equals("-"))
			compress();
		else if (args[0].equals("+"))
			expand();
		else
			throw new IllegalArgumentException("Illegal command line argument");
	}
}
