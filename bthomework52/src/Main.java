import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	/**
	 * @param args
	 */
	static SystemBoss sysBoss = new SystemBoss();

	static ArrayList<Challenger> challengerList = new ArrayList<Challenger>();
	static int index = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		startChallenge();
	}

	public static void startChallenge() throws IOException {
		sysBoss.initialSysBoss();

		System.out
				.println("Please input some challegers,including their name,score and level!Please end with $");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String line;
		while ((line = br.readLine()) != null) {
			if (line.startsWith("$")) {
				break;
			}
			String temp[] = line.split("\\s");
			Challenger challenger = new Challenger(temp[0], Integer
					.parseInt(temp[1]), Integer.parseInt(temp[2]));
			challengerList.add(challenger);
		}
		//创建线程池，各个线程开始工作
		ExecutorService es = Executors.newFixedThreadPool(3);
		SinleThread[] singleThread = new SinleThread[3];
		try {
			for (int i = 0; i < 3; i++) {
				singleThread[i] = new SinleThread();
				es.execute(singleThread[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}

	}

	//每个线程可以得到自己的challenger，但互必须不干扰
	public synchronized static Challenger getChallenger(
			ArrayList<Challenger> list) {
		Challenger c = null;
		if (index < list.size()) {
			c = list.get(index);
			index++;
		}
		return c;
	}
}
