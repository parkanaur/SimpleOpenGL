package ru.dansstuff.simpleopengl.viewer.frames;

import javax.swing.*;

public class SelectCurrentObjectAction implements FrameCallbackAction {
    @Override
    public void doAction(CurrentObjectSelectionFrame frame) {
        if (frame.getObjectJList().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(frame, "No object selected");
            return;
        }

        frame.getParent().setCurrentObject(frame.getObjectJList().getSelectedValue());
    }
}
