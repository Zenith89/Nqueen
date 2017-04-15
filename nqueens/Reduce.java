package nqueens;

import cl.niclabs.skandium.muscles.Merge;

public class Reduce implements Merge<Long, Long> {
	

	@Override
	public Long merge(Long[] arg0) throws Exception {
		long result = 0;
		for (int i = 0; i < arg0.length; i++) {

			result += arg0[i];

		}
		return result;
	}
}
