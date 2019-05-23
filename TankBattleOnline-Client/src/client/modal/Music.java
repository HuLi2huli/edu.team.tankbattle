package client.modal;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music {

	String file; // wav音乐文件
	int loop; // n循环标记，0=循环播放，1=单次播放, n=播放n次

	Clip clip;
	File wavFile;
	AudioInputStream ais;

	public Music(String filepath) {
		file = filepath;

		try {
			clip = AudioSystem.getClip();
			wavFile = new File(file);
			ais = AudioSystem.getAudioInputStream(wavFile);
			clip.open(ais);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Music play(int n) {
		loop = n;
		if (n == 0)
			clip.loop(Clip.LOOP_CONTINUOUSLY); // 永远循环播放
		else if (n >= 1) {
			clip.setMicrosecondPosition(0); // 每次重复播放前设置剪辑播放位置为0.
			clip.loop(n - 1); // 播放n次
		}
		return this;
	}

	// 这个stop具备暂停效果，再次调用play方法会继续播放下去
	public void stop() {
		clip.stop();
	}

	public boolean playingStatus() {
		return clip.isRunning();
	}
}