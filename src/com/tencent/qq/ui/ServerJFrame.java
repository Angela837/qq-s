package com.tencent.qq.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tencent.qq.biz.ServerBiz;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.Socket;

public class ServerJFrame extends JFrame {

	private JPanel contentPane;

	private static ServerBiz ser;
	private Socket socket;
	JButton button = new JButton("\u542F\u52A8\u670D\u52A1\u5668");
	JButton button_1 = new JButton("\u5173\u95ED\u670D\u52A1\u5668");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ser = new ServerBiz();
					ServerJFrame frame = new ServerJFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServerJFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 212);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				start(evt);
			}
		});

		button.setBounds(39, 55, 138, 41);
		contentPane.add(button);

		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				close(evt);
			}
		});
		button_1.setEnabled(false);
		button_1.setBounds(240, 55, 145, 41);
		contentPane.add(button_1);
	}

	// 点击按钮启动服务器
	private void start(ActionEvent evt) {

		System.out.println(evt);
		// 启动线程
		new Thread() {
			public void run() {
				button.setEnabled(false);

				button_1.setEnabled(true);

				try {
					ser.startServer();

				} catch (Exception e) {
//					System.out.println("服务器启动失败！");
//					e.printStackTrace();
				}
			};
		}.start();
	}

	// 点击按钮关闭服务器
	private void close(ActionEvent evt) {

		System.out.println(evt);
		new Thread() {
			public void run() {
				button.setEnabled(true);;
				button_1.setEnabled(false);

				try {

					ser.closeServer();

				} catch (Exception e) {
					System.out.println("服务器关闭失败！");
					e.printStackTrace();
				}
			};
		}.start();
	}
}
