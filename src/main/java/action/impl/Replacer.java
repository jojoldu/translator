package action.impl;

import action.TranslateAction;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Proposer;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 10.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

public class Replacer extends TranslateAction {
    private static final Logger logger = LoggerFactory.getLogger(Replacer.class);

    private Editor editor;

    @Override
    protected void init(AnActionEvent e) {
        editor = CommonDataKeys.EDITOR.getData(e.getDataContext());
    }

    @Override
    protected void action(String text, String translatedText) {
        if(editor != null && editor.getProject() != null){
            LookupManager lookupManager = LookupManager.getInstance(editor.getProject());
            ApplicationManager.getApplication().invokeLater(() -> lookupManager.showLookup(editor, getProposeList(translatedText)));
        }
    }

    private LookupElement[] getProposeList(String text){
        Proposer proposer = ServiceManager.getService(Proposer.class);

        return proposer.propose(text).stream()
                .map(LookupElementBuilder::create)
                .toArray(LookupElement[]::new);
    }
}
