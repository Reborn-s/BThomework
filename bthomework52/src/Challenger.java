import java.util.ArrayList;

public class Challenger {
	private String name;
	private int score;
	private int level;
	boolean isBoss;
	boolean isSysBoss;
	long startTime = System.currentTimeMillis();

	public Challenger(String name, int score, int level) {
		this.name = name;
		this.score = score;
		this.level = level;
		this.isBoss = false;
	}

	public Challenger() {
		// TODO Auto-generated constructor stub
	}

	
	//ÿ��challenger����challenge�Ĺ��ܣ�����ս������ʱ�������synchronized����������߳̾Ͳ��ܽ��и���
	public synchronized boolean challenge(SystemBoss boss, String name,
			int score, int level) {
		ArrayList<SystemBoss> bossChallenged = boss.getSysBossList();
		for (int i = 0; i < bossChallenged.size(); i++) {
			if (level == bossChallenged.get(i).getLevel()
					&& score > bossChallenged.get(i).getScore()) {
				bossChallenged.get(i).update(name, score, level);
				this.startTime = System.currentTimeMillis();
				this.isBoss = true;
				break;
			}
		}
		return this.isBoss;
	}

	public String getChallengerName() {
		return this.name;
	}

	public int getChallengerScore() {
		return this.score;
	}

	public int getChallengerLevel() {
		return this.level;
	}
}
