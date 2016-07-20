import java.util.List;

public class SinleThread extends Thread {

	//每个线程获得一定的challenger,并且让challenger挑战擂主，并且可以查看所有擂主列表
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			Challenger ch = Main.getChallenger(Main.challengerList);
			if (ch == null) {
				break;
			}
			ch.challenge(Main.sysBoss, ch.getChallengerName(), ch
					.getChallengerScore(), ch.getChallengerLevel());
			List<String> bossList = Main.sysBoss.getBossList();
			for (int i = 0; i < bossList.size(); i++) {
				System.out.println(bossList.get(i));
			}
		}

	}

}
