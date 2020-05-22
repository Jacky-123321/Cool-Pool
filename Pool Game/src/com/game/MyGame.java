package com.game;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.util.Iterator;

import com.render.Ball;
import com.render.GameArena;
import com.render.Line;
import com.render.Rectangle;
import com.render.Text;

/**
* @author Shiyuan Wen ID 37727273
* @version 1.0  2020.5.22
* @since JDK 14
*/

public class MyGame extends GameArena {

	public static final int render_width = 1000;
	public static final int render_height = 600;
	private long bgtime;
	private Table table;
	private Line line=new Line(0,0,0,0,7,"RED",10);///Pole
	private Line line_point=new Line(0,0,0,0,7,"BLUE",10);
	private Ball tball=new Ball(0,0,9,"RED",10);
	private boolean left_press=false;
	private double bgx=0;
	private double bgy=0;
	private double offx=0;
	private double offy=0;
	private double dirx=0;
	private double diry=0;
	private boolean line_is_moving=false;
	private double lnbgx=0;
	private double lnbgy=0;
	private double mov_distance=0;
	private double speed = 50;
	private Rectangle restart;
	private Text txtrestart;
	private double txttimer=0;
	private boolean btnpress=false;
	
	
	public MyGame() {
		super(render_width, render_height, true);
		bgtime = System.currentTimeMillis();
		initTable();
		initBalls();
		initRestartButton();
		intro();

	}

	private void initRestartButton() {
		
		restart = new Rectangle(render_width/2-50, 5, 100, 20, "WHITE", 5);
		this.addRectangle(restart);
		txtrestart=new Text("Restart",16,render_width/2-30, 20,"RED",6);
		this.addText(txtrestart);
		
	}

	private void initTable() {
		table = new Table(50, 50, 700, 400, 1);
		Rectangle r2 = new Rectangle(table.getX(), table.getY(), table.getWidth(), table.getHeight(), "GREEN", 1);
		this.addRectangle(r2);
		
		Ball hole1=new Ball(table.getX(),table.getY(),50,"BLACK",2);
		this.addBall(hole1);
		Ball hole2=new Ball(table.getX()+table.getWidth()/2-25,table.getY(),50,"BLACK",2);
		this.addBall(hole2);
		Ball hole3=new Ball(table.getX()+table.getWidth(),table.getY(),50,"BLACK",2);
		this.addBall(hole3);
		Ball hole4=new Ball(table.getX()+table.getWidth(),table.getY()+table.getHeight(),50,"BLACK",2);
		this.addBall(hole4);
		Ball hole5=new Ball(table.getX()+table.getWidth()/2-25,table.getY()+table.getHeight(),50,"BLACK",2);
		this.addBall(hole5);
		Ball hole6=new Ball(table.getX(),table.getY()+table.getHeight(),50,"BLACK",2);
		this.addBall(hole6);
		
		Rectangle border1 = new Rectangle(table.getX() - 20, table.getY() - 20, table.getWidth() + 40,20, "GREY", 3);
		this.addRectangle(border1);
		Rectangle border2 = new Rectangle(table.getX() - 20, table.getY() +table.getHeight(), table.getWidth() + 40,20, "GREY", 3);
		this.addRectangle(border2);
		Rectangle border3 = new Rectangle(table.getX() - 20, table.getY() - 20, 20,table.getY()+table.getHeight()-20, "GREY", 3);
		this.addRectangle(border3);
		Rectangle border4 = new Rectangle(table.getX()+table.getWidth(), table.getY() - 20, 20,table.getY()+table.getHeight()-20, "GREY", 3);
		this.addRectangle(border4);

		Line ln1=new Line(table.getX()+200,table.getY(),table.getX()+200,table.getY()+table.getHeight(),1,"WHITE",3);
		this.addLine(ln1);
	}

	private void intro(){
		Text title =new Text("Welcome to Cool Pool!", 40, 50, 550, "YELLOW");
		this.addText(title);
		Text introdece = new Text("Press and Move", 20, 800, 200, "RED");
		this.addText(introdece);
	}

