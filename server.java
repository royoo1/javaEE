package socket;
import java.net.*;
import java.io.*;

public class server {
	
 public static void main(String args[]){
	 
	 try{
		 ServerSocket server=null;
		 
		 try{
			 
			 server=new ServerSocket(3333);
			 //����ServerSocket�ڶ˿�3333�����ͻ�����
			 
		 }catch(Exception e){
			 System.out.println("can not listen to:"+e);
			 //������ӡ������Ϣ
		 }
	Socket sok=null;
    while(true){
      try{
		   System.out.println("start");
			sok=server.accept();
			//ʹ��accept()�����ȴ��ͻ������пͻ�������������һ��Socket���󣬲�����ִ��
			if(sok!=null){
				threadC threa=new threadC(sok);
			    threa.start();
			    sok=null;
			}
				
		 }catch(Exception e){
			 System.out.println("Error"+e);
		 }
		 
		 }
	 
 }catch(Exception e){
	 System.out.println("Error"+e);
	 //������ӡ������Ϣ
 }
 }


}

class threadC extends Thread{
	
	Socket socket=null;
	public threadC(Socket st){
		socket=st;
	}
	
	
	public void run(){
		
		try{
			 String line;
			 BufferedReader is =new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 //��Socket����õ�����������������Ӧ��BufferedReader����
			 
			 PrintWriter os=new PrintWriter(socket.getOutputStream());
			 //��Socket����õ��������������PrintWriter����
			
			 line=is.readLine();
			 String linef="";
			 
			 char[] arry=line.toCharArray();
			 for(int i=arry.length-1;i>0;i--){
				 linef+=arry[i];
			 }
			 
			 os.println(linef);
			 os.flush();
				
			 
			 os.close();//�ر�Socket�����
			 is.close();//�ر�Socket������
				
		}catch(IOException e){
					
				}
	}
}

















