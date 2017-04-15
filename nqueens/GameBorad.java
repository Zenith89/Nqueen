package nqueens;

public class GameBorad {
	final public int[] queens;
	final public int n; // game board width and height

	public GameBorad(int n) {
		this.n = n;
		queens = new int[n];

	}

	/***************************************************************************
	 * for PARALLEL VERSION try all permutations using backtracking
	 ***************************************************************************/

	public void enumerate(int[] q, int row, int cln, Result result) {
		boolean check = false;
		int n = this.n;
		if (row == n) {
			result.solutions++;
			check = true;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (row == 0 && !check) {
				i = cln;
				check = true;
			} else if (row == 0 && check)
				return;
			q[row] = i;
			if (isConsistent(q, row))
				enumerate(q, row + 1, n, result);
		}
		return;
	}

	/***********************************************************************
	 * for SEQUENTIAL VERSION Try all permutations using backtracking
	 **********************************************************************/

	public void enumerate(int[] q, int row, Result result) {
		int n = q.length;
		if (row == n)
			result.solutions++;

		else {
			for (int i = 0; i < n; i++) {
				q[row] = i;
				if (isConsistent(q, row))
					enumerate(q, row + 1, result);
			}
		}
	}

	/*********************************************************************
	 * This method check the place  either it is safe or not 
	 *****************************************************************/

	private boolean isConsistent(int[] q, int n) {
		for (int i = 0; i < n; i++) {
			if (q[i] == q[n])
				return false; // same column

			if ((q[i] - q[n]) == (n - i))
				return false;// same major diagonal

			if ((q[n] - q[i]) == (n - i))
				return false;// same minor diagonal

		}
		return true;
	}

	/*********************************************************************
	 * This method give an intervals by Splitting the Game board column wise
	 *****************************************************************/
	public Interval[] splitGameBoard(int N_THREADS) {

		int n = this.n;
		int step = n / (N_THREADS);
		int extra = n % N_THREADS;
		int start = 0;
		int chunk = 0;
		Interval[] bounds = new Interval[N_THREADS];
		for (int j = 0; j < N_THREADS; j++) {
			start = start + chunk;
			chunk = step + (extra-- > 0 ? 1 : 0);
			bounds[j] = new Interval(start, start + chunk, n);
		}
		return bounds;
	}

}
