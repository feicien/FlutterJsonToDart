package cn.dxy.app.dxyjsontodart;

import com.google.gson.*;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.DocumentAdapter;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBDimension;
import com.intellij.util.ui.JBInsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;

public class InputJsonDialog extends DialogWrapper {

    private JTextField mClassNameField;
    private JTextArea mJsonTextField;


    public InputJsonDialog() {
        super(false); // use current window as parent
        setTitle("Generate Dart Data Class Annotated with JsonSerializable");
        init();
        getOKAction().setEnabled(false);
    }


    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel messagePanel = new JPanel(new BorderLayout());

        JLabel textLabel = new JLabel("Please input the JSON text and Class name");

        Box hBox1 = Box.createHorizontalBox();
        hBox1.add(textLabel);
        hBox1.add(Box.createHorizontalGlue());


        JBLabel jsonLabel = new JBLabel("JSON text:");

        Box hBox2 = Box.createHorizontalBox();
        hBox2.add(jsonLabel);
        hBox2.add(Box.createHorizontalGlue());

        //格式化按钮栏
        JButton formatButton = new JButton("Format");
        formatButton.setHorizontalAlignment(SwingConstants.CENTER);
        formatButton.addActionListener(e -> handleFormatJSONString());
        hBox2.add(formatButton);

        Box vBox = Box.createVerticalBox();
        vBox.add(hBox1);
        vBox.add(hBox2);

        messagePanel.add(vBox, BorderLayout.NORTH);


        //创建内容输入区域
        mJsonTextField = new JTextArea(15, 50);


        mJsonTextField.setMargin(JBInsets.create(10, 10));
        mJsonTextField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                enableOkAction();
            }
        });

        JBScrollPane jbScrollPane = new JBScrollPane(mJsonTextField);
        jbScrollPane.setPreferredSize(new JBDimension(700, 350));
        jbScrollPane.setAutoscrolls(true);
        jbScrollPane.setHorizontalScrollBarPolicy(JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jbScrollPane.setVerticalScrollBarPolicy(JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        messagePanel.add(jbScrollPane, BorderLayout.CENTER);


        Box horizontalBox = Box.createHorizontalBox();

        JBLabel classNameLabel = new JBLabel("Class name: ");
        horizontalBox.add(classNameLabel);

        mClassNameField = new JTextField();
        mClassNameField.setPreferredSize(new JBDimension(400, 40));
        mClassNameField.getDocument().addDocumentListener(new DocumentAdapter() {
            @Override
            protected void textChanged(@NotNull DocumentEvent e) {
                enableOkAction();
            }
        });
        horizontalBox.add(mClassNameField);

        messagePanel.add(horizontalBox, BorderLayout.SOUTH);


        return messagePanel;
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


}
