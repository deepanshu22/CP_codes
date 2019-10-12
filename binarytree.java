package july17;

import java.util.ArrayList;
import java.util.LinkedList;

public class binarytree {

	private class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
		}
	}

	Node root;

	public binarytree(int[] arr) {
		LinkedList<Node> st = new LinkedList();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == -1) {
				Node top = st.removeFirst();
				if (st.size() == 0) {
					root = top;
				} else {
					if (st.getFirst().left == null) {
						st.getFirst().left = top;
					} else {
						st.getFirst().right = top;
					}
				}
			} else {
				st.addFirst(new Node(arr[i]));
			}
		}
	}

	public binarytree(int[] pos, int[] ino) {
		root = construct1(pos, ino, 0, pos.length - 1, 0, ino.length - 1);
	}

	// private Node construct(int[] preo, int[] ino, int plo, int phi, int ilo,
	// int ihi) {
	// if (plo > phi || ilo > ihi) {
	// return null;
	// }
	//
	// Node n1 = new Node(preo[plo]);
	// int index = 0;
	// for (int i = 0; i < ino.length; i++) {
	// if (n1.data == ino[i]) {
	// index = i;
	// break;
	// }
	// }
	//
	// n1.left = construct(preo, ino, plo + 1,index - ilo, ilo, index - 1);
	// n1.right = construct(preo, ino, plo + index - ilo+1, phi, index + 1,
	// ihi);
	//
	// return n1;
	// }
	private Node construct1(int[] pos, int[] ino, int plo, int phi, int ilo, int ihi) {
		if (plo > phi || ilo > ihi) {
			return null;
		}
		Node n2 = new Node(pos[phi]);
		int index = 0;
		for (int i = 0; i < ino.length; i++) {
			if(n2.data == ino[i]) {
				index = i;
				break;
			}
		}
		n2.left = construct1(pos, ino, plo,phi-index - 1, ilo, index - 1);
		n2.right = construct1(pos, ino, phi-index, phi - 1, index + 1, ihi);
		return n2;
	}

	public void display() {
		display(root);
	}

	private void display(Node node) {
		if (node == null) {
			return;
		}
		String str = "";
		if (node.left == null) {
			str = str + "-1 ->";
		} else {
			str = str + node.left.data + " ->";
		}
		str = str + node.data;
		if (node.right == null) {
			str = str + "<-  -1";
		} else {
			str = str + "<-  " + node.right.data;
		}
		System.out.println(str);
		display(node.left);
		display(node.right);
	}

	public int size1() {
		return size1(root);
	}

	private int size1(Node node) {
		int val = 0;
		if (node.left != null) {
			val = val + size1(node.left);
		}
		if (node.right != null) {
			val = val + size1(node.right);
		}
		return val + 1;
	}

	public int max() {
		return max(root);
	}

	private int max(Node node) {
		int max1 = node.data;
		if (node.left != null) {
			int h = max(node.left);
			if (h > max1) {
				max1 = h;
			}
		}
		if (node.right != null) {
			int h = max(node.right);
			if (h > max1) {
				max1 = h;
			}
		}
		return max1;
	}

	public int height() {
		return height(root);
	}

	private int height(Node node) {
		// int htmax = -1;
		// if (node.left != null) {
		// int h = height(node.left);
		// if (h > htmax) {
		// htmax = h;
		// }
		// }
		// if (node.right != null) {
		// int h = height(node.right);
		// if (h > htmax) {
		// htmax = h;
		// }
		// }
		// return htmax + 1;

		if (node == null) {
			return -1;
		}

		return Math.max(height(node.left), height(node.right)) + 1;

	}

	public boolean find(int data) {
		return find(root, data);
	}

	public boolean find(Node node, int data) {
		if (node.data == data) {
			return true;
		}
		if (node.left != null) {
			boolean ans = find(node.left, data);
			if (ans == true) {
				return true;
			}
		}
		if (node.right != null) {
			boolean ans = find(node.right, data);
			if (ans == true) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Integer> nodetorootpath(int data) {
		return nodetorootpath(root, data);
	}

	private ArrayList<Integer> nodetorootpath(Node node, int data) {
		if (node == null) {
			ArrayList<Integer> bres = new ArrayList<>();
			return bres;
		}
		ArrayList<Integer> myans = new ArrayList<>();
		ArrayList<Integer> lans = nodetorootpath(node.left, data);
		ArrayList<Integer> rans = nodetorootpath(node.right, data);
		if (node.data == data) {
			myans.add(node.data);
		}
		if (lans.size() > 0) {
			for (int x : lans) {
				myans.add(x);
			}
			myans.add(node.data);
		}
		if (rans.size() > 0) {
			for (int x : rans) {
				myans.add(x);
			}
			myans.add(node.data);
		}
		return myans;

	}

	public void printroottoleafpath(int lo, int hi) {
		printroottoleaf(root, "", 0, lo, hi);
	}

	private void printroottoleaf(Node node, String psf, int ssf, int lo, int hi) {
		if (node == null) {
			return;
		}
		if (node.right == null && node.left == null) {
			if (ssf >= lo && ssf <= hi) {
				System.out.println(psf);
			}
			return;
		}
		if (node == root) {
			psf = psf + node.data + " ";
		}
		printroottoleaf(node.left, psf + node.left.data + " ", ssf + node.left.data, lo, hi);
		printroottoleaf(node.right, psf + node.right.data + " ", ssf + node.right.data, lo, hi);

	}

	public void removeleaves() {
		// removeleaves(root);
		root = removeleaves1(root);
	}

	private void removeleaves(Node node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			if (node.left.left == null && node.left.right == null) {
				node.left = null;
			}
			removeleaves(node.left);
		}
		if (node.right != null) {
			if (node.right.left == null && node.right.right == null) {
				node.right = null;
			}
			removeleaves(node.right);
		}
	}

	private Node removeleaves1(Node node) {
		if (node == null) {
			return null;
		}
		if (node.left == null && node.right == null) {
			return null;
		}
		node.left = removeleaves1(node.left);
		node.right = removeleaves1(node.right);
		return node;
	}

	public void singlechild() {
		singlechild(root);
	}

	private void singlechild(Node node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			if (node.left.left != null && node.left.right == null) {
				System.out.println(node.left.left.data);
			} else if (node.left.left == null && node.left.right != null) {
				System.out.println(node.left.right.data);
			}
			singlechild(node.left);
		}
		if (node.right != null) {
			if (node.right.left != null && node.right.right == null) {
				System.out.println(node.right.left.data);
			} else if (node.right.left == null && node.right.right != null) {
				System.out.println(node.right.right.data);
			}
			singlechild(node.right);
		}
	}

	public void kstepsawayfar(int data, int steps) {
		ArrayList<Node> paths = nodetorootpath1(root, data);

		for (int i = 0; i < paths.size() && (steps - i) >= 0; i++) {
			printstepsdown(paths.get(i), steps - i, i == 0 ? null : paths.get(i - 1));
		}

	}

	private void printstepsdown(Node node, int steps, Node obs) {
		if (node == null || node == obs) {
			return;
		}
		if (steps == 0) {
			System.out.println(node.data);
			return;
		}
		printstepsdown(node.left, steps - 1, obs);
		printstepsdown(node.right, steps - 1, obs);

	}

	private ArrayList<Node> nodetorootpath1(Node node, int data) {
		if (node == null) {
			return new ArrayList<>();
		}
		// ArrayList<Node> myans = new ArrayList<>();
		// ArrayList<Node> lans = nodetorootpath1(node.left,data);
		// ArrayList<Node> rans = nodetorootpath1(node.right,data);
		// if(node.data==data)
		// {
		// myans.add(node);
		// }
		// if(lans.size()>0)
		// {
		// for(Node x : lans)
		// {
		// myans.add(x);
		// }
		// myans.add(node);
		// }
		// if(rans.size()>0)
		// {
		// for(Node x : rans)
		// {
		// myans.add(x);
		// }
		// myans.add(node);
		// }

		if (node.data == data) {
			ArrayList<Node> list = new ArrayList<>();
			list.add(node);
			return list;
		}
		ArrayList<Node> list = new ArrayList<>();
		list = nodetorootpath1(node.left, data);
		if (list.size() > 0) {
			list.add(node);
			return list;
		}

		list = nodetorootpath1(node.right, data);
		if (list.size() > 0) {
			list.add(node);
			return list;
		}

		return new ArrayList<>();

	}

	public void levelorder() {
		LinkedList<Node> q = new LinkedList<>();
		q.addLast(root);
		while (q.size() > 0) {
			Node rem = q.removeFirst();
			System.out.print(rem.data + " ");
			if (rem.left != null) {
				q.addLast(rem.left);
			}
			if (rem.right != null) {
				q.addLast(rem.right);
			}
		}
	}

	public void preo() {
		preo(root);
	}

	private void preo(Node node) {
		if (node == null) {
			return;
		}
		System.out.println(node.data + " ");
		preo(node.left);
		preo(node.right);
	}

	public void ino() {
		ino(root);
	}

	public void ino(Node node) {
		if (node == null) {
			return;
		}
		ino(node.left);
		System.out.println(node.data + " ");
		ino(node.right);
	}

	public int diameter() {
		return diameter(root);
	}

	private int diameter(Node node) {
		if (node == null) {
			return 0;
		}
		int dia = height(node.left) + height(node.right) + 2;

		int ldia = diameter(node.left);
		int rdia = diameter(node.right);

		int ans = Math.max(ldia, rdia);
		int fans = Math.max(ans, dia);
		return fans;
	}
	private class diapair{
		int height = -1;
		int diameter =0;
	}
	public int diameter2(){
		diapair pair = diameter2(root);
		return pair.diameter;
	}
	private diapair diameter2(Node node)
	{
		if(node==null)
		{
			return new diapair();
		}
		diapair dl = diameter2(node.left);
		diapair dr = diameter2(node.right);
		diapair d1 = new diapair();
		
		d1.height = Math.max(dl.height, dr.height) + 2;
		d1.diameter = Math.max((dl.height +dr.height + 2),Math.max(dl.diameter,dr.diameter));
		return d1;			
	}
	private class balancetre {
		boolean isbalance = true;
		int height = -1;
	}

	public boolean isbalanced() {
		balancetre b = isbalanced(this.root);
		return b.isbalance;

	}

	private balancetre isbalanced(Node node) {
		if (node == null) {
			return new balancetre();
		}
		balancetre bt = new balancetre();
		balancetre bl = isbalanced(node.left);
		if (bl.isbalance == false) {
			return bl;
		}
		balancetre br = isbalanced(node.right);
		if (br.isbalance == false) {
			return br;
		}

		int delta = Math.abs(bl.height - br.height);
		if (delta > 1) {
			bt.isbalance = false;
		} else {
			bt.isbalance = true;
		}
		return bt;

	}
	private class bstpair {
		private int max;
		private int min;
		private boolean isBST;
	}

	public boolean isbst() {
		bstpair bp = isbst(this.root);
		return bp.isBST;

	}

	private bstpair isbst(Node node) {
		if (node == null) {
			bstpair bp = new bstpair();

			bp.isBST = true;
			bp.max = Integer.MIN_VALUE;
			bp.min = Integer.MAX_VALUE;
			return bp;
		}

		bstpair lp = isbst(node.left);
		bstpair rp = isbst(node.right);

		bstpair mp = new bstpair();

		mp.max = Math.max(node.data, Math.max(lp.max, rp.max));
		mp.min = Math.min(node.data, Math.min(lp.min, rp.min));

		if (lp.isBST && rp.isBST && node.data > lp.max && node.data < rp.min) {
			mp.isBST = true;
		}
		return mp;
	}

	private class tpair {
		Node node;
		boolean mywork;
		boolean leftwork;
		boolean rightwork;
	}

	public void traversaliterative() {
		LinkedList<tpair> stack = new LinkedList<>();

		tpair rootpair = new tpair();
		rootpair.node = root;
		stack.addFirst(rootpair);

		while (stack.size() > 0) {
			tpair tos = stack.getFirst();

			if (!tos.mywork) {
				tos.mywork = true;
			} else if (!tos.leftwork) {
				tos.leftwork = true;
				if (tos.node.left != null) {
					tpair tp = new tpair();
					tp.node = tp.node.left;
					stack.addFirst(tp);
				}
			} else if (!tos.rightwork) {
				tos.rightwork = true;
				if (tos.node.right != null) {
					tpair tp = new tpair();
					tp.node = tp.node.right;
					stack.addFirst(tp);
				} else {
					stack.removeFirst();
				}
			}
		}
	}
}