package client.parameter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import client.modal.Music;

@Component
public class Musics {
	// 全局音乐对象
	// public static Music musicBKM = new Music("music/The Warrior Song.wav");
	// // 背景音乐对象
	// public static Music musicFire = new Music("music/开始攻击.wav");
	// public static Music musicIron = new Music("music/打铁.wav");
	// public static Music musicBulletExplode = new Music("music/爆炸.wav");
	// public static Music musicHomeExplode = new Music("music/爆炸1.wav");
	// public static Music musicBomb = new Music("music/炸弹.wav");
	// public static Music musicEat = new Music("music/吃东西.wav");// 吃东西
	// public static Music musicGameOver = new Music("music/输.wav");// 游戏结束
	// public static Music musicGetHp = new Music("music/奖命.wav");
	// public static Music musicMove = new Music("music/自己移动.wav");
	// public static Music musicRelive = new Music("music/复活.wav");
	// public static Music musicStart = new Music("music/开始.wav");
	/*
	 *spring bean xml文件注入
	 * */
	public static Music musicBKM; // 背景音乐对象
	public static Music musicFire;
	public static Music musicIron;
	public static Music musicBulletExplode;
	public static Music musicHomeExplode;
	public static Music musicBomb;
	public static Music musicEat;// 吃东西
	public static Music musicGameOver;// 游戏结束
	public static Music musicGetHp;
	public static Music musicMove;
	public static Music musicRelive;
	public static Music musicStart;

	@Autowired
	public static Music getMusicBKM() {
		return musicBKM;
	}

	@Autowired
	public void setMusicBKM(Music musicBKM) {
		Musics.musicBKM = musicBKM;
	}

	@Autowired
	public Music getMusicFire() {
		return musicFire;
	}

	@Autowired
	public void setMusicFire(Music musicFire) {
		Musics.musicFire = musicFire;
	}

	@Autowired
	public Music getMusicIron() {
		return musicIron;
	}

	@Autowired
	public void setMusicIron(Music musicIron) {
		Musics.musicIron = musicIron;
	}

	@Autowired
	public Music getMusicBulletExplode() {
		return musicBulletExplode;
	}

	@Autowired
	public void setMusicBulletExplode(Music musicBulletExplode) {
		Musics.musicBulletExplode = musicBulletExplode;
	}

	@Autowired
	public Music getMusicHomeExplode() {
		return musicHomeExplode;
	}

	@Autowired
	public void setMusicHomeExplode(Music musicHomeExplode) {
		Musics.musicHomeExplode = musicHomeExplode;
	}

	@Autowired
	public Music getMusicBomb() {
		return musicBomb;
	}

	@Autowired
	public void setMusicBomb(Music musicBomb) {
		Musics.musicBomb = musicBomb;
	}

	@Autowired
	public Music getMusicEat() {
		return musicEat;
	}

	@Autowired
	public void setMusicEat(Music musicEat) {
		Musics.musicEat = musicEat;
	}

	@Autowired
	public Music getMusicGameOver() {
		return musicGameOver;
	}

	@Autowired
	public void setMusicGameOver(Music musicGameOver) {
		Musics.musicGameOver = musicGameOver;
	}

	@Autowired
	public Music getMusicGetHp() {
		return musicGetHp;
	}

	@Autowired
	public void setMusicGetHp(Music musicGetHp) {
		Musics.musicGetHp = musicGetHp;
	}

	@Autowired
	public Music getMusicMove() {
		return musicMove;
	}

	@Autowired
	public void setMusicMove(Music musicMove) {
		Musics.musicMove = musicMove;
	}

	@Autowired
	public Music getMusicRelive() {
		return musicRelive;
	}

	@Autowired
	public void setMusicRelive(Music musicRelive) {
		Musics.musicRelive = musicRelive;
	}

	@Autowired
	public Music getMusicStart() {
		return musicStart;
	}

	@Autowired
	public void setMusicStart(Music musicStart) {
		Musics.musicStart = musicStart;
	}

}
