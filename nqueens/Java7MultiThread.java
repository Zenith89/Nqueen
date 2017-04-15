package nqueens;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Java7MultiThread {

	final int THREADS;
	final int BOARD;

	public Java7MultiThread(int tHREADS, int bOARD) {
		THREADS = tHREADS;
		BOARD = bOARD;
	}

	public void execute() throws Exception {

		GameBorad gameBoard = new GameBorad(BOARD);
		Interval[] bounds = gameBoard.splitGameBoard(THREADS);
		ExecutorService threadpool = Executors.newFixedThreadPool(THREADS);
		List<Future<Long>> list = new ArrayList<Future<Long>>();
		
		for (int i = 0; i < THREADS; i++) {
			Callable<Long> worker = new WorkerThreadPool(BOARD, bounds[i]);
			Future<Long> submit = threadpool.submit(worker);
			list.add(submit);
		}
		
		long init = System.currentTimeMillis();
		long result = 0;
		for (Future<Long> future : list) {
			try {
				result += future.get();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

		}
		System.out.println("NQUEENS-Java7-Thread>>Total # of solutions is " + result + " Size=" + BOARD
				+ " with Processor:" + THREADS + " in " + (System.currentTimeMillis() - init) + "[ms]");

		threadpool.shutdown();

		System.exit(0);
	}
}
