import org.w3c.dom.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
public class TxtToXml {

	/**
	 * @param args
	 */
	
	//����װ��Server�����serverList��װ��Zone�����zoneList��
	static ArrayList<Server> serverList= new ArrayList<Server>();
	static ArrayList<Zone> zoneList= new ArrayList<Zone>();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		getXmlContent();
		transform();
	}
	
	//��ȡ�ļ�
	public static BufferedReader readFile(String fileName) throws FileNotFoundException{
		File file=new File(fileName);
		BufferedReader br=new BufferedReader(new FileReader(file));
		return br;
	}
	
	//�õ�װ��Server�����serverList��װ��Zone�����zoneList
	public static void getXmlContent() throws Exception{
		
		//��ȡ���ļ�
		BufferedReader zoneBr=readFile("zoneReplaced.config");
		String zoneLine;
		
		//�������ļ���������list������װ������ZoneReplaced�Ķ���
		ArrayList<NewZone> newZoneList= new ArrayList<NewZone>();
		
		//�õ��������ظ�Ԫ�ص�newZoneList,�ڵõ�������ϵĹ����о��Ѿ����˵��˵����Ϊ1��Ԫ��
		while((zoneLine=zoneBr.readLine())!=null){
			String zoneTemp[]=zoneLine.split("\t");
			NewZone newZone= new NewZone(zoneTemp[0],zoneTemp[1],zoneTemp[2]);
			newZone.getNewZoneList(newZoneList, newZone);
		}
		
		//��ȡ���ļ�
		BufferedReader br=readFile("zone.config");
		
		//��ȡ���ļ��õ���ÿһ��
		String line;
		while((line=br.readLine())!=null){
			
			//��tab����Ϊ���ȡ��ÿ������ɵ�����temp
			String temp[]=line.split("\t");
			
			//temp[3]��curPlayerNum,temp[4]��maxPlayerNum�����ҵõ�capacity
			PlayerNum playerNum=new PlayerNum(temp[3], temp[4]);
			String capacity=playerNum.getCapacity(playerNum.curPlayerNum, playerNum.maxPlayerNum);
			
			//��zone��ip�洢��map��,�Ա��ڻ��serverIndex
			ServerMap serverMap= new ServerMap();
			serverMap.key=temp[0];
			serverMap.value=temp[1];
			serverMap.put(temp[0], temp[1]);
			
			//�õ�serverList
			int lastServerIndex=0;                      //serverList�����һ��Ԫ�ص�����
			Server lastServer=new Server();            //ͨ�������õ�list�д洢�Ĵ������һ��λ�õĶ���
			Server server = new Server(temp[1],temp[1],temp[2]);                  //temp[1]��ip,temp[2]��port
			server.getServerList(serverList, newZoneList, server);
			
			//�õ�zoneList
			String serverIndex=null;
			int lastZoneIndex=0; 
			Zone lastZone=new Zone();          //ͨ�������õ�list�д洢�Ĵ������һ��λ�õĶ���
			if(!(line.startsWith("$"))){                                 //����"$"��ͷ����
				Zone zone = new Zone(temp[0],capacity,serverIndex);                           //temp[0]��zone
				zone.getZoneList(zoneList, zone);
                //�õ�serverIndex
				lastZoneIndex=zoneList.size()-1;
				lastZone=zoneList.get(lastZoneIndex);
				serverIndex=serverMap.getServerIndex(serverList,serverMap);
				lastZone.serverIndex=serverIndex;
			}
		}
	}
	
	public static void transform() throws Exception{
		
		//����dom��������µĸ�Ԫ���Լ��ӽڵ㡣
		DocumentBuilderFactory factory=DocumentBuilderFactory .newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document= builder.newDocument();
		
		//�������ڵ�
		Element root= (Element)document.createElement("root");
		
		//����servers�ӽڵ�����ӽڵ�
		Element servers=(Element)document.createElement("servers");
		Iterator itServer= serverList.iterator();
		Server xmlServer= new Server();
		while(itServer.hasNext()){
			xmlServer=(Server) itServer.next();
			Element serverElement=(Element)document.createElement("server");
			serverElement.appendChild(document.createTextNode(xmlServer.newZone+"/"+xmlServer.port));
			servers.appendChild(serverElement);
		}
		
		//����zones�ӽڵ�����ӽڵ�
		Element zones=(Element)document.createElement("zones");
		Iterator itZone= zoneList.iterator();
		Zone xmlZone=new Zone();
		while(itZone.hasNext()){
			xmlZone=(Zone) itZone.next();
			Element zoneElement=(Element)document.createElement("zone");
			zoneElement.setAttribute("name", xmlZone.zoneName);
			zoneElement.setAttribute("serverindex", xmlZone.serverIndex);
			zoneElement.setAttribute("capacity", xmlZone.capacity);
			zones.appendChild(zoneElement);
		}

		//���ڵ�ȫ������root��
		root.appendChild(servers);
		root.appendChild(zones);
		document.appendChild(root);
		OutputFile(document);
	}
		
	public static void OutputFile(Document document) throws Exception{
		//�����xml�ļ�
		TransformerFactory transFactory=TransformerFactory.newInstance();
		Transformer transformer=transFactory.newTransformer();
		DOMSource source=new DOMSource(document);
		File zoneXmlInfo= new File("zoneXmlInfo.xml");
		FileOutputStream os= new FileOutputStream(zoneXmlInfo);
		StreamResult result = new StreamResult(os);
		transformer.transform(source, result);
	}
}
