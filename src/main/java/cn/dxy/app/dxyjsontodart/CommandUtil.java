package cn.dxy.app.dxyjsontodart;

import cn.dxy.app.dxyjsontodart.setting.FlutterJsonToDartSetting;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory;
import org.jetbrains.plugins.terminal.TerminalView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;


public class CommandUtil {


    private static void showInfo(String message) {
        Messages.showErrorDialog(message, "FlutterJsonToDart");
    }

    public static void runFlutterPubRun(@NotNull AnActionEvent event) {
        List<String> commands = new ArrayList<>();

        String command = getDependencyCommand(event);
        if (command != null) {
            commands.add(command);
        }

        FlutterJsonToDartSetting instance = FlutterJsonToDartSetting.getInstance();
        boolean runBuilderRunner = instance.runBuilderRunner;

        if (runBuilderRunner) {
            boolean supportFvm = instance.supportFvm;
            commands.add(getBuildRunnerCommand(supportFvm, false));
        }

        executeCommand(event, commands);
    }

    private static void executeCommand(@NotNull AnActionEvent event, List<String> commands) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        if (project == null) {
            return;
        }

        String workingDirectory = project.getBasePath();

        TerminalView terminalView = TerminalView.getInstance(project);
        ToolWindow window = ToolWindowManager.getInstance(project).getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID);
        if (window == null) {
            showInfo("Please check that the following two plugins are installed: Terminal and Shell Script");
            return;
        }

        ShellTerminalWidget terminalWidget = terminalView.createLocalShellWidget(workingDirectory, "Local");

        for (String command : commands) {
            try {
                terminalWidget.executeCommand(command);
            } catch (IOException exception) {
                showInfo("Cannot run command:" + command + "  " + exception.getMessage());
            }
        }
    }


    public static void runBuildRunnerCommandWatch(@NotNull AnActionEvent event, boolean supportFvm, boolean isWatch) {
        List<String> commands = new ArrayList<>();
        commands.add(getBuildRunnerCommand(supportFvm, isWatch));

        executeCommand(event, commands);
    }

    private static String getBuildRunnerCommand(boolean supportFvm, boolean isWatch) {
        return (supportFvm ? "fvm " : "") + "dart run build_runner " + (isWatch ? "watch" : "build") + " --delete-conflicting-outputs";
    }

    private static String getDependencyCommand(@NotNull AnActionEvent event) {
        //判断是 pubspec.yaml 文件中是否已经添加了 json_annotation 、json_serializable、build_runner 依赖
        boolean hasJsonAnnotation = false;
        boolean hasJsonSerializable = false;
        boolean hasBuildRunner = false;

        try {
            Project project = event.getData(PlatformDataKeys.PROJECT);
            if (project == null) {
                return null;
            }
            String workingDirectory = project.getBasePath();
            File file = new File(workingDirectory, "pubspec.yaml");
            String yaml = Files.readString(Path.of(file.getPath()));
            List<FlutterDependencyBean> dependencies = YamlUtils.getDependencies(yaml);

            for (FlutterDependencyBean dependency : dependencies) {
                if ("json_annotation".equals(dependency.packageName)) {
                    hasJsonAnnotation = true;
                }
                if ("json_serializable".equals(dependency.packageName)) {
                    hasJsonSerializable = true;
                }
                if ("build_runner".equals(dependency.packageName)) {
                    hasBuildRunner = true;
                }
            }
        } catch (Exception ignored) {
        }

        if (hasJsonAnnotation && hasJsonSerializable && hasBuildRunner) {
            return null;
        }

        FlutterJsonToDartSetting instance = FlutterJsonToDartSetting.getInstance();
        boolean supportFvm = instance.supportFvm;

        StringBuilder sb = new StringBuilder();

        if (supportFvm) {
            sb.append("fvm ");
        }
        sb.append("dart pub add");

        if (!hasJsonAnnotation) {
            sb.append(" json_annotation");
        }
        if (!hasJsonSerializable) {
            sb.append(" dev:json_serializable");
        }

        if (!hasBuildRunner) {
            sb.append(" dev:build_runner");
        }

        return sb.toString();
    }

}
