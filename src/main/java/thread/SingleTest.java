package thread;

/**
 * Created by xiongfeng on 15/12/10.
 */
public class SingleTest {
	private Singleton s;

	public Singleton getSingle() {
		if (s == null) {
			synchronized(this) {
				System.out.println("in synchronized");
				if (s ==  null) {
					s = new Singleton();
				}
			}
			return s;
		}

		return s;
	}
}

class SingleStaticTest {
	public static Singleton getSingle() {
		return Hoder.s;
	}

	private static class Hoder {
		static {
			System.out.print("in init Hoder");
		}

		public static final Singleton s = new Singleton();
	}
}


class Singleton {
	public static void main(String[] s) {
		(new SingleTest()).getSingle();
		SingleStaticTest.getSingle();
	}
}
