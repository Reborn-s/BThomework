import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServerMap extends HashMap {
	
	// key是zone,value是ip
	public HashMap serverMap; 
	public String key;
	public String value;

	//serverList是一个包含不重复的所有server的List
	//将value对应到此List,从而根据对应的顺序来得到serverIndex.
	public String getServerIndex(ArrayList<Server> a, HashMap map) {
		String serverIndex = null;
		Server s=new Server();
		//用set來得到map里的key,从而可以获取和操纵key
		Set set = map.keySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			key=(String) it.next();
			for (int i = 0; i < a.size(); i++) {
				s=a.get(i);
				if (s.ip .equals( map.get(key))) {
					serverIndex = Integer.toString(i + 1);
				}
			}
		}
		return serverIndex;
	}

}
