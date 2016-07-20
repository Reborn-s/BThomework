import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


public class MaxWordsNum{

	/**
	 * @param args
	 * @throws Exception 
	 */

	static HashMap<String,Integer> map= new HashMap<String,Integer>();
	static HashMap<String,Integer> newMap= new HashMap<String,Integer>();
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		sortAllWord(args);
	}
	
	
	public static void addToMap(HashMap<String,Integer> m,String wordString){
		Word word = new Word(wordString);
		String key=word.value;
		int value=word.count;
		
		/*此方法会引起map异常，Exception in thread "main" java.util.ConcurrentModificationException  
		 * 慎用iterator来改变map里value的值。因为一旦iterator指向的集合发生变化，就会报这个错误，
		 * 所以不能在iterator循环里改变map的值
		Iterator keys = map.keySet().terator();
		if(m.size()==0){
			m.put(word.value, word.count);
			newMap.put(word.value, word.count);
		}else{
			while(keys.hasNext()){
				String key1= (String) keys.next();
				if(wordString.equals(key)){
					newMap.put(word.value, new Integer(newMap.get(word.value)+1));
					word.count++;
				}
				else{
				//System.out.println(word.value);
					map.put(word.value, new Integer(1));
					newMap.put(word.value, new Integer(1));
					word.count=1;
				//System.out.println(word.value);
				//System.out.println(word.value);
				}
			}
		}
		*/
		
		if(m.size()==0){
			m.put(key, new Integer(1));
		}else{
			if(m.containsKey(key)){
				m.put(key, new Integer(m.get(key)+1));
			}else{
				m.put(key, new Integer(1));
			}
		}
	}
	
	//改写compare函数，来得到降序序列
	public static ArrayList<Map.Entry<String,Integer>> sortMap(Map map){  
	     List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());  
	     Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {  
	         public int compare(Map.Entry<String, Integer> obj1 , Map.Entry<String, Integer> obj2) {  
	             return obj2.getValue() - obj1.getValue();  
	         }  
	     });  
	      return (ArrayList<Entry<String, Integer>>) entries;  
	}
	
	public static void sortAllWord(String[] arguments) throws Exception{
		
		if(arguments.length<1){
			System.out.println("Please input file directory!");
			System.exit(1);
		}
		//F:\\myeclipse 8.6\\workspace\\bthomework32\\word
		String filePath = arguments[0];
		File dirs = new File(filePath);
		if(dirs.exists()&&dirs.isDirectory()){
			File[] dirContens = dirs.listFiles();
			if(dirContens!=null){
				for(int i=0;i<dirContens.length;i++){
					File file = new File(filePath+"\\"+dirContens[i].getName());
					BufferedReader br=new BufferedReader(new FileReader(file));
					String line=br.readLine();
						while(line!=null){
							addToMap(map,line);
							line=br.readLine();
					}
				}
				ArrayList<Map.Entry<String,Integer>> entries= sortMap(map);
				for(int r= 1;r<11;r++){
					String key=entries.get(r-1).getKey();
					int value=entries.get(r-1).getValue();
					//map.put(key, value);
					System.out.println("出现次数第"+r+"的单词是："+key+",出现"+value+"次。");
				}
			}
		}
	}
}
