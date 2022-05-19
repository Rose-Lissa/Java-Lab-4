package ru.nsu.fit.carfactory.view;

import ru.nsu.fit.carfactory.CarFactoryRunner;
import ru.nsu.fit.carfactory.application.model.Configuration;
import ru.nsu.fit.carfactory.infrastructure.controller.FactoryInfoController;
import ru.nsu.fit.carfactory.infrastructure.port.FactoryInfoView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class FactoryView extends JFrame implements FactoryInfoView {
    private JSlider suppliersSpeedSlider;
    private JSlider dealersSpeedSlider;
    private JLabel nAccessories;
    private JLabel nEngines;
    private JLabel nCarBodies;
    private JLabel nCars;
    private JLabel nSoldCars;
    private JPanel mainPanel;
    Configuration configuration = Configuration.getInit();


    public FactoryView(FactoryInfoController controller) throws IOException {
        final int width = 400;
        final int height = 200;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        setBounds(((int)toolkit.getScreenSize().getWidth() - width) / 2,
                ((int)toolkit.getScreenSize().getHeight() - height) / 2,
                width,
                height);
        setContentPane(mainPanel);


        suppliersSpeedSlider.setMajorTickSpacing(1000);
        suppliersSpeedSlider.setMinorTickSpacing(100);
        suppliersSpeedSlider.setMinimum(0);
        suppliersSpeedSlider.setMaximum(5000);
        suppliersSpeedSlider.setValue(5500 - configuration.getSuppliersTimeoutPerMillisecond());

        dealersSpeedSlider.setMajorTickSpacing(1000);
        dealersSpeedSlider.setMinorTickSpacing(100);
        dealersSpeedSlider.setMinimum(0);
        dealersSpeedSlider.setMaximum(5000);
        dealersSpeedSlider.setValue(5500 - configuration.getSuppliersTimeoutPerMillisecond());

        suppliersSpeedSlider.addChangeListener(event -> {
            var slider = (JSlider) event.getSource();
            int timeoutPerMilliSecond = slider.getMaximum() - slider.getValue();
            controller.changeSuppliersTimeoutPerMilliSec(timeoutPerMilliSecond);
        });

        dealersSpeedSlider.addChangeListener(event ->{
            var slider = (JSlider) event.getSource();
            int timeoutPerMilliSecond = slider.getMaximum() - slider.getValue();
            controller.changeDealersTimeoutPerMilliSec(timeoutPerMilliSecond);
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                CarFactoryRunner.endJob();
            }
        });
    }

    public void setNAccessories(int n) {
        nAccessories.setText(String.valueOf(n));
    }

    public void setNEngines(int n) {
        nEngines.setText(String.valueOf(n));
    }

    public void setNCarBodies(int n) {
        nCarBodies.setText(String.valueOf(n));
    }

    public void setNCars(int n) {
        nCars.setText(String.valueOf(n));
    }

    public void setNSoldCars(int n) {
        nSoldCars.setText(String.valueOf(n));
    }

}