	private void initBalls() {
		double bbgx=table.getX()+500;
		double bbgy=table.getY()+table.getHeight()/2-10;
		double r3=Math.sqrt(3)+0.1;
		
		GameObjectBall ball0 = new GameObjectBall(table, bbgx-300,bbgy, "WHITE");
		table.getGameobjs().add(ball0);
		this.addBall(ball0);
		
		
		GameObjectBall ball = new GameObjectBall(table, bbgx,bbgy, "RED");
		table.getGameobjs().add(ball);
		this.addBall(ball);
		
		GameObjectBall ball2 = new GameObjectBall(table, bbgx+10*r3,bbgy-11, "BLUE");
		table.getGameobjs().add(ball2);
		this.addBall(ball2);
		
		GameObjectBall ball3 = new GameObjectBall(table, bbgx+10*r3,bbgy+11, "RED");
		table.getGameobjs().add(ball3);
		this.addBall(ball3);
		
		GameObjectBall ball4 = new GameObjectBall(table, bbgx+20*r3,bbgy-21, "RED");
		table.getGameobjs().add(ball4);
		this.addBall(ball4);
		
		GameObjectBall ball5 = new GameObjectBall(table, bbgx+20*r3,bbgy, "BLACK");
		table.getGameobjs().add(ball5);
		this.addBall(ball5);
		
		GameObjectBall ball6 = new GameObjectBall(table, bbgx+20*r3,bbgy+21, "BLUE");
		table.getGameobjs().add(ball6);
		this.addBall(ball6);
		
		GameObjectBall ball7 = new GameObjectBall(table, bbgx+30*r3+3,bbgy-32, "BLUE");
		table.getGameobjs().add(ball7);
		this.addBall(ball7);
		
		GameObjectBall ball8 = new GameObjectBall(table, bbgx+30*r3+3,bbgy-11, "RED");
		table.getGameobjs().add(ball8);
		this.addBall(ball8);
		
		GameObjectBall ball9 = new GameObjectBall(table, bbgx+30*r3+3,bbgy+11, "BLUE");
		table.getGameobjs().add(ball9);
		this.addBall(ball9);
		
		GameObjectBall ball10 = new GameObjectBall(table, bbgx+30*r3+3,bbgy+32, "RED");
		table.getGameobjs().add(ball10);
		this.addBall(ball10);
		
		GameObjectBall ball11 = new GameObjectBall(table, bbgx+40*r3+3,bbgy-42, "BLUE");
		table.getGameobjs().add(ball11);
		this.addBall(ball11);
		
		
		GameObjectBall ball12 = new GameObjectBall(table, bbgx+40*r3+3,bbgy-21, "RED");
		table.getGameobjs().add(ball12);
		this.addBall(ball12);
		
		GameObjectBall ball13 = new GameObjectBall(table, bbgx+40*r3+3,bbgy, "BLUE");
		table.getGameobjs().add(ball13);
		this.addBall(ball13);
		
		GameObjectBall ball14 = new GameObjectBall(table, bbgx+40*r3+3,bbgy+21, "RED");
		table.getGameobjs().add(ball14);
		this.addBall(ball14);
		
		GameObjectBall ball15 = new GameObjectBall(table, bbgx+40*r3+3,bbgy+42, "BLUE");
		table.getGameobjs().add(ball15);
		this.addBall(ball15);

	}

	@Override
	public void paint(Graphics gr) {
		super.paint(gr);
		double dt = ((System.currentTimeMillis() - bgtime) / 1000.0);
		bgtime = System.currentTimeMillis();
		update(dt);

	}

	private void update(double dt) {
		Iterator<Updateable> it = table.getGameobjs().iterator();
		while (it.hasNext()) {
			GameObjectBall ball=(GameObjectBall)it.next();
			if(ball.isDeleted())
			{
				this.removeBall(ball);
				it.remove();
			}
			else
			{
				ball.setCollisedBall(null);
			}
			
		}
		it = table.getGameobjs().iterator();
		while (it.hasNext()) {
			it.next().update(dt);
		}
		updatePole(dt);
		updateButton(dt);
	}

