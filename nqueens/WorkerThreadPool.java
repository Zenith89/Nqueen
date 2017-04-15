package nqueens;

import java.util.concurrent.Callable;

public class WorkerThreadPool implements Callable<Long> {

	final int n;
	final Interval bound;

	public WorkerThreadPool(int n, Interval bound) {

		this.n = n;
		this.bound = bound;

	}

	@Override
	public Long call() throws Exception {
		GameBorad gb = new GameBorad(n);
		Result result = new Result();
		for (int cln = bound.start; cln < bound.end; cln++)
			gb.enumerate(gb.queens, 0, cln, result);
		return result.solutions;
	}

}
