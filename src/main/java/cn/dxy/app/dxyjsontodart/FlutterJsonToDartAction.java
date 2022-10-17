package cn.dxy.app.dxyjsontodart;

import com.intellij.notification.*;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFileFactory;
import com.jetbrains.lang.dart.DartFileType;
import com.jetbrains.lang.dart.psi.DartFile;
import org.jetbrains.annotations.NotNull;


public class FlutterJsonToDartAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        if (project == null) {
            return;
        }

        Navigatable data = LangDataKeys.NAVIGATABLE.getData(event.getDataContext());
        if (data == null) {
            return;
        }
        PsiDirectory directory = (PsiDirectory) data;

        //弹一个 dialog，用户可以输入 json

        InputJsonDialog dialog = new InputJsonDialog(project);
        dialog.show();

        String inputClassName = dialog.getClassName();
        String inputJsonStr = dialog.getJsonText();

        if (inputClassName == null || inputClassName.isEmpty()){
            return;
        }
        if (inputJsonStr == null || inputJsonStr.isEmpty()) {
            return;
        }
        String generatorClassContent = JsonHelper.generateDartClassesToString(inputClassName, inputJsonStr);



        CommandProcessor.getInstance().executeCommand(project, () -> {
            //添加 dart 文件
            ApplicationManager.getApplication().runWriteAction(() -> {
                //文件名，小写，可以带下划线
                String fileName = StringUtils.getFileName(inputClassName);
                PsiFileFactory psiFileFactory = PsiFileFactory.getInstance(project);
                DartFile file = (DartFile) psiFileFactory.createFileFromText(fileName + ".dart", DartFileType.INSTANCE, generatorClassContent);
                directory.add(file);
            });
        }, "FlutterJsonToDart", "FlutterJsonToDart");

        showNotify(project, "Dart Data Class file generated successful");

        // 添加 json_serializable 依赖，并执行相关命令
        CommandUtil.runFlutterPubRun(project);

    }


    private void showNotify(Project project, String content) {
        NotificationGroupManager.getInstance()
                .getNotificationGroup("FlutterJsonToDart")
                .createNotification(content, NotificationType.INFORMATION)
                .notify(project);
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        Project project = e.getProject();
        VirtualFile file = e.getData(CommonDataKeys.VIRTUAL_FILE);
        boolean enabled = project != null && file != null && file.isDirectory();
        //项目不为空，并且点击的是一个目录时，显示 action
        e.getPresentation().setEnabledAndVisible(enabled);
    }
}
