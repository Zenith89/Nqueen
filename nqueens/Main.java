package nqueens;

public class Main {

	public static void main(String[] args) throws Exception {

		int THREADS;
		int BOARD; // Size board of the board
		int VERSION;
		// VERSION=1 for Sequential version
		// VERSION=2 for Skandium version
		// VERSION=3 for Java-7 Thread version
		// VERSION=4 for Java-8 Thread version

		if (args.length == 0) {
			errorMessage();
		}
		if (args[0] == null || args[1] == null) {
			errorMessage();

		}

		VERSION = Integer.parseInt(args[0]);
		THREADS = Integer.parseInt(args[1]);

		if (VERSION == 1) {

			BOARD = Integer.parseInt(args[1]);
			NQS nqs = new NQS(BOARD);
			nqs.execute();
		}

		else if (VERSION == 2) {
			if (args.length != 3) {
				errorMessage();
			}
			BOARD = Integer.parseInt(args[2]);
			THREADS = THREADS > BOARD ? BOARD : THREADS;
			SkandiumParallel nqp = new SkandiumParallel(THREADS, BOARD);
			nqp.execute();
		}

		else if (VERSION == 3) {
			if (args.length != 3) {
				errorMessage();
			}
			BOARD = Integer.parseInt(args[2]);
			THREADS = THREADS > BOARD ? BOARD : THREADS;
			Java7MultiThread java7MultiThread = new Java7MultiThread(THREADS, BOARD);
			java7MultiThread.execute();
		}
		else if (VERSION == 4) {
			if (args.length != 3) {
				errorMessage();
			}
			BOARD = Integer.parseInt(args[2]);
			THREADS = THREADS > BOARD ? BOARD : THREADS;
			Java8MultiThread java8MultiThread = new Java8MultiThread(THREADS, BOARD);
			java8MultiThread.execute();
						
		}

	}

	private static void errorMessage() {
		System.out.println("For parellel version Please  type:  VersionNumber NumberOfProcesor Boardsize ");
		System.out.println("For Sequental version Please type:  VersionNumber  Boardsize ");
		System.exit(0);

	}
}
