package action.impl;

import action.InstantTranslateAction;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import lombok.extern.slf4j.Slf4j;
import service.LanguageChecker;
import service.Proposer;

/**
 * Created by jojoldu@gmail.com on 2017. 5. 10.
 * Blog : http://jojoldu.tistory.com
 * Github : http://github.com/jojoldu
 */

@Slf4j
public class Replacer extends InstantTranslateAction {
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

        return proposer.propose(text, ServiceManager.getService(LanguageChecker.class)).stream()
                .map(LookupElementBuilder::create)
                .toArray(LookupElement[]::new);
    }
}
