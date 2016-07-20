import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class FileDealer {

	private static String filePath;
	private static File[] fileList;
	private static int index;

	public FileDealer(String path) {
		this.filePath = path;
		File dirs = new File(path);
		this.fileList = dirs.listFiles();
		this.index = 0;
	}

	//每个线程要获取文件，获取的时候应该为synchronized，这样它获取的时候别的线程只能等待，不会造成干扰
	public synchronized static File getFile() {
		File file = null;
		if (index < fileList.length) {
			file = new File(filePath + "\\" + fileList[index].getName());
			index++;
		}
		return file;
	}

	//每个线程都有自己的map，将获得的文件内容加到自己的map中去
	public static void addToMap(File file, HashMap<String, Integer> map)
			throws IOException {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String word;
			while ((word = br.readLine()) != null) {
				Integer count = map.get(word);
				if (null == count) {
					map.put(word, 1);
				} else {
					map.put(word, count + 1);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				br.close();
			}
		}

	}
}
