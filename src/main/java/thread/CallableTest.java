package thread;

import java.util.concurrent.*;

/**
 * Created by xiongfeng on 15/10/30.
 */
public class CallableTest {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();

		Task task = new Task();

		FutureTask<Integer> t = new FutureTask(task);

		//use future and callable
		Future<Integer> f = executorService.submit(task);

		//use futureTask and callable
		executorService.submit(t);

		executorService.shutdown();

		System.out.println(f.isDone());


		try {
			Thread.sleep(3000);
			System.out.println(f.get());
			System.out.println(t.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}


	}
}

class Task implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("in call");
		Thread.sleep(5000);

		int result = 0;
		for (int i = 0; i < 50; i++) {
			System.out.println("in" + i);
			result += i;
		}
		return result;
	}
}
