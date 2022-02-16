package com.jachs.reactor;


/***
 * 
 * @author zhanchaohan
 *
 */
public class ThClass implements Runnable{
	private String name;
	
	public ThClass(String name) {
		super();
		this.name = name;
	}

	@Override
	public void run() {
		Thread.currentThread().setName(name);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
