import java.util.ArrayList;
import java.util.List;

public class SystemBoss {
	private String name;
	private int score;
	private int level;

	private static ArrayList<SystemBoss> sysBossList = new ArrayList<SystemBoss>();

	
	//��ʼ��ϵͳ����
	public void initialSysBoss() {
		for (int i = 0; i < 10; i++) {
			SystemBoss sysBoss = new SystemBoss();
			sysBoss.name = "SystemBoss" + (i + 1);
			sysBoss.score = (i + 1) * 100;
			sysBoss.level = i + 1;
			this.sysBossList.add(sysBoss);
		}
	}

	
	//����������
	public void update(String name, int score, int level) {
		this.name = name;
		this.score = score;
		this.level = level;
	}

	//�鿴�����б���ʱ������synchronized
	public synchronized List<String> getBossList() {
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < this.sysBossList.size(); i++) {
			String bossString = sysBossList.get(i).name + "  "
					+ sysBossList.get(i).level + "  "
					+ sysBossList.get(i).score;
			result.add(bossString);
		}
		return result;
	}

	public ArrayList<SystemBoss> getSysBossList() {
		return this.sysBossList;
	}

	public int getLevel() {
		return this.level;
	}

	public int getScore() {
		return this.score;
	}

}
