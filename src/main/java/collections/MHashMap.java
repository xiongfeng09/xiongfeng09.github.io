package collections;

/**
 * Created by xiongfeng on 15/11/6.
 */
public class MHashMap<K, V> extends Object {
	final int length = 137;
	int num = 0;
	Entry<K, V>[] table = new Entry[length];

	static class Entry<K, V> extends Object {
		K k;
		V v;
		Entry next;

		Entry(K k, V v) {
			this.k = k;
			this.v = v;
		}

		void addEntry(Entry<K, V> e) {
			for(Entry<K, V> i = this; i != null; i = i.next) {
				if (i.k == e.k) {
					i.v = e.v;
					break;
				}

				if (i.next == null) {

				}
			}
		}

		Entry<K, V> getEntry(K k) {
			for (Entry<K, V> i = this; i != null; i = i.next) {
				if (i.k == k) {
					return i;
				}
			}
			return null;
		}

		@Override public String toString() {
			return "k=" + k + ",V=" + v;
		}
	}

	MHashMap<K, V> put(K k, V v) {
		int index = k.hashCode() / length;

		if (table[index] == null) {
			table[index] = new Entry(k, v);
			num ++;
		} else {
			table[index].addEntry(new Entry(k, v));
		}

		return this;
	}

	V get(K k) {
		int index = k.hashCode() / length;
		Entry<K, V> e = table[index];
		if (e == null) {
			return null;
		} else {
			return e.getEntry(k).v;
		}
	}

	@Override public String toString() {
		String s = "";
		for (int i =0; i < num; i ++) {
			s += table[i].toString();
		}
		return s;
	}

	public static void main(String[] args) {
		MHashMap<String, String> h = new MHashMap();
		System.out.println(h.put("a", "b"));
		System.out.println(h.get("a"));
	}
}
