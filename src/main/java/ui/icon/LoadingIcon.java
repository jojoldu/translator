package ui.icon;

import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.AnimatedIcon;

import javax.swing.*;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 8.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class LoadingIcon extends AnimatedIcon {

    private static final int CYCLE_LENGTH = 800;

    private static final Icon[] ICONS = {
            IconLoader.getIcon("/process/fs/step_1@2x.png"),
            IconLoader.getIcon("/process/fs/step_2@2x.png"),
            IconLoader.getIcon("/process/fs/step_3@2x.png"),
            IconLoader.getIcon("/process/fs/step_4@2x.png"),
            IconLoader.getIcon("/process/fs/step_5@2x.png"),
            IconLoader.getIcon("/process/fs/step_6@2x.png"),
            IconLoader.getIcon("/process/fs/step_7@2x.png"),
            IconLoader.getIcon("/process/fs/step_8@2x.png"),
            IconLoader.getIcon("/process/fs/step_9@2x.png"),
            IconLoader.getIcon("/process/fs/step_10@2x.png"),
            IconLoader.getIcon("/process/fs/step_11@2x.png"),
            IconLoader.getIcon("/process/fs/step_12@2x.png"),
            IconLoader.getIcon("/process/fs/step_13@2x.png"),
            IconLoader.getIcon("/process/fs/step_14@2x.png"),
            IconLoader.getIcon("/process/fs/step_15@2x.png"),
            IconLoader.getIcon("/process/fs/step_16@2x.png"),
            IconLoader.getIcon("/process/fs/step_17@2x.png"),
            IconLoader.getIcon("/process/fs/step_18@2x.png")
    };

    private static final Icon STEP_PASSIVE = IconLoader.getIcon("/process/fs/step_passive@2x.png");

    public LoadingIcon() {
        super("Querying Process", ICONS, STEP_PASSIVE, CYCLE_LENGTH);
    }
}