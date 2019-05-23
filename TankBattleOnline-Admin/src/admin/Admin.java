package admin;

import java.awt.*;

import javax.swing.*;
import admin.Listener.*;

public class Admin extends JFrame {
	public static final int win_WIDTH = 800;
	public static final int win_HEIGHT = 600;
	public static final int cube_WIDTH = 20;
	public static final int cube_HEIGHT = 20;
	public NetAdmin na;
	public int current_type = 0; //无物品(0),砖块(1),水(2),金属块(3),草地(5)
	private myPanel panelMap;
	private JPanel panel;
	private JLabel label_title;
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;
	private JLabel label_walls;  //四个图标
	private JLabel label_water;
	private JLabel label_steels;
	private JLabel label_lawn;
	private JButton button_submit;
	private JButton button_cancel;
	public JTextField text;
	public String mapName;
	public String mapStr;
	public int[][] map = new int[][] {
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
	};
	Image img1 = new ImageIcon("image/walls.gif").getImage();
	Image img2 = new ImageIcon("image/water.gif").getImage();
	Image img3 = new ImageIcon("image/steels.gif").getImage();
	Image img4 = new ImageIcon("image/home.png").getImage();
	Image img5 = new ImageIcon("image/grass.PNG").getImage();
	public Admin()  {
		na = new NetAdmin(Admin.this);
		
		this.setLocation(400, 300);
		this.setSize(win_WIDTH, win_HEIGHT);
		this.setTitle("admin  WELCOME");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.gray);
		this.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(100, 100, 400, 400);
		panelMap = new myPanel();
		label_title = new JLabel("地图名：");
		label_title.setBounds(200, 50, 60, 20);
		text = new JTextField();
		text.setBounds(250, 50, 100, 20);
		text.addFocusListener(new TextListener(Admin.this));
		label1 = new JLabel("石头墙");
		label1.setBounds(650, 80, 50, 30);
		label2 = new JLabel("砖头墙");
		label2.setBounds(650, 180, 50, 30);
		label3 = new JLabel("水");
		label3.setBounds(650, 280, 50, 30);
		label4 = new JLabel("草地");
		label4.setBounds(650, 380, 50, 30);
		label_steels = new JLabel();
		label_steels.setIcon(new ImageIcon("image/steels.gif"));
		label_steels.setBounds(650, 100, 70, 70);
		label_steels.addMouseListener(new LabelListener(3,Admin.this));
		label_walls = new JLabel();
		label_walls.setIcon(new ImageIcon("image/walls.gif"));
		label_walls.setBounds(650, 200, 70, 70);
		label_walls.addMouseListener(new LabelListener(1,Admin.this));
		label_water = new JLabel();
		label_water.setIcon(new ImageIcon("image/water.gif"));
		label_water.setBounds(650, 300, 70, 70);
		label_water.addMouseListener(new LabelListener(2,Admin.this));
		label_lawn = new JLabel();
		label_lawn.setIcon(new ImageIcon("image/grass.png"));
		label_lawn.setBounds(650, 400, 70, 70);
		label_lawn.addMouseListener(new LabelListener(5,Admin.this));
		button_submit = new JButton("上传");
		button_submit.setBounds(670, 500, 70, 40);
		button_submit.addActionListener(new SubmitListener(Admin.this));
		button_cancel = new JButton("取消方块");
		button_cancel.setBounds(550, 500, 100, 40);
		button_cancel.addActionListener(new ButtonListener(Admin.this));
		
		this.add(text);
		this.add(panelMap, null);
		this.add(label_lawn, null);
		this.add(label_water, null);
		this.add(label_steels, null);
		this.add(label_walls, null);
		this.add(button_submit, null);
		this.add(button_cancel, null);
		this.add(label_title);
		this.add(label1, null);
		this.add(label2, null);
		this.add(label3, null);
		this.add(label4, null);
		this.add(panel, null);
		
		
		
		this.setVisible(true);
	}
	public static void main(String[] args) {
		Admin admin = new Admin();
	}
	private class myPanel extends JPanel  {
		public myPanel()  {
			this.addMouseListener(new XYListener(Admin.this));
			this.setBounds(100, 100, 400, 400);
		}
		public void paint(Graphics g)  {
			System.out.println("重画");
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					switch (map[j][i]) {
//					case 0:
//						g.drawImage(img0, i * Width, j * Height, Width, Height, null);
					case 1:
						g.drawImage(img1, i * cube_WIDTH, j * cube_WIDTH, cube_WIDTH, cube_WIDTH, null);
						break;
					case 2:
						g.drawImage(img2, i * cube_WIDTH, j * cube_WIDTH, cube_WIDTH, cube_WIDTH, null);
						break;
					case 3:
						g.drawImage(img3, i * cube_WIDTH, j * cube_WIDTH, cube_WIDTH, cube_WIDTH, null);
						break;
					case 4:
						g.drawImage(img4, i * cube_WIDTH, j * cube_WIDTH, cube_WIDTH, cube_WIDTH, null);
						break;
					case 5:
						g.drawImage(img5, i * cube_WIDTH, j * cube_WIDTH, cube_WIDTH, cube_WIDTH, null);
						break;
					default:
						break;
					}
				}
			}
		}
	}
	public void submitSuccess()  {
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null, "上传成功", "提示", JOptionPane.PLAIN_MESSAGE);
	}
}
