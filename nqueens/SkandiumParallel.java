
package nqueens;

import java.util.concurrent.Future;

import cl.niclabs.skandium.Skandium;
import cl.niclabs.skandium.Stream;
import cl.niclabs.skandium.skeletons.Map;
import cl.niclabs.skandium.skeletons.Skeleton;

/**
 * @author tsinu
 *
 */

public class SkandiumParallel {
	final int THREADS;
	final int BOARD;

	public SkandiumParallel(int THREADS, int BOARD) {
		this.THREADS = THREADS;
		this.BOARD = BOARD;
	}

	public void execute() throws Exception {

		GameBorad gameBoard = new GameBorad(BOARD);
		Skandium skandium = new Skandium(THREADS);
		Skeleton<GameBorad, Long> map = new Map<GameBorad, Long>(new Splitter(THREADS), new Worker(), new Reduce());
		Stream<GameBorad, Long> stream = skandium.newStream(map);
		long init = System.currentTimeMillis();
		Future<Long> future = stream.input(gameBoard);
		Long result = future.get();
		System.out.println("NQUEENS-Skandium>>Total # of solutions is:" + result + " Size=" + BOARD + " with Processor:"
				+ THREADS + " in " + (System.currentTimeMillis() - init) + "[ms]");
		skandium.shutdown();
		System.exit(0);

	}

}
