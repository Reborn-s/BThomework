import java.util.List;

public class SinleThread extends Thread {

	//ÿ���̻߳��һ����challenger,������challenger��ս���������ҿ��Բ鿴���������б�
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
