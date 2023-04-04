package cn.dxy.app.dxyjsontodart;

import cn.dxy.app.dxyjsontodart.setting.FlutterJsonToDartSetting;
import com.google.gson.*;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.LanguageTextField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.event.ItemEvent;

public class InputJsonDialog extends DialogWrapper {

    private final Project myProject;
    private JTextField mClassNameField;
    private LanguageTextField mJsonTextField;
    private JPanel mainPanel;
    private JSplitPane jSplitPane;
    private LanguageTextField mDartTextField;
    private JCheckBox createToJson;
    private JCheckBox defaultValue;
    private JCheckBox useJsonKeyName;
    private JCheckBox explicitToJson;
    private JCheckBox fieldRename;
    private JCheckBox copyWith;


    public InputJsonDialog(Project project) {
        super(project, false); // use current window as parent
        this.myProject = project;
        setTitle("Generate Dart Data Class Annotated with JsonSerializable");
        init();
        getOKAction().setEnabled(false);



        mJsonTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(com.intellij.openapi.editor.event.@NotNull DocumentEvent event) {
                // 文档发生改变回调
                enableOkAction();
            }
        });

        mClassNameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                enableOkAction();
            }
        });

        FlutterJsonToDartSetting instance = FlutterJsonToDartSetting.getInstance();
        createToJson.setSelected(instance.createToJson);
        defaultValue.setSelected(instance.defaultValue);
        useJsonKeyName.setSelected(instance.useJsonKeyName);
        explicitToJson.setSelected(instance.explicitToJson);
        fieldRename.setSelected(instance.fieldRename);
        copyWith.setSelected(instance.copyWith);


        createToJson.addItemListener(e -> {
            instance.createToJson = e.getStateChange() == ItemEvent.SELECTED;
            enableOkAction();
        });
        defaultValue.addItemListener(e -> {
            instance.defaultValue = e.getStateChange() == ItemEvent.SELECTED;
            enableOkAction();
        });
        useJsonKeyName.addItemListener(e -> {
            instance.useJsonKeyName = e.getStateChange() == ItemEvent.SELECTED;
            enableOkAction();
        });
        explicitToJson.addItemListener(e -> {
            instance.explicitToJson = e.getStateChange() == ItemEvent.SELECTED;
            enableOkAction();
        });
        fieldRename.addItemListener(e -> {
            instance.fieldRename = e.getStateChange() == ItemEvent.SELECTED;
            enableOkAction();
        });
        copyWith.addItemListener(e -> {
            instance.copyWith = e.getStateChange() == ItemEvent.SELECTED;
            enableOkAction();
        });
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return mainPanel;
    }

    private void enableOkAction() {
        String jsonText = mJsonTextField.getText().trim();

        String className = mClassNameField.getText().trim();

        if (jsonText.isEmpty()) {
            setErrorText("Please input JSON text", mJsonTextField);
            getOKAction().setEnabled(false);
            return;
        }

        if (className.isEmpty()) {
            setErrorText("Please input Class name", mClassNameField);
            getOKAction().setEnabled(false);
            return;
        }

        boolean isJson = inputIsValidJson(jsonText);
        if (!isJson) {
            setErrorText("Please check the JSON text is correct", mJsonTextField);
            getOKAction().setEnabled(false);
            return;
        }

        setErrorText(null);
        getOKAction().setEnabled(true);

        FlutterJsonToDartSetting instance = FlutterJsonToDartSetting.getInstance();
        String generatorClassContent = JsonHelper.generateDartClassesToString(className, jsonText, instance);
        mDartTextField.setText(generatorClassContent);
    }

    private boolean inputIsValidJson(String string) {
        try {
            JsonElement jsonElement = JsonParser.parseString(string);
            return jsonElement.isJsonObject() || jsonElement.isJsonArray();
        } catch (JsonSyntaxException ignored) {
        }
        return false;
    }


    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        //重新该方法，可以设置默认获取焦点
        return mJsonTextField;
    }

    @Nullable
    public String getClassName() {
        return getExitCode() == 0 ? mClassNameField.getText().trim() : null;
    }

    @Nullable
    public String getJsonText() {
        return getExitCode() == 0 ? mJsonTextField.getText().trim() : null;
    }


    private void createUIComponents() {
        mJsonTextField = new JsonLanguageTextField(myProject);
        mJsonTextField.setEnabled(true);


        mDartTextField = new DartLanguageTextField(myProject);
        mDartTextField.setEnabled(true);
    }
}
