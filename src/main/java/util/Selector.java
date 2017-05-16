package util;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.VisualPosition;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 10.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Selector {

    @Nullable
    public static Point extractPoint(Editor editor) {
        Point point = null;

        if (editor != null) {
            point = editor.visualPositionToXY(toVisualPosition(editor));
        }

        return point;
    }

    private static final int LINE_INTERVAL = 1;

    public static VisualPosition toVisualPosition(Editor editor) {
        VisualPosition start = editor.getSelectionModel().getSelectionStartPosition();
        VisualPosition end = editor.getSelectionModel().getSelectionEndPosition();
        int line = 0;
        int column = 0;

        if (end != null && start != null) {
            line = end.getLine() + LINE_INTERVAL;
            column = start.getColumn() + ((end.column - start.getColumn())/2);
        }

        return new VisualPosition(line, column);
    }

    public static JComponent getCurrentComponent(AnActionEvent e){
        Editor editor = PlatformDataKeys.EDITOR.getData(e.getDataContext());

        if (editor != null) {
            return editor.getContentComponent();
        }

        return null;
    }

    public static String getSelectedText(AnActionEvent e) {
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        if (editor != null) {
            SelectionModel selectionModel = editor.getSelectionModel();
            String selectedText = selectionModel.getSelectedText();
            return StringUtils.isNotEmpty(selectedText)? selectedText : getAutoSelectedText(selectionModel);
        }

        return "";
    }

    private static String getAutoSelectedText(SelectionModel selectionModel){
        selectionModel.selectWordAtCaret(false);
        return selectionModel.getSelectedText();
    }
}
