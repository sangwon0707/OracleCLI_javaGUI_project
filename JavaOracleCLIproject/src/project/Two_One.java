package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Two_oneFrame extends JFrame {
    JButton btn_member;
    JButton btn_non_member;

    public Two_oneFrame() {
        JPanel p_buttons = new JPanel(); // p_member와 p_non_member를 감싸는 패널
        JPanel p_member = new JPanel();
        JPanel p_non_member = new JPanel();
        JPanel SouthJPanel = new JPanel();

        btn_member = new JButton(new ImageIcon("./image/member.png"));
        btn_non_member = new JButton(new ImageIcon("./image/member_non.png"));
        JButton btn_foodback = new JButton("뒤로가기");
        btn_foodback.setBackground(new Color(240,240,240));
        btn_foodback.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        SouthJPanel.add(btn_foodback);
        SouthJPanel.setLayout(new FlowLayout());

        // 뒤로가기 버튼의 폰트 설정
        btn_foodback.setFont(new Font("고딕", Font.BOLD, 20));

        p_member.setLayout(new BorderLayout());
        p_non_member.setLayout(new BorderLayout());

        p_member.add(new JLabel("    "), BorderLayout.NORTH);
        p_member.add(new JLabel("    "), BorderLayout.SOUTH);
        p_member.add(new JLabel("    "), BorderLayout.EAST);
        p_member.add(new JLabel("    "), BorderLayout.WEST);
        p_member.add(btn_member, BorderLayout.CENTER);

        p_non_member.add(new JLabel("    "), BorderLayout.NORTH);
        p_non_member.add(new JLabel("    "), BorderLayout.SOUTH);
        p_non_member.add(new JLabel("    "), BorderLayout.EAST);
        p_non_member.add(new JLabel("    "), BorderLayout.WEST);
        p_non_member.add(btn_non_member, BorderLayout.CENTER);

        p_buttons.setLayout(new GridLayout(1, 2)); // 1행 2열로 나열
        p_buttons.add(p_member);
        p_buttons.add(p_non_member);

        btn_member.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 현재 프레임을 닫음
                new LoginFrame2();
            }
        });

        btn_non_member.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                IntroFrame.us = 100;
                new RoomPayFrame2();
            }
        });

        btn_foodback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
               new IntroFrame();
            }
        });

        // 레이아웃 설정
        setLayout(new BorderLayout());
        add(SouthJPanel, BorderLayout.SOUTH);
        add(p_buttons, BorderLayout.CENTER); // p_buttons를 중앙에 추가

        // 기타 설정
        
        p_buttons.setBackground(Color.WHITE);
        p_member.setBackground(Color.WHITE);
        p_non_member.setBackground(Color.WHITE);
        SouthJPanel.setBackground(Color.WHITE);

        setTitle("회원/비회원");
        setSize(700, 1000);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}