import java.io.File;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class SingleThread implements Runnable {

	private HashMap<String, Integer> map = new HashMap<String, Integer>();
	private CountDownLatch doneSignals;

	public SingleThread(CountDownLatch doneSignals) {
		this.doneSignals = doneSignals;

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				File file = FileDealer.getFile();
				if (file == null) {
					break;
				}
				FileDealer.addToMap(file, map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doneSignals.countDown();
		}
	}

	public HashMap<String, Integer> getMap() {
		return map;
	}

}
