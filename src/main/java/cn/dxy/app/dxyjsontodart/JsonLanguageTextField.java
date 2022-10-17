package cn.dxy.app.dxyjsontodart;

import com.intellij.codeInsight.folding.CodeFoldingManager;
import com.intellij.json.JsonLanguage;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.project.Project;
import com.intellij.ui.LanguageTextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//https://intellij-support.jetbrains.com/hc/en-us/community/posts/4407879092370-search-bar-in-LanguageTextField-can-not-be-fixed
public class JsonLanguageTextField extends LanguageTextField {

    public JsonLanguageTextField(@Nullable Project project) {
        super(JsonLanguage.INSTANCE, project, "", false);
    }

    @Override
    protected @NotNull EditorEx createEditor() {
        EditorEx editor = super.createEditor();
        editor.setVerticalScrollbarVisible(true);
        editor.setHorizontalScrollbarVisible(true);
        editor.setCaretEnabled(true);
        //
        editor.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(com.intellij.openapi.editor.event.@NotNull DocumentEvent event) {
                ApplicationManager.getApplication().invokeLater(() -> CodeFoldingManager.getInstance(getProject()).updateFoldRegions(editor), ModalityState.NON_MODAL);
            }
        });
        EditorSettings settings = editor.getSettings();
//        settings.setGutterIconsShown(true);
//        settings.setSmartHome(true);
        settings.setLineNumbersShown(true);
//        settings.setIndentGuidesShown(true);
        //如果某一个内容比较长，自动换行
        settings.setUseSoftWraps(true);
        settings.setAutoCodeFoldingEnabled(true);
        settings.setFoldingOutlineShown(true);
        settings.setAllowSingleLogicalLineFolding(true);
//        settings.setRightMarginShown(true);
//        settings.setRightMargin(100);
//        settings.setCaretRowShown(true);
//        settings.setLineMarkerAreaShown(true);
//        settings.setDndEnabled(true);
        //在底部添加额外 5 行
        settings.setAdditionalLinesCount(5);

        return editor;
    }
}
