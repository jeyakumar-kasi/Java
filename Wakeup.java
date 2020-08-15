
import java.awt.*;
import java.util.*;

public class Wakeup {
	// Methods
	static void delay(Robot r, long interval) {
		synchronized (r) {
			try {
				Thread.sleep(interval + 1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
	
	// Main method
	public static void main(String[] args) throws Exception {
		int x = 0;
		int y = 0;
		long interval = 1000 * 60 * 5; // 5 Mins
		Robot robot = new Robot();
		//Random random = new Random();
		
		// Get mouse pointer location
		PointerInfo mouseInfo = MouseInfo.getPointerInfo();
		System.out.println("Mouse Device Name: " + mouseInfo.getDevice().toString());
		
		while (true) {
			delay(robot, interval);
			//int x = random.nextInt() % 640;
			//int y = random.nextInt() % 480;
			
			mouseInfo = MouseInfo.getPointerInfo();
			Point point = mouseInfo.getLocation();
			
			// Check the user is used the system 5 mins back or not?
			if (x != 0 && (int) point.getX() != x) {
				// System is using by the user. Don't do anything!.
				x = (int) point.getX();
				continue;
			}
			
			x = (int) point.getX();
			y = 360; //(int) point.getY(); // Middle
			
			if (x >= 640) {
				// reached the right end, set in reverse
				x -= 1;
			} else {
				x += 1;
			}
			
			// Move the mouse cursor
			robot.mouseMove(x, y);
			System.out.println("["+ new java.text.SimpleDateFormat("Y-MM-dd HH:mm:ss").format(new Date()) +"] Position: (" + point.getX() + ", " + point.getY() + ")");
		}
	}
	
	
}
