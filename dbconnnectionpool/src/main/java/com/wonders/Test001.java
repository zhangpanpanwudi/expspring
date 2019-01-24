package com.wonders;

import java.sql.Connection;
import java.util.List;
import java.util.Vector;

public class Test001 {

	public static void main(String[] args) {
		ThreadConnection threadConnection = new ThreadConnection();
		for (int i = 1; i < 3; i++) {
			Thread thread = new Thread(threadConnection, "线程i:" + i);
			thread.start();
		}
	}

}

class ThreadConnection implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			Connection connection = ConnectionManager.getConnection();
			System.out.println(Thread.currentThread().getName() + ",connection:" + connection);
            ConnectionManager.realseConnect(connection);
		}
	}

}
