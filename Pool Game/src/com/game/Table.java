package com.game;
import java.util.LinkedList;
import java.util.List;
/**
* @author Shiyuan Wen ID 37727273
* @version 1.0  2020.5.22
* @since JDK 14
*/


/**
 * Make a pool table and the friction on table 
 */

public class Table {
	
	private double x;
	private double y;
	private double width;
	private double height;
	private double friction;
	private List<Updateable> gameobjs = new LinkedList<Updateable>();
	public Table(double x, double y, double width, double height,double friction) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.friction=friction;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getWidth() {
		return width;
	} 
	public double getHeight() {
		return height;
	}
	public double getFriction() {
		return friction;
	}
	public List<Updateable> getGameobjs() {
		return gameobjs;
	}
	public void setGameobjs(List<Updateable> gameobjs) {
		this.gameobjs = gameobjs;
	}
	
	
	
	
	
}
