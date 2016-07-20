import java.util.ArrayList;
public class Server {
	public String ip;
	public String port;
	
	public Server(String ipString,String portString){
		this.ip=ipString;
		this.port=portString;
	}
	public Server() {
		// TODO Auto-generated constructor stub
	}
	
	//得到serverList
	public void getServerList(ArrayList<Server> a, Server s){
		String listIp;
		String listPort;
		boolean flag = false;             //记录s是否和list里面的元素重复的标志
		if(a.size()==0){                  //如果list大小为0，则直接添加元素
			a.add(s);
		}else{                                       //如果list大小不为0，则过滤掉重复元素再添加
			for(int i=0;i<a.size();i++){
				listIp=a.get(i).ip;
				listPort=a.get(i).port;
				if(!listIp.equals(s.ip)||!listPort.equals(s.port)){
					flag=true;
				}
				else{
					flag=false;
				}
			}
			if(flag==true){
				a.add(s);
			}
		}
	}

}


	
