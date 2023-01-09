package cn.dxy.app.dxyjsontodart;

import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.project.Project;
import com.intellij.ui.LanguageTextField;
import com.jetbrains.lang.dart.DartLanguage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//https://intellij-support.jetbrains.com/hc/en-us/community/posts/4407879092370-search-bar-in-LanguageTextField-can-not-be-fixed
public class DartLanguageTextField extends LanguageTextField {

    public DartLanguageTextField(@Nullable Project project) {
        super(DartLanguage.INSTANCE, project, "", false);
    }

    @Override
    protected @NotNull EditorEx createEditor() {
        EditorEx editor = super.createEditor();
        editor.setVerticalScrollbarVisible(true);
        editor.setHorizontalScrollbarVisible(true);
        editor.setCaretEnabled(false);

        EditorSettings settings = editor.getSettings();
        settings.setLineNumbersShown(true);
        //如果某一个内容比较长，自动换行
        settings.setUseSoftWraps(true);
        settings.setAutoCodeFoldingEnabled(true);
        settings.setFoldingOutlineShown(true);
        settings.setAllowSingleLogicalLineFolding(true);
        //在底部添加额外 5 行
        settings.setAdditionalLinesCount(5);


        return editor;
    }
}
