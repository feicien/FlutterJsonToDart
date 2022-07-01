package cn.dxy.app.dxyjsontodart;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class RunCommandAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        if (project == null) {
            return;
        }
        CommandUtil.runFlutterPubWatch(project);
    }

}
