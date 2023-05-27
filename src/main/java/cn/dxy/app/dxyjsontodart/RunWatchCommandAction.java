package cn.dxy.app.dxyjsontodart;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

public class RunWatchCommandAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent event) {
        CommandUtil.runBuildRunnerCommandWatch(event, false, true);
    }

}
