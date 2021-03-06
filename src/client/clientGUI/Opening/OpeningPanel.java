package client.clientGUI.Opening;

import helper.MusicPlayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class OpeningPanel extends JPanel implements ActionListener {
	
	Timer timer = new Timer(40, this);
	
	/* initial position and moving speed of each sprites */
	int millerX = 0, millerXSpeed = 6, millerY = 170, millerYSpeed;
	int crowleyX = 590, crowleyXSpeed = -6, crowleyY = 170, crowleyYSpeed;
	
	int gameLogoY = -800, gameLogoYSpeed = 0;
	
	int dolanX = -200, dolanXSpeed = 0, dolanY = 70, dolanYSpeed;
	int jungIllX = -200, jungIllXSpeed = 0, jungIllY = 270, jungIllYSpeed;
	int goobyX = 800, goobyXSpeed = 0, goobyY = 70, goobyYSpeed;
	int mistyX = 800, mistyXSpeed = 0, mistyY = 270, mistyYSpeed;
	
	int aerodonX = 800, aerodonXSpeed = 0;
	int beetwoX = 920, beetwoXSpeed = 0;
	int dadizardX = 1031, dadizardXSpeed = 0;
	int exegynxX = 1176, exegynxXSpeed = 0;
	int feelgletX = 1277, feelgletXSpeed = 0;
	int gengynxX = 1394, gengynxXSpeed = 0;
	int geonxX = 1498, geonxXSpeed = 0; //1498
	int golemX = 1605, golemXSpeed = 0;
	int jiwodudeX = 1716, jiwodudeXSpeed = 0;
	int lapradactylX = 1833, lapradactylXSpeed = 0;
	
	int lickisterX = -122, lickisterXSpeed = 0;
	int magikunaX = -165, magikunaXSpeed = 0;
	int marozardX = -286, marozardXSpeed = 0;
	int meonxX = marozardX-107, meonxXSpeed = 0;
	int pikayuX = meonxX-68, pikayuXSpeed = 0;
	int rhyfetchX = pikayuX-76, rhyfetchXSpeed = 0;
	int scydraX = rhyfetchX-60, scydraXSpeed = 0;
	int seanasaurX = scydraX-60, seanasaurXSpeed = 0;
	int sexypodX = seanasaurX-103, sexypodXSpeed = 0;
	int weepintoiseX = sexypodX-115, weepintoiseXSpeed = 0;
	
	Image dolanImage = null, millerImage = null, crowleyImage = null,
			mistyImage = null, jungIllImage = null, goobyImage = null;
	Image aerodon_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Aerodon_left_tr.png"); 
	Image beetwo_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Bee-two_left_tr.png"); 
	Image dadizard_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/dadizard_left_tr.png"); 
	Image exegynx_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Exegynx_left_tr.png"); 
	Image feelglet_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/feelglet_left_tr.png");
	Image gengynx_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Gengynx_left_tr.png"); 
	Image geonx_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Geonx_left_tr.png"); 
	Image golem_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Golem_left_tr.png"); 
	Image jiwodude_left = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/jiwodude_left_tr.png"); 
	Image lapradactyl_left = null;
	
	Image lickister_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Lickister_right_tr.png"); 
	Image magikuna_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Magikuna_right_tr.png"); 
	Image marozard_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Marozard_right_tr.png"); 
	Image meonx_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Meonx_right_tr.png"); 
	Image pikayu_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Pikayu_right_tr.png");
	Image rhyfetch_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/sexypod_left_tr.png"); 
	Image scydra_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Scydra_right_tr.png"); 
	Image seanasaur_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/Seanasaur_right_tr.png"); 
	Image sexypod_right = null;//Toolkit.getDefaultToolkit().getImage("res/Pokemon_sprites/sexypod_right_tr.png"); 
	Image weepintoise_right = null;
	
	Image gameScreen = null;
	
	public boolean done = false;
	int timerCount = 0;
	int timerCountSpeed = 1;
	
	public Clip clip;
	
	AudioInputStream inputStream;// = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("res/Epic_sax_guy.wav"));
	
	public OpeningPanel(){
		setPreferredSize(new Dimension(800,600));
		try {
			millerImage = ImageIO.read(getClass().getResource("/Character_sprites/Miller_hot.jpg"));
			crowleyImage = ImageIO.read(getClass().getResource("/Character_sprites/Crowley_robot.jpg"));
			dolanImage = ImageIO.read(getClass().getResource("/Character_sprites/Dolan_normal.png"));
		} catch(IOException ioe) {
			System.out.println("fail reading image; openingPanel");
		}
		try {
			jungIllImage = ImageIO.read(getClass().getResource("/Character_sprites/JungIll_normal.png"));
		} catch(IOException ioe) {
			System.out.println("fail reading image; openingPanel");
		}
		try {
			goobyImage = ImageIO.read(getClass().getResource("/Character_sprites/Gooby_normal.png"));
		} catch(IOException ioe) {
			System.out.println("fail reading image; openingPanel");
		}
		try {
			mistyImage = ImageIO.read(getClass().getResource("/Character_sprites/Misty_normal.png"));
		} catch(IOException ioe) {
			System.out.println("fail reading image; openingPanel");
		}
		try {
			aerodon_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Aerodon_left_tr.png")); 
			beetwo_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Bee-two_left_tr.png"));
			dadizard_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/dadizard_left_tr.png"));
			exegynx_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Exegynx_left_tr.png")); 
			feelglet_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Derplett_left_tr.png"));
			
			gengynx_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Gengynx_left_tr.png")); 
			geonx_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Geonx_left_tr.png")); 
			golem_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Golem_left_tr.png")); 
			jiwodude_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/jiwodude_left_tr.png")); 
			lapradactyl_left = ImageIO.read(getClass().getResource("/Pokemon_sprites/Lapradactyl_left_tr.png"));
			
		} catch(IOException ioe) {
			System.out.println("fail reading img openingpanel");
		}

		try {
			lickister_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Lickister_left_tr.png"));
			magikuna_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Magikuna_left_tr.png")); 
			marozard_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Marozard_left_tr.png"));
			meonx_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Meonx_left_tr.png"));
			pikayu_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Pikayu_left_tr.png"));
			rhyfetch_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Sexy-apod_right_tr.png"));
			scydra_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Sexy-apod_left_tr.png"));
			seanasaur_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Seanasaur_left_tr.png"));
			sexypod_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Sexy-apod_left_tr.png")); 
			weepintoise_right = ImageIO.read(getClass().getResource("/Pokemon_sprites/Weepintoise_left_tr.png"));
		} catch(IOException ioe) {
			System.out.println("Fail reading images openingpanel");
		}
		try {
			gameScreen = ImageIO.read(getClass().getResource("/screen_opening.png"));
		} catch(IOException ioe) {
			System.out.println("Fail reading screen oopeningpanel");
		}
		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
	}
	
	public void stop(){
		clip.stop();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 800);
		
		/* get the image files and place them on their default position */
		
		g.drawImage(millerImage, millerX, millerY, this);
		
	
		g.drawImage(crowleyImage, crowleyX, crowleyY, this);

		g.drawImage(dolanImage, dolanX, dolanY, this);
		
		g.drawImage(jungIllImage, jungIllX, jungIllY, this);
		
		g.drawImage(goobyImage, goobyX, goobyY, this);
	
		g.drawImage(mistyImage, mistyX, mistyY, this);
		
		g.drawImage(aerodon_left, aerodonX, 0, this);
		g.drawImage(beetwo_left, beetwoX, 0, this);
		g.drawImage(dadizard_left, dadizardX, 0, this);
		g.drawImage(exegynx_left, exegynxX, 0, this);
		g.drawImage(feelglet_left, feelgletX, 0, this);
		g.drawImage(gengynx_left, gengynxX, 0, this);
		g.drawImage(geonx_left, geonxX, 0, this);
		g.drawImage(golem_left, golemX, 0, this);
		g.drawImage(jiwodude_left, jiwodudeX, 0, this);
		g.drawImage(lapradactyl_left, lapradactylX, 0, this);

		g.drawImage(lickister_right, lickisterX, 450, this);
		g.drawImage(magikuna_right, magikunaX, 450, this);
		g.drawImage(marozard_right, marozardX, 450, this);
		g.drawImage(meonx_right, meonxX, 450, this);
		g.drawImage(pikayu_right, pikayuX, 450, this);
		g.drawImage(rhyfetch_right, rhyfetchX, 450, this);
		g.drawImage(scydra_right, scydraX, 450, this);
		g.drawImage(seanasaur_right, seanasaurX, 450, this);
		g.drawImage(sexypod_right, sexypodX, 450, this);
		g.drawImage(weepintoise_right, weepintoiseX, 450, this);
		
		g.drawImage(gameScreen, 0, gameLogoY, this);
		
		/* start the timer */
		timer.start();
		if (clip == null)
			//clip = MusicPlayer.run("res/Epic_sax_guy.wav");
			
			try {
				inputStream = AudioSystem.getAudioInputStream(getClass().getResource("/Epic_sax_guy.wav"));
				clip = AudioSystem.getClip();
				clip.open(inputStream);
				clip.start();
			} catch(LineUnavailableException lue) {
				System.out.println("fail reading music");
			} catch(IOException ioe) {
				System.out.println("fail reading music 2");
			} catch(UnsupportedAudioFileException uafe) {
				System.out.println("fail reading music 3");
			}
	}
	
	public void actionPerformed (ActionEvent e) {
		timerCount += timerCountSpeed;
		/* simulate animation by changing position over time */
		gameLogoY += gameLogoYSpeed;
		
		millerX += millerXSpeed;
		crowleyX += crowleyXSpeed;
		dolanX += dolanXSpeed;
		jungIllX += jungIllXSpeed;
		goobyX += goobyXSpeed;
		mistyX += mistyXSpeed;
		
		millerY += millerYSpeed;
		crowleyY += crowleyYSpeed;
		dolanY += dolanYSpeed;
		jungIllY += jungIllYSpeed;
		goobyY += goobyYSpeed;
		mistyY += mistyYSpeed;
		
		aerodonX += aerodonXSpeed;
		beetwoX += beetwoXSpeed;
		dadizardX += dadizardXSpeed;
		exegynxX += exegynxXSpeed;
		feelgletX += feelgletXSpeed;
		gengynxX += gengynxXSpeed;
		geonxX += geonxXSpeed;
		golemX += golemXSpeed ;
		jiwodudeX +=  jiwodudeXSpeed;
		lapradactylX += lapradactylXSpeed;
		
		lickisterX += lickisterXSpeed;
		magikunaX += magikunaXSpeed;
		marozardX += marozardXSpeed;
		meonxX += meonxXSpeed;
		pikayuX += pikayuXSpeed;
		rhyfetchX += rhyfetchXSpeed;
		scydraX += scydraXSpeed;
		seanasaurX +=  seanasaurXSpeed;
		sexypodX +=  sexypodXSpeed;
		weepintoiseX +=  weepintoiseXSpeed;
		
		/* change the speed if needed */
		int topRollingSpeed = -10;
		if ( (millerX + 160 > crowleyX) ) {
			millerXSpeed = 0;
			crowleyXSpeed = 0;
			dolanXSpeed = 4;
			jungIllXSpeed = 4;
			goobyXSpeed = -4;
			mistyXSpeed = -4;
			
			aerodonXSpeed = topRollingSpeed;
			beetwoXSpeed = topRollingSpeed;
			dadizardXSpeed = topRollingSpeed;
			exegynxXSpeed = topRollingSpeed;
			feelgletXSpeed = topRollingSpeed;
			gengynxXSpeed = topRollingSpeed;
			geonxXSpeed = topRollingSpeed;
			golemXSpeed = topRollingSpeed;
			jiwodudeXSpeed = topRollingSpeed;
			lapradactylXSpeed = topRollingSpeed;
			
			lickisterXSpeed = -topRollingSpeed;
			magikunaXSpeed = -topRollingSpeed;
			marozardXSpeed = -topRollingSpeed;
			meonxXSpeed = -topRollingSpeed;
			pikayuXSpeed = -topRollingSpeed;
			rhyfetchXSpeed = -topRollingSpeed;
			scydraXSpeed = -topRollingSpeed;
			seanasaurXSpeed = -topRollingSpeed;
			sexypodXSpeed = -topRollingSpeed;
			weepintoiseXSpeed = -topRollingSpeed;
		}
		
		if ( dolanX >= 0) {
			dolanXSpeed = 0;
			jungIllXSpeed = 0;
			goobyXSpeed = 0;
			mistyXSpeed = 0;
		}
		
		if (weepintoiseX > 850) {
			dolanYSpeed = 30;
			jungIllYSpeed = 30;
			goobyYSpeed = 30;
			mistyYSpeed = 30;
			millerYSpeed = 30;
			crowleyYSpeed = 30;
			gameLogoYSpeed = 80;
		}
		if (gameLogoY >= 00) {
			gameLogoYSpeed = 0;
		}
		
		if (timerCount >= 350) {
			System.out.println("opening done!");
			done = true;
			timer.stop();
//			clip.stop();
		}
//		if (done) System.out.println("DONE and ALAN LIKES DETERGENTS");
		
		/*
		if (weepintoiseX > 0) {
			lickisterXSpeed = -0;
			magikunaXSpeed = -0;
			marozardXSpeed = -0;
			meonxXSpeed = -0;
			pikayuXSpeed = -0;
			rhyfetchXSpeed = -0;
			scydraXSpeed = -0;
			seanasaurXSpeed = -0;
			sexypodXSpeed = -0;
			weepintoiseXSpeed = -0;
		}
		
		if (lapradactylX < 917) {
			aerodonXSpeed = 0;
			beetwoXSpeed = 0;
			dadizardXSpeed = 0;
			exegynxXSpeed = 0;
			feelgletXSpeed = 0;
			gengynxXSpeed = 0;
			geonxXSpeed = 0;
			golemXSpeed = 0;
			jiwodudeXSpeed = 0;
			lapradactylXSpeed = 0;
		}
		*/
		repaint();
	}
	public static void main (String args []) {
		OpeningPanel op = new OpeningPanel();
		JFrame testWindow = new JFrame();
		testWindow.setTitle("Testing Opening");
		testWindow.setResizable(false);
		testWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		testWindow.add(op);
		testWindow.pack();
		testWindow.setVisible(true);
	}
}
