import java.util.Arrays;

public class Heap {
	int[] a;
	int size;
	public Heap(int[] a){
		this.a = new int[100];
		size=a.length;
		for(int i = 0; i<size; i++)
			this.a[i] = a[i];
		buildMaxHeap();
		//for(int i = 0; i<a.length; i++)	add(a[i]);
	}
	
	int getLeftChildIndex(int parentIndex){return 2*parentIndex + 1;}
	int getRightChildIndex(int parentIndex){return 2*parentIndex + 2;}
	int getParentIndex(int childIndex){return (childIndex-1)/2;}
	
	boolean hasLeftChild(int index){return getLeftChildIndex(index)<size;}
	boolean hasRightChild(int index){return getRightChildIndex(index)<size;}
	boolean hasParent(int index){return getParentIndex(index)>=0;}
	
	int leftChildValue(int index){return a[getLeftChildIndex(index)];}
	int rightChildValue(int index){return a[getRightChildIndex(index)];}
	int parentValue(int index){return a[getParentIndex(index)];}
	
	int peek(){//look at the top value
		if(size==0)	throw new IllegalStateException();
		return a[0];
	}
	
	int removeTop(){//remove top
		if(size==0)	throw new IllegalStateException();
		int item = a[0];
		a[0] = a[size-1];
		size--;
		heapifyDown2(0);
		//System.out.println(item+" removed");
		//printHeap();
		return item;
	}
	
	void add(int item){
		a[size] = item;
		size++; 
		heapifyUp2(size-1);
		//System.out.println(item+" added");
		//printHeap();
	}
	
	void heapifyUp1(int index){//iterative
		while(hasParent(index) && parentValue(index) < a[index]){
			SWAP(getParentIndex(index),index);
			index = getParentIndex(index);
		}
	}
	void heapifyUp2(int index){//recursive
		if(hasParent(index) && parentValue(index) < a[index]){
			SWAP(getParentIndex(index),index);
			heapifyUp2(getParentIndex(index));
		}
	}
	
	void heapifyDown1(int index){//iterative
		while(hasLeftChild(index)){
			int largerChildIndex = getLeftChildIndex(index);
			if(hasRightChild(index) && rightChildValue(index)>leftChildValue(index))
				largerChildIndex = getRightChildIndex(index);
			
			if(a[index]>a[largerChildIndex]){
				break;
			}else
				SWAP(index, largerChildIndex);
			
			index = largerChildIndex;
		}
	}
	void heapifyDown2(int index){//recursive
		if(!hasLeftChild(index)) 	return;
		int largerChildIndex = getLeftChildIndex(index);
		if(hasRightChild(index) && rightChildValue(index)>leftChildValue(index))
			largerChildIndex = getRightChildIndex(index);
		
		if(a[index]<a[largerChildIndex]){
			SWAP(index, largerChildIndex);
			heapifyDown2(largerChildIndex);
		}
	}
	
	void buildMaxHeap(){//max a max heap from unordered array
		for(int i=size/2; i>=0; i--)
			heapifyDown2(i);	
	}
	
	//assuming that trees rooted at left and right of index are maxHeaps
	void maxHeapify(int index){
		if(!hasLeftChild(index)) 	return;
		int largerChildIndex = getLeftChildIndex(index);
		if(hasRightChild(index) && rightChildValue(index)>leftChildValue(index)){
			largerChildIndex = getRightChildIndex(index);
		}
		if(a[index]<a[largerChildIndex]){
			SWAP(index, largerChildIndex);
			maxHeapify(largerChildIndex);
		}
	}

	static int[] heapSort1(int[] a){
		Heap newH = new Heap(a);
		int[] b = new int[a.length];
		int bIndex = 0;
		while(newH.size>0)
			b[bIndex++] = newH.removeTop();
		return b;
	}
	static int[] heapSort2(int[] a){//in-place sorting
		Heap newH = new Heap(a);
		for(int i = newH.size-1; i>0; i--){
			newH.SWAP(0,i);
			newH.size--;
			newH.maxHeapify(0);
		}
		return newH.a;
	}
	
	void SWAP(int l, int r){
		int temp = a[l];
		a[l]=a[r];
		a[r]=temp;
	}
	
	void printHeap(){
		for(int i = 0; i<size; i++)
			System.out.print(a[i]+" ");
		System.out.println("\n");
	}
	
	public static void main(String[] args) {
		int[] a = new int[10];//max heap
		a[0] = 2;	a[1] = 5;	a[2] = 10;	a[3] = 8;	a[4] = 7;
		a[5] = 9;	a[6] = 3;	a[7] = 16;	a[8] = 4;	a[9] = 1;
		
		Heap h = new Heap(a);
		h.printHeap();
		h.removeTop();
		h.printHeap();
		h.add(6);
		h.printHeap();
		
		int[] b = heapSort1(new int[]{15,6,0,4,1,75,9,20,0,1});
		System.out.println(Arrays.toString(b));
	}
}
