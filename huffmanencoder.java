package july30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class huffmanencoder {

	private HashMap<Character, String> Encoder = new HashMap<>();
	private HashMap<String, Character> Decoder = new HashMap<>();

	private class Node implements Comparable<Node> {
		char data;
		int freq;
		Node left;
		Node right;

		PriorityQueue<Node> pq = new PriorityQueue<>();

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.freq - o.freq;
		}
	}

	public HashMap<Character, Integer> fmap = new HashMap<>();

	public huffmanencoder(String feeder) {
		for (int i = 0; i < feeder.length(); i++) {
			char ch = feeder.charAt(i);
			fmap.put(ch, fmap.containsKey(ch) == false ? i : fmap.get(ch) + 1);
		}
		PriorityQueue<Node> pq = new PriorityQueue<>();
		ArrayList<Character> keys = new ArrayList<>(fmap.keySet());
		for(Character key: keys){
			Node node = new Node();
			node.data = key;
			node.freq = fmap.get(key);
			pq.add(node);
		}		
		// while pq.size is 1, remove two, merge and put back
		while(pq.size() > 1){
			Node one = pq.remove();
			Node two = pq.remove();
			
			Node merged = new Node();
			merged.freq = one.freq + two.freq;
			merged.left = one;
			merged.right = two;
			
			pq.add(merged);
		}
		
		// retrieve last node, travel it and fill encoder and decoder
		Node root = pq.remove();
		traverse(root, "");
		System.out.println(Encoder);
	}
	

	public void traverse(Node node, String psf) {
		if (node.left == null && node.right == null) {
			Encoder.put(node.data, psf);
			Decoder.put(psf, node.data);
			return;
		}
		traverse(node.left, psf + "0");
		traverse(node.right, psf + "1");
		
		
		
	}

	public String encode(String str) {

		String code = "";
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			code = code + Encoder.get(ch);
		}
		return code;
	}

	public String decode(String str) {
		String decode = "";
		String prefix = "";
		for (int i = 0; i < str.length(); i++) {
			prefix = prefix + str.charAt(i);
			if (Decoder.containsKey(prefix)) {
				decode = decode + Decoder.get(prefix);
				prefix = "";
			}
		}
		return decode;
	}
}
