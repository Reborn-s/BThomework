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
	
	//�õ�serverList
	public void getServerList(ArrayList<Server> a, Server s){
		String listIp;
		String listPort;
		boolean flag = false;             //��¼s�Ƿ��list�����Ԫ���ظ��ı�־
		if(a.size()==0){                  //���list��СΪ0����ֱ�����Ԫ��
			a.add(s);
		}else{                                       //���list��С��Ϊ0������˵��ظ�Ԫ�������
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


	
