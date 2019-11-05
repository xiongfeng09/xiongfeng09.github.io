package jvm;

/**
 * Created by xiongfeng on 15/10/30.
 */
public class ClassLoaderTest {
	static {
		System.out.println("in class loader test");
	}
	public static void main(String[] args) {
		//使用new 关键字实例化对象时
//		initNewTest();

		//获取静态变量(非常量-final)
//		staticGetTest();

		//设置静态变量
//		staticSetTest();

		//访问静态方法
//		staticMethodTest();

		//reflect 调用
//		reflectTest();

		arrayTest();
	}

	static void initNewTest() {
		new SubClass();
	}


	static void staticGetTest() {
		System.out.println(SubClass.a);
	}

	static void staticSetTest() {
		SubClass.a = 2;
		System.out.println(SubClass.a);
	}

	static void staticMethodTest() {
		System.out.println(SubClass.getName());
	}

	static void reflectTest() {
		try {
			Class c = Class.forName("jvm.ClassLoaderTest");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static void arrayTest() {
		SuperClass[] s = {new SuperClass()};
		System.out.println(s.getClass().getName());
	}

	static void classLoaderTest() {

	}

	void runnableTest() {
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println(Thread.currentThread());
					Long n = (long) (Math.random() * 5000L);
					System.out.println(n);
					Thread.sleep(n);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				new initClassTest();
			}
		};

		Thread t1 = new Thread(r);
		Thread t2 = new Thread(r);
		t1.start();
		t2.start();
	}
}

class SuperClass {
	static int a = 1;
	static final int b = 2;
	static {
		System.out.println("in super class static");
	}

	static String getName() {
		return "xiongfeng";
	}

}

class SubClass extends SuperClass {
	static {
		System.out.println("in sub class static");
	}
}

class initClassTest {
	static {
		System.out.print(Thread.currentThread() + "init class test");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}


