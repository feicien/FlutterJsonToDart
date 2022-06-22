package cn.dxy.app.dxyjsontodart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.Messages.InputDialog;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.util.ui.JBDimension;
import com.intellij.util.ui.JBEmptyBorder;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;


public class InputJsonDialog extends InputDialog {


    private JTextField classNameInput;

    public InputJsonDialog() {
        super("请输入你要转换的 json 数据", "把 Json 转成 json_serializable 模型类",
                Messages.getInformationIcon(), "", null);
    }


    @Override
    protected @NotNull JPanel createMessagePanel() {
        JPanel messagePanel = new JPanel(new BorderLayout());
        if (myMessage != null) {
            JComponent textComponent = createTextComponent();
            messagePanel.add(textComponent, BorderLayout.NORTH);
        }
        myField = createTextFieldComponent();


        JPanel classNameInputContainer = createLinearLayoutVertical();
        JBLabel classNameTitle = new JBLabel("Class Name: ");
        classNameTitle.setBorder(new JBEmptyBorder(5, 0, 5, 0));
        addComponentIntoVerticalBoxAlignmentLeft(classNameInputContainer, classNameTitle);


        classNameInput = new JTextField();
        classNameInput.setPreferredSize(new JBDimension(400, 40));
//        classNameInput.getDocument().addDocumentListener(new DocumentAdapter() {
//            @Override
//            protected void textChanged(@NotNull DocumentEvent e) {
//                getOKAction().setEnabled(myInputValidator.checkInput(myField.getText()));
//            }
//        });

        addComponentIntoVerticalBoxAlignmentLeft(classNameInputContainer, classNameInput);
        classNameInputContainer.setPreferredSize(new JBDimension(500, 56));


        JComponent createScrollableTextComponent = createMyScrollableTextComponent();
        JPanel jsonInputContainer = createLinearLayoutVertical();
        jsonInputContainer.setPreferredSize(new JBDimension(700, 400));
        jsonInputContainer.setBorder(new JBEmptyBorder(5, 0, 5, 5));
        JBLabel jsonTitle = new JBLabel("JSON Text:");
        jsonTitle.setBorder(new JBEmptyBorder(5, 0, 5, 0));
        addComponentIntoVerticalBoxAlignmentLeft(jsonInputContainer, jsonTitle);
        addComponentIntoVerticalBoxAlignmentLeft(jsonInputContainer, createScrollableTextComponent);


        JPanel centerContainer = new JPanel();
        BoxLayout centerBoxLayout = new BoxLayout(centerContainer, BoxLayout.PAGE_AXIS);
        centerContainer.setLayout(centerBoxLayout);
        addComponentIntoVerticalBoxAlignmentLeft(centerContainer, classNameInputContainer);
        addComponentIntoVerticalBoxAlignmentLeft(centerContainer, jsonInputContainer);
        messagePanel.add(centerContainer, BorderLayout.CENTER);

        JButton formatButton = new JButton("Format");
        formatButton.setHorizontalAlignment(SwingConstants.CENTER);
        formatButton.addActionListener(e -> handleFormatJSONString());

        //底部按钮栏
        JPanel settingContainer = new JPanel();
        settingContainer.setBorder(new JBEmptyBorder(0, 5, 5, 7));
        BoxLayout boxLayout = new BoxLayout(settingContainer, BoxLayout.LINE_AXIS);
        settingContainer.setLayout(boxLayout);
        settingContainer.add(Box.createHorizontalGlue());

        settingContainer.add(formatButton);
        messagePanel.add(settingContainer, BorderLayout.SOUTH);
        return messagePanel;
    }

    private void addComponentIntoVerticalBoxAlignmentLeft(JPanel centerContainer, Component component) {
        if (centerContainer.getLayout() instanceof  BoxLayout) {

            Box hBox = Box.createHorizontalBox();
            hBox.add(component);
            hBox.add(Box.createHorizontalGlue());
            centerContainer.add(hBox);
        }
    }

    @Override
    protected JTextComponent createTextFieldComponent() {
        JTextArea jTextArea = new JTextArea(15, 50);
        jTextArea.setMinimumSize(new JBDimension(750, 400));
//        jTextArea.lineWrap = true
//        jTextArea.wrapStyleWord = true
//        jTextArea.autoscrolls = true
        return jTextArea;
    }


    private JComponent createMyScrollableTextComponent() {
        JBScrollPane jbScrollPane = new JBScrollPane(myField);
        jbScrollPane.setPreferredSize(new JBDimension(700, 350));
        jbScrollPane.setAutoscrolls(true);
        jbScrollPane.setHorizontalScrollBarPolicy(JBScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jbScrollPane.setVerticalScrollBarPolicy(JBScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return jbScrollPane;
    }



    public void handleFormatJSONString() {
        String currentText = myField.getText();//text ?: ""
        if (!currentText.isEmpty()) {
            try {
                Gson prettyGson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
                JsonElement jsonElement = prettyGson.fromJson(currentText, JsonElement.class);
                String formatJSON = prettyGson.toJson(jsonElement);
                myField.setText(formatJSON);
            } catch (Exception ignored) {
            }
        }

    }


    public @Nullable @NlsSafe String getClassName() {
        return getExitCode() == 0 ? classNameInput.getText().trim() : null;
    }

    public @Nullable @NlsSafe String getJson() {
        return getExitCode() == 0 ? myField.getText().trim() : null;
    }


    JPanel createLinearLayoutVertical() {
        JPanel container = new JPanel();
        BoxLayout boxLayout = new BoxLayout(container, BoxLayout.PAGE_AXIS);
        container.setLayout(boxLayout);
        return container;
    }

}
