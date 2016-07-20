import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FileDealer fileDealer = new FileDealer(args[0]);
		int threadNumber = Integer.parseInt(args[1]);
		ExecutorService es = Executors.newFixedThreadPool(threadNumber);
		SingleThread[] threads = new SingleThread[threadNumber];
		try {
			CountDownLatch doneSignals = new CountDownLatch(threadNumber);
			for (int i = 0; i < threadNumber; i++) {
				threads[i] = new SingleThread(doneSignals);
				es.execute(threads[i]);
			}
			doneSignals.await();
			HashMap<String, Integer> map = mergerThreadMap(threads);
			outputMaxWords(map);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			es.shutdown();
		}

	}

	// 把各个子线程得到的map进行合并,最后汇总到主线程的map中
	private static HashMap<String, Integer> mergerThreadMap(
			SingleThread[] threads) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (SingleThread singleThread : threads) {
			HashMap<String, Integer> threadMap = singleThread.getMap();
			for (Map.Entry<String, Integer> entry : threadMap.entrySet()) {
				String threadWord = entry.getKey();
				Integer threadCount = entry.getValue();
				Integer count = map.get(threadWord);

				if (count == null) {
					map.put(threadWord, threadCount);
				} else {
					map.put(threadWord, threadCount + count);
				}
			}
		}
		return map;
	}

	// 重写compare函数，对得到的map进行降序排序
	public static ArrayList<Map.Entry<String, Integer>> sortMap(Map map) {
		List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(
				map.entrySet());
		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> obj1,
					Map.Entry<String, Integer> obj2) {
				return obj2.getValue() - obj1.getValue();
			}
		});
		return (ArrayList<Entry<String, Integer>>) entries;
	}

	// 打印出出现频率最多的10个单词
	private static void outputMaxWords(HashMap<String, Integer> map) {
		ArrayList<Map.Entry<String, Integer>> entries = sortMap(map);
		for (int r = 1; r < 11; r++) {
			String key = entries.get(r - 1).getKey();
			int value = entries.get(r - 1).getValue();
			// map.put(key, value);
			System.out.println("出现次数第" + r + "的单词是：" + key + ",出现" + value
					+ "次。");
		}
	}
}
