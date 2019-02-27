package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;

public class TemperatureDevice {
	
	private static final int COUNT = 10;
	
	public static void main(String[] args) {
		
		TemperatureSensor sn = new TemperatureSensor();
		Client client = new Client("DisplayDevice" + Math.floor(Math.random()*51) , Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		client.subscribe(Common.TEMPTOPIC);
		
		for (int i = 0; i<COUNT;i++) {
			
			int temp = sn.read();

			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			client.publish(Common.TEMPTOPIC, Integer.toString(temp));

		}
		client.unsubscribe(Common.TEMPTOPIC);
		client.disconnect();
		
		System.out.println("Temperature device stopping ... ");
		
		
	}
}
