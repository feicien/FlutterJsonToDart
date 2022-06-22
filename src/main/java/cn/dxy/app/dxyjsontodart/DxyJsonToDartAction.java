package cn.dxy.app.dxyjsontodart;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFileFactory;
import com.jetbrains.lang.dart.DartFileType;
import com.jetbrains.lang.dart.psi.DartFile;
import org.jetbrains.annotations.NotNull;


public class DxyJsonToDartAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);

        //弹一个 dialog，用户可以输入 json

        InputJsonDialog dialog = new InputJsonDialog();
        dialog.show();

        String className = dialog.getClassName();
        String json = dialog.getJson();

        String generatorClassContent = JsonHelper.generateDartClassesToString(className, json);

        PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
        Navigatable data = LangDataKeys.NAVIGATABLE.getData(event.getDataContext());

        PsiDirectory directory = (PsiDirectory) data;


        CommandProcessor.getInstance().executeCommand(project, () -> {
            //添加 dart 文件
            ApplicationManager.getApplication().runWriteAction(() -> {
                //文件名，小写，可以带下划线
                String fileName = JsonHelper.getFileName(className);
                DartFile file = (DartFile) psiFileFactory.createFileFromText(fileName + ".dart", DartFileType.INSTANCE, generatorClassContent);
                directory.add(file);
            });
        }, "DxyJsonToDart", "DxyJsonToDart");

        showNotify(project, "Dart Data Class file generated successful");

    }


    private void showNotify(Project project, String notifyMessage) {
        NotificationGroup notificationGroup = new NotificationGroup("JSON to Dart Class", NotificationDisplayType.BALLOON, true);
        ApplicationManager.getApplication().invokeLater(() -> {
            Notification notification = notificationGroup.createNotification(notifyMessage, NotificationType.INFORMATION);
            Notifications.Bus.notify(notification, project);
        });
    }
}
