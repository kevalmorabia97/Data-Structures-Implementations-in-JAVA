
public class DisjointUnionSet{
	//If i is a representative of a set, rank[i] is the height of the tree representing the set
	int[] rank, parent;
	int n;
	
	public DisjointUnionSet(int n){
		this.n=n;
		rank = new int[n];
		parent = new int[n];
		for(int i = 0; i < n; i++)	parent[i] = i;
	}
	
	boolean areDisjoint(int x, int y){
		return findRepresentative(x)!=findRepresentative(y);
	}
	
	int findRepresentative(int x){
		if(parent[x]!=x){
			parent[x] = findRepresentative(parent[x]);
		}
		return parent[x];
	}
	
	void union(int x, int y){
		int xRep = findRepresentative(x), yRep = findRepresentative(y);
		if(xRep==yRep)	return;
		if(rank[xRep] < rank[yRep]){
			parent[xRep] = yRep;
		}else if(rank[yRep] < rank[xRep]){
			parent[yRep] = xRep;
		}else{
			parent[xRep] = yRep;
			rank[yRep]++;
		}
	}
	
	public static void main(String[] args) {
		DisjointUnionSet dus = new DisjointUnionSet(5);
		System.out.println(dus.areDisjoint(1, 2));
		System.out.println(dus.findRepresentative(3));
		dus.union(0, 4);
		System.out.println(dus.areDisjoint(4, 0));
		System.out.println(dus.findRepresentative(4));
		System.out.println(dus.findRepresentative(0));
		dus.union(0, 2);
		System.out.println(dus.areDisjoint(4, 2));
		System.out.println(dus.findRepresentative(2));
		
	}

}
