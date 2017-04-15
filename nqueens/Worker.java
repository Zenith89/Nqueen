package nqueens;

import cl.niclabs.skandium.muscles.Execute;

public class Worker implements Execute<Interval, Long> {

	public Long execute(Interval arg0) throws Exception {

		GameBorad gb = new GameBorad(arg0.n);
		Result result = new Result();
		for (int cln = arg0.start; cln < arg0.end; cln++)
			gb.enumerate(gb.queens, 0, cln, result);

		return result.solutions;
	}

}