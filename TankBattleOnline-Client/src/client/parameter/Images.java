package client.parameter;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

public class Images {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public static Image[] imagesExplode = {
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode1.png")),
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode2.png")),
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode3.png")),
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode4.png")),
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode5.png")),
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode6.png")),
			// tk.getImage(Explode.class.getClassLoader().getResource("image/explode7.png"))
			new ImageIcon("image/explode1.png").getImage(), new ImageIcon("image/explode2.png").getImage(),
			new ImageIcon("image/explode3.png").getImage(), new ImageIcon("image/explode4.png").getImage(),
			new ImageIcon("image/explode5.png").getImage(), new ImageIcon("image/explode6.png").getImage(),
			new ImageIcon("image/explode7.png").getImage() };

	// private static Toolkit tk = Toolkit.getDefaultToolkit();
	// private static Image img0 =
	// tk.getImage(Explode.class.getClassLoader().getResource("image/nothing.gif"));
	// private static Image img1 =
	// tk.getImage(Explode.class.getClassLoader().getResource("image/walls.gif"));
	// private static Image img2 =
	// tk.getImage(Explode.class.getClassLoader().getResource("image/water.gif"));
	// private static Image img3 =
	// tk.getImage(Explode.class.getClassLoader().getResource("image/steels.gif"));
	// private static Image img4 =
	// tk.getImage(Explode.class.getClassLoader().getResource("image/home.gif"));
	// private static Image img5 =
	// tk.getImage(Explode.class.getClassLoader().getResource("image/grass.gif"));

	public static Image[] imagesBattleMapItems = { new ImageIcon("image/nothing.gif").getImage(),
			new ImageIcon("image/walls.gif").getImage(), new ImageIcon("image/water.gif").getImage(),
			new ImageIcon("image/steels.gif").getImage(), new ImageIcon("image/home.png").getImage(),
			new ImageIcon("image/grass.PNG").getImage() };

	public static Image[] imagesMissile = {
			// tk.getImage(Missile.class.getClassLoader().getResource("image/bullet.gif")),
			// tk.getImage(Missile.class.getClassLoader().getResource("image/bullet.gif"))
			new ImageIcon("image/bullet.gif").getImage(), new ImageIcon("image/bullet.gif").getImage() };

	public static Image[] imagesTank = { // 加载两方阵营的图片
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankD.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankL.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankLD.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankLU.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankR.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankRD.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankRU.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p1tankU.gif")),
			//
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankD.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankL.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankLD.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankLU.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankR.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankRD.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankRU.gif")),
			// tk.getImage(Tank.class.getClassLoader().getResource("image/p2tankU.gif")),

			new ImageIcon("image/p1tankD.gif").getImage(), new ImageIcon("image/p1tankL.gif").getImage(),
			new ImageIcon("image/p1tankLD.gif").getImage(), new ImageIcon("image/p1tankLU.gif").getImage(),
			new ImageIcon("image/p1tankR.gif").getImage(), new ImageIcon("image/p1tankRD.gif").getImage(),
			new ImageIcon("image/p1tankRU.gif").getImage(), new ImageIcon("image/p1tankU.gif").getImage(),

			new ImageIcon("image/p2tankD.gif").getImage(), new ImageIcon("image/p2tankL.gif").getImage(),
			new ImageIcon("image/p2tankLD.gif").getImage(), new ImageIcon("image/p2tankLU.gif").getImage(),
			new ImageIcon("image/p2tankR.gif").getImage(), new ImageIcon("image/p2tankRD.gif").getImage(),
			new ImageIcon("image/p2tankRU.gif").getImage(), new ImageIcon("image/p2tankU.gif").getImage()

	};

	public static Image image1 = new ImageIcon("image/info.jpg").getImage();
	public static Image image2 = new ImageIcon("image/begin.jpg").getImage();
	// public static Image image2 = new ImageIcon("image/begin.jpg").getImage();
	public static ImageIcon icon = new ImageIcon("image/title2.PNG");
}
