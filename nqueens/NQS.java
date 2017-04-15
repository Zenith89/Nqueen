package nqueens;

public class NQS {

	int n;
	public NQS(int n) {
		this.n = n;
	}

	public void execute() {
		GameBorad gb = new GameBorad(n);
		long init = System.currentTimeMillis();
		Result result=new Result();
		gb.enumerate(gb.queens, 0,result);
		System.out.println("NQUEENS-Sequential>>Total # of solutions for Board size=" + n + " is:" + result.solutions + " in "
				+ (System.currentTimeMillis() - init) + "[ms]");

	}

}
