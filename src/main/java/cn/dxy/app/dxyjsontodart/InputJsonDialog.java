package cn.dxy.app.dxyjsontodart;

import com.google.gson.*;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.LanguageTextField;
import com.intellij.util.ui.JBDimension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;

public class InputJsonDialog extends DialogWrapper {

    private final Project myProject;
    private JTextField mClassNameField;
    private LanguageTextField mJsonTextField;
    private JPanel mainPanel;
    private JButton formatButton;


    public InputJsonDialog(Project project) {
        super(project, false); // use current window as parent
        this.myProject = project;
        setTitle("Generate Dart Data Class Annotated with JsonSerializable");
        init();
        getOKAction().setEnabled(false);

        formatButton.addActionListener(e -> handleFormatJSONString());


        mJsonTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void documentChanged(com.intellij.openapi.editor.event.@NotNull DocumentEvent event) {
                enableOkAction();
            }
        });

        mClassNameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                enableOkAction();
            }
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
    }

    private boolean inputIsValidJson(String string) {
        try {
            JsonElement jsonElement = JsonParser.parseString(string);
            return jsonElement.isJsonObject() || jsonElement.isJsonArray();
        } catch (JsonSyntaxException ignored) {
        }
        return false;
    }

    private void handleFormatJSONString() {
        String jsonText = mJsonTextField.getText().trim();
        if (!jsonText.isEmpty()) {
            try {
                Gson prettyGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                JsonElement jsonElement = prettyGson.fromJson(jsonText, JsonElement.class);
                String formatJSON = prettyGson.toJson(jsonElement);
                mJsonTextField.setText(formatJSON);
            } catch (Exception ignored) {
            }
        }

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
        mJsonTextField.setPreferredSize(new JBDimension(700, 350));
        mJsonTextField.setEnabled(true);
    }
}
