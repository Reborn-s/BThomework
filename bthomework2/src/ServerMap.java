import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ServerMap extends HashMap {
	
	// key��zone,value��ip
	public HashMap serverMap; 
	public String key;
	public String value;

	//serverList��һ���������ظ�������server��List
	//��value��Ӧ����List,�Ӷ����ݶ�Ӧ��˳�����õ�serverIndex.
	public String getServerIndex(ArrayList<Server> a, HashMap map) {
		String serverIndex = null;
		Server s=new Server();
		//��set��õ�map���key,�Ӷ����Ի�ȡ�Ͳ���key
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
