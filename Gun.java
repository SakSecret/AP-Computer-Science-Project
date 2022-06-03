import java.util.ArrayList;

public class Gun extends GameObject{
	
	public Bullet[] bullets = new Bullet[100];
	
	public int activeBullet = 0;
	private int fireFrame = 0;
	
	public Gun() {
		super(0, 0, 100, 20, "src/misc/gun_right.png");
		for (int i = 0; i < bullets.length; i++) {
			bullets[i] = new Bullet(1);
		}
	}
	
	public void update(ArrayList<GameObject> list) {
		setLocation(list.get(0).getX() + 100, list.get(0).getY() + 50);
		System.out.println("Mario xPos: " + list.get(0).getX());
	}
	public void shoot(int direction) { //-1 is left, 1 is right
		
		if (fireFrame == 5) {
			bullets[activeBullet].setActive();
			
			activeBullet++;
			if (activeBullet == 100) {
				activeBullet = 0;
			}
			fireFrame = 0;
		}
		fireFrame++;
	}
	
	public class Bullet extends GameObject {
		private int frameCount = 0;
		int direction;
		boolean active = false;
		public Bullet(int vdir) {
			super(Gun.this.getX(), Gun.this.getY(), 10, 10, "src/misc/bullet_right.png");
			direction = vdir;
			setPhysics(false);
			//setVisible(false);
		}
		
		
		public void update(ArrayList<GameObject> objects) {
			if (active) {
				setLocation(getX() + getdx(), getY() + getdy());
				if (frameCount == 70) {
					changedy(1);
					frameCount = 0;
				}
				frameCount++;
			}
		}
		
		public void setActive() {
			active = true;
			setVisible(true);
			setLocation(Gun.this.getX(), Gun.this.getY());
			setdx(20);
			setdy((int)(Math.random() * 4) - 2);
		}
		public boolean isActive() {
			return active;
		}
		public void stopBullet() {
			active = false;
			setVisible(false);
		}
		
	}
}
