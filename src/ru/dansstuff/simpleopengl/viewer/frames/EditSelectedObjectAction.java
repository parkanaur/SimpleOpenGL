package ru.dansstuff.simpleopengl.viewer.frames;

import ru.dansstuff.simpleopengl.misc.helpers.ObjectCreationFrameFactory;
import ru.dansstuff.simpleopengl.objects.GLObject;
import ru.dansstuff.simpleopengl.objects.windows.TypeBaseFrame;

import javax.swing.*;

public class EditSelectedObjectAction implements FrameCallbackAction {
    @Override
    public void doAction(CurrentObjectSelectionFrame frame) {
        if (frame.getObjectJList().isSelectionEmpty()) {
            JOptionPane.showMessageDialog(frame, "No object selected");
            return;
        }

        GLObject selectedObject = frame.getObjectJList().getSelectedValue();
        JFrame editFrame = ObjectCreationFrameFactory.getFrame
                (selectedObject.getClass(), frame.getParent(), false, selectedObject);
    }
}
