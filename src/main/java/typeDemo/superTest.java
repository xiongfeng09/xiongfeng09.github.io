package typeDemo;

import java.util.*;

/**
 * Created by xiongfeng on 15/11/6.
 */
public class superTest<T extends Number> {
	List<? extends Number> fooa = new ArrayList();
	Number a = fooa.get(1);

	public static <T> void copy(List<? super T > dest, List<? extends T> src) {
		int srcSize = src.size();
		if (srcSize > dest.size())
			throw new IndexOutOfBoundsException("Source does not fit in dest");

		if (srcSize < 10 ||
			(src instanceof RandomAccess && dest instanceof RandomAccess)) {
			for (int i = 0; i < srcSize; i++)
				dest.set(i, src.get(i));
		} else {
			ListIterator<? super T> di = dest.listIterator();
			ListIterator<? extends T> si = src.listIterator();
			for (int i = 0; i < srcSize; i++) {
				di.next();
				di.set(si.next());
			}
		}
	}
}
