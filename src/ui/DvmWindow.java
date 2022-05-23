package ui3;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public abstract class DvmWindow extends JFrame implements ActionListener {
    private static final String WINDOW_TITLE = "T2 OOPT DVM";
    //20 drink list
    protected static final int MAX_ITEM = 20;
    protected static final int MAX_LOCAL_ITEM = 7;
    protected static final String[] drinkList = {"콜라", "사이다",
            "녹차", "홍차",
            "밀크티", "탄산수",
            "보리차", "캔커피",
            "물", "에너지드링크",
            "바닷물", "식혜",
            "아이스티", "딸기주스",
            "오렌지주스", "포도주스",
            "이온음료", "아메리카노",
            "핫초코", "카페라떼"
    };
    protected static Controller controller;
//    protected JFrame frame2 = new JFrame();
    protected Container frame = this.getContentPane();
    protected static final JLabel vmID = new JLabel("          VM's ID           ");
    protected static JPanel panel;
    
//    =================================================================
//    		GridBagConstraints needs to initiate here,
//    		So it will re-initiate every time we move the window
    
    protected static GridBagConstraints c;

//    =================================================================
//    		Integer i is initiated here
//    		So, the loop will not run again every time we move the window
    
    protected static Integer i=0;
    
//    =================================================================
    
    private static final int frameWidth = 500;
    private static final int frameHeight = 500;

    protected DvmWindow(Controller controller) {
        this(controller, WINDOW_TITLE);
    }

    protected DvmWindow(Controller controller, String title) {
        super(title);
        DvmWindow.controller = controller;
        this.setSize(frameWidth, frameHeight);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        this.setVisible(true);
//        this.add(frame);
    }

    protected abstract void init();

    public abstract void actionPerformed(ActionEvent e);
    
}