package july9;

public class addlastLL {

	private class Node {
		int data;
		Node next;
	}

	Node head;
	Node tail;
	int size;

	public void addlast(int val)// o(1)
	{
		if (this.size == 0) {
			handleaddwhensize0(val);
			return;
		}
		Node node = new Node();
		node.data = val;

		tail.next = node;
		tail = node;
		size++;
	}

	private void handleaddwhensize0(int val) {
		Node node = new Node();
		node.data = val;
		head = node;
		tail = node;
		size++;
	}

	public void display()// o(n)
	{
		for (Node node = head; node != null; node = node.next) {
			System.out.print(node.data + "-> ");
		}
	}

	public int getfirst() {
		if (size == 0) {
			System.out.println("linkedlist is empty");
		}
		return head.data;
	}

	public int getlast() {
		if (size == 0) {
			System.out.println("linkedlist out of bound");
		}
		return tail.data;
	}

	public int getAt(int idx) {
		if (size == 0) {
			System.out.println("list is empty");
			return -1;
		} else if (idx < 0 || idx >= size) {
			System.out.println("index out of bound");
			return -1;
		}
		Node node = head;
		for(int i = 0; i < idx; i++) {
			node = node.next;
		}
		return node.data;
	}

	public void addFirst(int val) {
		if (size == 0) {
			handleaddwhensize0(val);
			return;
		}
		Node node = new Node();
		node.data = val;
		node.next = head;
		head = node;
		size++;
	}

	private Node getNodeAt(int idx) {
		if (size == 0) {
			System.out.println("list is empty");
			return null;
		} else if (idx < 0 || idx >= size) {
			System.out.println("index out of bound");
			return null;
		}
		Node node = head;
		for (int i = 0; i < idx; i++) {
			node = node.next;
		}
		return node;

	}

	public void addat(int val, int idx) {
		if (idx < 0 || idx > size) {
			System.out.println("linked list out of bound");
			return;
		} else if (idx == 0) {
			this.addFirst(val);
		} else if (idx == size) {
			this.addlast(val);
		} else{
			Node node = new Node();
			node.data = val;

			Node nm1 = getNodeAt(idx - 1);
			Node np1 = nm1.next;

			nm1.next = node;
			node.next = np1;
			size++;
		}
	}

	private int handlewhensize1() {
		int rv = head.data;
		head = tail = null;
		size = 0;

		return rv;
	}

	public int removefirst() {
		if (size == 0) {
			System.out.println("list empty");
			return -1;
		} else if (size == 1) {
			int x = handlewhensize1();
			return x;
		}
		int rv = head.data;
		Node second = head.next;
		head = second;
		size--;
		return rv;
	}

	public int removelast()// o(n)
	{
		if (size == 0) {
			System.out.println("list empty");
			return -1;
		} else if (size == 1) {
			int x = handlewhensize1();
			return x;
		}
		int rv = tail.data;
		Node secondlast = getNodeAt(size - 2);
		tail = secondlast;
		tail.next = null;
		size--;
		return rv;
	}

	public int removeat(int idx) {
		if (idx < 0 || idx >= size) {
			System.out.println("linked list out of bound");
			return -1;
		} else if (idx == 0) {
			return this.removefirst();
		} else if (idx == size - 1) {
			return this.removelast();
		}

		Node nm1 = getNodeAt(idx - 1);
		Node n = nm1.next;
		Node np1 = n.next;
		int rv = n.data;
		nm1.next = np1;
		size--;
		return rv;
	}

	public void reverseDataiterative() // o(n^2)
	{
		if (size == 1) {
			handlewhensize1();
			return;
		}
		int i = 0;
		int j = size - 1;
		while (i < j) {
			Node n1 = getNodeAt(i);
			Node n2 = getNodeAt(j);
			int temp = n1.data;
			n1.data = n2.data;
			n2.data = temp;
			i++;
			j--;
		}
	}

	public void rversepointerIterative()// o(n)
	{
		Node prev = head;
		Node cur = prev.next;
		while (cur != null) {
			Node ocn = cur.next;
			cur.next = prev;
			prev = cur;
			cur = ocn;
		}
		Node temp = head;
		head = tail;
		tail = temp;
		tail.next = null;

	}

	public void displayrev() // o(n)
	{
		displayrevhelper(head);
	}

	private void displayrevhelper(Node node) {
		if (node == null) {
			return;
		}
		displayrevhelper(node.next);
		System.out.print(node.data + " ");
	}

	public void reversePointerRecursive() {
		reversepointerRecursiveHelper(head);
		Node temp = head;
		head = tail;
		tail = temp;
		tail.next = null;
	}

	private void reversepointerRecursiveHelper(Node node) {
		if (node.next == null) {
			return;
		}
		reversepointerRecursiveHelper(node.next);
		node.next.next = node;
	}

	public class heapmover {
		Node node;
	}

	public void reversedatarecursive() {
		heapmover left = new heapmover();
		left.node = head;
		reversedatarecursivehelper(left, head, 0);
	}

	private void reversedatarecursivehelper(heapmover left, Node node, int floor) {
		if (node == null) {
			return;
		}
		reversedatarecursivehelper(left, node.next, floor + 1);
		if (floor >= size / 2){
			int temp = left.node.data;
			left.node.data = node.data;
			node.data = temp;
		}
		left.node = left.node.next;
	}

	public class heapmover1 {
		Node node;
	}

	public boolean ispalin() {
		heapmover left = new heapmover();
		left.node = head;
		boolean res = ispalinhelper(left, head);
		return res;
	}

	private boolean ispalinhelper(heapmover left, Node right) {
		if (right == null) {
			return true;
		}
		boolean ans = ispalinhelper(left, right.next);
		{
			if (ans == false){
				return false;
			} else {
				if (left.node.data == right.data) {
					left.node = left.node.next;
					return true;
				} else {
					return false;
				}
			}

		}
	}