	private void updateButton(double dt) {
		
		txttimer+=dt;
		if(txttimer>=0.2)
		{
			if(txtrestart.getColour().equals("RED"))
				txtrestart.setColour("BLUE");
			else
				txtrestart.setColour("RED");
			txttimer=0;
		}
		
		if(leftMousePressed() && !btnpress)
		{
			btnpress=true;
			double mx=getMousePositionX();
			double my=getMousePositionY();
			if(mx>=restart.getXPosition() && mx<=restart.getXPosition()+restart.getWidth() && my>=restart.getYPosition() && my<=restart.getYPosition()+restart.getHeight())
			{
				restart_game();
			}
			
		}
		if(btnpress && !leftMousePressed())
		{
			btnpress=false;
			
		}
		
	}

	private void restart_game() {
		
		Iterator<Updateable> it = table.getGameobjs().iterator();
		while (it.hasNext()) {
			GameObjectBall ball=(GameObjectBall)it.next();
			this.removeBall(ball);
		}
		table.getGameobjs().clear();
		initBalls();
		
	}

	private void updatePole(double dt) {
		PointerInfo pin = MouseInfo.getPointerInfo();
		boolean left=leftMousePressed();
		if(line_is_moving)
		{
			
			double x1 = line.getXStart() - dirx * speed * dt;
			double y1 = line.getYStart() - diry * speed * dt;
			double x2 = line.getXEnd() - dirx * speed * dt;
			double y2 = line.getYEnd() - diry * speed * dt;
			line.setLinePosition(x1, y1, x2, y2);
			tball.setXPosition(x1);
			tball.setYPosition(y1);
			
			Iterator<Updateable> it = table.getGameobjs().iterator();
			while (it.hasNext()) {
				GameObjectBall ball=(GameObjectBall)it.next();
				if(tball.collides(ball))
				{
					Vector[] r=Physic.deflect(tball, ball, new Vector(dirx * speed*2.5 , diry * speed*2.5 ), new Vector(ball.getSpeed_x(), ball.getSpeed_y()));
					ball.SetSpeed(r[1].x, r[1].y);
					this.removeLine(line);
					this.removeLine(line_point);
					this.removeBall(tball);
					line_is_moving=false;
					speed=50;
					break;
				}
			}
			
			if(!line_is_moving)
				return;
			double mov_dis=Math.sqrt((lnbgx-x1)*(lnbgx-x1)+ (lnbgy-y1)*(lnbgy-y1));
			if(mov_dis>mov_distance)
			{
				this.removeLine(line);
				this.removeLine(line_point);
				this.removeBall(tball);
				line_is_moving=false;
				speed=50;
			}
			speed+=30;
			return;
		}
		if(left && !left_press)//begin
		{
			left_press=true;
			offx=pin.getLocation().getX()-this.getMousePositionX();
			offy=pin.getLocation().getY()-this.getMousePositionY();
			bgx=this.getMousePositionX();
			bgy=this.getMousePositionY();
			tball.setXPosition(bgx);
			tball.setYPosition(bgy);
			line.setLinePosition(bgx, bgy, bgx,bgy);
			line_point.setLinePosition(bgx, bgy, bgx,bgy);
			this.addLine(line);
			this.addLine(line_point);
			this.addBall(tball);
			
		}
		else if(left)///move
		{
			double edx=pin.getLocation().getX()-offx;
			double edy=pin.getLocation().getY()-offy;
			double dis=Math.sqrt((edx-bgx)*(edx-bgx)+(edy-bgy)*(edy-bgy));
			if(dis>5)
			{
				
				dirx=(edx-bgx)/dis;
				diry=(edy-bgy)/dis;
				double nx=dirx *500+(bgx+edx)/2;
				double ny=diry *500+(bgy+edy)/2;
				line.setLinePosition((bgx+edx)/2, (bgy+edy)/2, nx,ny);
				tball.setXPosition((bgx+edx)/2);
				tball.setYPosition((bgy+edy)/2);
			}
		}
		
		if(!left && left_press)
		{
			left_press=false;
			line_is_moving=true;
			lnbgx=line.getXStart();
			lnbgy=line.getYStart();
			mov_distance=Math.sqrt((lnbgx-bgx)*(lnbgx-bgx)+(lnbgy-bgy)*(lnbgy-bgy))*2;
		}
		
	}

}
