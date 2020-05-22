package com.game;
import java.util.Iterator;

import com.render.Ball;

/**
* @author Shiyuan Wen ID 37727273
* @version 1.0  2020.5.22
* @since JDK 14
*/


/**
 * Set the range of table that balls can run
 */

public class GameObjectBall extends Ball implements Updateable {
	
	public static final int RADIUS_BALL=10;
	private double speed_x=0;
	private double speed_y=0;
	private Table table=null;
	private GameObjectBall collised=null;
	private boolean deleted=false;

	public GameObjectBall(Table table,double x, double y, String col) {
		super(x, y, RADIUS_BALL*2, col,5);
		this.table=table;
	}

	@Override
	public void update(double dt) {
		
		
		//System.out.println("speed_x:"+speed_x+",speed_y:"+speed_y);
		this.move(speed_x*dt, speed_y*dt);
		checkCollisionTable();
		updateBallCollision();
		updateFriction();
		
		
	}
	
	public void setCollisedBall(GameObjectBall colbal)
	{
		collised=colbal;
	}
	
	public void SetSpeed(double sx,double sy)
	{
		speed_x=sx;
		speed_y=sy;
	}
	
	public void SetSpeedX(double sx)
	{
		speed_x=sx;
		
	}
	
	public void SetSpeedY(double sy)
	{
		speed_y=sy;
	}
	
	
	private void checkCollisionTable()
	{
		double posx=this.getXPosition();
		double posy=this.getYPosition();
		double lx=posx-RADIUS_BALL;
		double rx=posx+RADIUS_BALL;
		double ty=posy-RADIUS_BALL;
		double by=posy+RADIUS_BALL;
		double tlx=table.getX();
		double trx=table.getX()+table.getWidth();
		double tty=table.getY();
		double tby=table.getY()+table.getHeight();
		
		if(lx<tlx)
		{
			this.setXPosition(tlx+RADIUS_BALL);
			speed_x=-speed_x;
			
		}
		if(rx>trx)
		{
			this.setXPosition(trx-RADIUS_BALL);
			speed_x=-speed_x;
			
		}	
		
		if(ty<tty)
		{
			this.setYPosition(tty+RADIUS_BALL);
			speed_y=-speed_y;
		}
		
		if(by>tby)
		{
			this.setYPosition(tby-RADIUS_BALL);
			speed_y=-speed_y;
		}
		
		if(this.getXPosition()<tlx+2*RADIUS_BALL && this.getYPosition()<=tty+2*RADIUS_BALL)
			this.deleted=true;
		if(this.getXPosition()>trx-2*RADIUS_BALL && this.getYPosition()<=tty+2*RADIUS_BALL)
			this.deleted=true;
		if(this.getXPosition()<tlx+2*RADIUS_BALL && this.getYPosition()>=tby-2*RADIUS_BALL)
			this.deleted=true;
		if(this.getXPosition()>trx-2*RADIUS_BALL && this.getYPosition()>=tby-2*RADIUS_BALL)
			this.deleted=true;
		if(this.getXPosition()<tlx+table.getWidth()/2+RADIUS_BALL && this.getXPosition()>tlx+table.getWidth()/2-RADIUS_BALL-25 && this.getYPosition()<=tty+2*RADIUS_BALL)
			this.deleted=true;
		if(this.getXPosition()<tlx+table.getWidth()/2+RADIUS_BALL && this.getXPosition()>tlx+table.getWidth()/2-RADIUS_BALL-25 && this.getYPosition()>=tby-2*RADIUS_BALL)
			this.deleted=true;
		
	}
	public void updateFriction()
	{
		if(Math.abs(speed_x)<1.2)
		{
			speed_x=0;
		}
		if(Math.abs(speed_y)<1.2)	
		{
			speed_y=0;
		}

		if(speed_x<0)
			speed_x+=table.getFriction();
		else if(speed_x>0)
			speed_x-=table.getFriction();
		
		if(speed_y<0)
			speed_y+=table.getFriction();
		else if (speed_y>0)
			speed_y-=table.getFriction();
		
	}
	
	private void updateBallCollision()
	{
		Iterator<Updateable> it=table.getGameobjs().iterator();
		
		while(it.hasNext())
		{
			Updateable utb=it.next();
			GameObjectBall ball=(GameObjectBall)utb;
			if(ball!=this && ball!=collised)
			{
				if(this.collides(ball))
				{
					ball.setCollisedBall(this);
					Vector[] r=Physic.deflect(this, ball, new Vector(this.speed_x, this.speed_y), new Vector(ball.speed_x, ball.speed_y));
					this.speed_x=r[0].x;
					this.speed_y=r[0].y;
					ball.speed_x=r[1].x;
					ball.speed_y=r[1].y;
				}
			}
		}
		
	}

	public double getSpeed_x() {
		return speed_x;
	}

	public void setSpeed_x(double speed_x) {
		this.speed_x = speed_x;
	}

	public double getSpeed_y() {
		return speed_y;
	}

	public void setSpeed_y(double speed_y) {
		this.speed_y = speed_y;
	}

	public boolean isDeleted() {
		return deleted;
	}
	


}