	public class heapmover2 {
		Node node;
	}

	public void folds() {
		heapmover left = new heapmover();
		left.node = head;
		foldhelper(left, head, 0);
	}

	private void foldhelper(heapmover left, Node right, int floor) {

		if (right == null) {
			return;
		}
		foldhelper(left, right.next, floor + 1);
		if (floor > size / 2) {
			Node save = left.node.next;
			left.node.next = right;
			right.next = save;
			left.node = save;
		} else if (floor == size / 2) {
			tail = right;
			tail.next = null;
		}
	}

	public int kthfromlast(int k) {
		Node fast = head;
		Node slow = head;

		for (int i = 1; i < k; i++) {
			fast = fast.next;
		}
		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		return slow.data;
	}

	public int midelement() {
		Node slow = head;
		Node fast = head;

		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		return slow.data;
	}

	public void reversekth(int k) {
		addlastLL prev = new addlastLL();
		addlastLL curr = new addlastLL();

		while (this.size != 0) {
			for (int i = 0; i < k && this.size!=0; i++) {
				curr.addfirstnode(this.removefirstnode());
			}
			if (prev.size == 0) {
				prev = curr;
			} else {
				prev.tail.next = curr.head;
				prev.tail = curr.tail;
				prev.size = prev.size + curr.size;
			}
			curr = new addlastLL();
		}
		this.head = prev.head;
		this.tail = prev.tail;
		this.size = prev.size;
	}

	private Node removefirstnode(){
		if (size == 0){
			System.out.println("list empty");
			return null;
		}
		else if (size == 1){
			return handleremovalwhensize1();
		}
		
		Node rv = head;
		Node second = head.next;
		head = second;
		rv.next = null;
		size--;
		return rv;
	}

	private Node handleremovalwhensize1() {
		Node rv = head;
		head = tail = null;
		size = 0;
		rv.next = null;
		return rv;
	}

	private void addfirstnode(Node node) {
		if (size == 0) {
			handleaddwhensizeis0(node);
			return;
		}
		node.next = head;
		head = node;
		size++;
	}

	private void handleaddwhensizeis0(Node node) {

		head = node;
		tail = node;
		size++;
	}

	public void removeduplicate() {
		addlastLL cur = new addlastLL();

		while (this.size != 0) {
			Node node = this.removefirstnode1();
			if (cur.size == 0 || cur.tail.data != node.data) {
				cur.addlastnode(node);
			}
		}
		this.head = cur.head;
		this.tail = cur.tail;
		this.size = cur.size;
	}
	public void addlastnode(Node node)// o(1)
	{
		if (this.size == 0) {
			handleaddwhensizeis0(node);
			return;
		}
		tail.next = node;
		tail = node;
		size++;
	}
	public Node removefirstnode1() {
		if (size == 0) {
			System.out.println("list empty");
			return null;
		} else if (size == 1) {
			return handleremovalwhensize1();

		}
		Node rv = head;
		Node second = head.next;
		head = second;
		rv.next = null;
		size--;
		return rv;
	}
	public void oddeven()
	{
		addlastLL odd = new addlastLL();
		addlastLL even = new addlastLL();
		while (this.size != 0) {
			Node node = this.removefirstnode1();
			if (node.data % 2 == 0) {
				even.addlastnode(node);
			} else {
				odd.addlastnode(node);
			}
		}
		this.head = even.size > 0 ? even.head : odd.head;
		this.tail = odd.size > 0 ? odd.tail : even.tail;
		this.size = odd.size + even.size;

		if (even.size > 0) {
			even.tail.next = odd.head;
		}
	}

	public static addlastLL mergesort(addlastLL list) {
		if (list.size == 1) {
			return list;
		}

		addlastLL l1 = new addlastLL();
		addlastLL l2 = new addlastLL();
		Node n1 = list.head;
		for (int i = 0; i < list.size; i++) {

			if (i < list.size / 2) {
				l1.addlast(n1.data);
			} else {
				l2.addlast(n1.data);
			}
			n1 = n1.next;
		}
		addlastLL l4 = mergesort(l1);
		addlastLL l3 = mergesort(l2);

		addlastLL l5 = addlastLL.merge(l4, l3);
		return l5;

	}

	public static addlastLL merge(addlastLL list1, addlastLL list2) {
		addlastLL list = new addlastLL();

		Node l1temp = list1.head;
		Node l2temp = list2.head;

		while (l1temp != null && l2temp != null) {
			if (l1temp.data < l2temp.data) {
				list.addlast(l1temp.data);
				l1temp = l1temp.next;
			} else {
				list.addlast(l2temp.data);
				l2temp = l2temp.next;
			}
		}
		while (l1temp != null) {
			list.addlast(l1temp.data);
			l1temp = l1temp.next;
		}
		while (l2temp != null) {
			list.addlast(l2temp.data);
			l2temp = l2temp.next;
		}
		return list;
	}

	public void removenode(int val) {
		Node prev = head;
		Node cur = head.next;
		for (int i = 0; i < this.size - 1; i++) {
			if (cur.data == val) {
				Node x = cur.next;
				prev.next = x;
				size--;
			}
			prev = prev.next;
			cur = cur.next;
		}
	}

	public void bubblesort() {
	
		if(this.size>1){
		for (int i = 0; i < this.size-1; i++) {
			Node cur  = head.next;
			Node prev = head;
			for(int j=0;j<this.size-1-i;j++)
			{
				if(prev.data > cur.data)
				{
					int temp = cur.data;
					cur.data = prev.data;
					prev.data = temp;
				}
				cur = cur.next;
				prev= prev.next;
			}
		}			
	}
 }
	
		
}