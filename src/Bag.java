import java.util.ArrayList;

public class Bag {
	private static ArrayList<String> bag;
	
	public Bag(){
		bag = new ArrayList<String>();
	}
	

	
	
	public void add(String a) {
		bag.add(a);
	}

	public int getWords() {
		return bag.size();
	}

	public int getUniqueWords() {
		ArrayList<String> unique = new ArrayList<String>();
		for (int i = 0; i < bag.size(); i++) {
			if (!unique.contains(bag.get(i))) {
				unique.add(bag.get(i));
			}
		}
		return unique.size();
	}

	public ArrayList<String> getUnique() {
		ArrayList<String> unique = new ArrayList<String>();
		for (int i = 0; i < bag.size(); i++) {
			if (!unique.contains(bag.get(i))) {
				unique.add(bag.get(i));
			}
		}
		return unique;
	}

	public int getNumOccurances(String a) {
		int c = 0;
		for (int i = 0; i < bag.size(); i++) {
			if (bag.get(i).equals(a)) {
				c++;
			}
		}
		return c;
	}

	public String getMostFrequent() {
		ArrayList<String> u = getUnique();
		String m = u.get(0);
		for (int i = 0; i < u.size(); i++) {
			if (getNumOccurances(u.get(i)) > getNumOccurances(m)) {
				m = u.get(i);
			}
		}
		return m;
	}

	public int[] getFrequencies() {
		ArrayList<String> u = getUnique();
		int[] r = new int[u.size()];
		for (int i = 0; i < u.size(); i++) {
			r[i] = getNumOccurances(u.get(i));
		}
		return r;
	}
	
	public static boolean ordered(int[] a){
		for(int i = 0; i < a.length - 1; i ++){
			if(a[i+1] > a[i])
				return false;
		}
		return true;
	}

	public static void orderArray(int[] a) {
		while (ordered(a)) {
			for (int i = 0; i < a.length - 1; i++) {
				if (a[i] < a[i + 1]) {
					a[i] += a[i + 1];
					a[i + 1] = a[i] - a[i + 1];
					a[i] = a[i] - a[i + 1];
				}
			}
		}
	}
	
	public int indexOf(int a, int[] b){
		for(int i = 0; i < b.length - 1; i ++){
			if(a == b[i])
				return i;
		}
		return -1;
	}

	public String[] getNMostFrequent(int n) {
		String[] s = new String[n];
		int[] f = getFrequencies();
		orderArray(f);
		for (int i = 0; i < n; i++) {
			s[i] = getUnique().get(indexOf(f[i],getFrequencies()));
		}
		return s;
	}
	public String getString(){
		String a = "";
		for(int i = 0 ; i < bag.size(); i++){
			a = a + bag.get(i);
		}
		return a;
	}
	
	public static int size(){
		return bag.size();
	}
}
