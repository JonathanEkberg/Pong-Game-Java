package sound;

import options.Options;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundHandler {

    public static final File hitSoundBitch = new File((String) Options.map.get("HitSoundBitch"));
    public static final File hitSound = new File((String) Options.map.get("HitSound"));
    public static File soundPlay;
    public static float volume = 0.0f;
    private static Clip clip;
    private static FloatControl gainControl;

    public static synchronized void playSound() {
        try {
            soundPlay = ((int) (Math.random() * 2 + 1)) == 1 ? hitSoundBitch : hitSound;
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundPlay);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}