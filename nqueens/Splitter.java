package nqueens;

import cl.niclabs.skandium.muscles.Split;

public class Splitter implements Split<GameBorad, Interval> {
	final int nw;
	long init = System.currentTimeMillis();

	public Splitter(int nw) {
		this.nw = nw;

	}

	@Override
	public Interval[] split(GameBorad arg0) throws Exception {
		Interval[] interval = null;
		interval = arg0.splitGameBoard(nw);

		return interval;
	}

}
