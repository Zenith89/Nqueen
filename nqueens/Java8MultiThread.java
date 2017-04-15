package nqueens;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Java8MultiThread {
	final int THREADS;
	final int BOARD;

	public Java8MultiThread(int tHREADS, int bOARD) {
		THREADS = tHREADS;
		BOARD = bOARD;
	}

	public void execute() throws Exception {

		GameBorad gb = new GameBorad(BOARD);
		Interval[] bounds = gb.splitGameBoard(THREADS);
		ExecutorService threadpool = Executors.newFixedThreadPool(THREADS);
		List<Interval> bound = Arrays.asList(bounds);

		List<CompletableFuture<Long>> numSolution = bound.stream()
				.map(boundIt -> CompletableFuture.supplyAsync(() -> calculateSoultions(boundIt), threadpool))
				.collect(Collectors.<CompletableFuture<Long>>toList());
		
		long init = System.currentTimeMillis();
		List<Long> list = numSolution.stream().map(CompletableFuture::join).collect(Collectors.toList());
		long result = list.stream().reduce(0L, (a, b) -> a + b);

		System.out.println("NQUEENS-Java8-Thread>>Total # of solutions is " + result + " Size=" + BOARD
				+ " with Processor:" + THREADS + " in " + (System.currentTimeMillis() - init) + "[ms]");

		threadpool.shutdown();

		System.exit(0);
	}

	long calculateSoultions(Interval bound) {
		Result result = new Result();
		GameBorad gbc = new GameBorad(BOARD);
		for (int cln = bound.start; cln < bound.end; cln++)
			gbc.enumerate(gbc.queens, 0, cln, result);
		return result.solutions;
	}
}
