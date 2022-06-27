package cn.dxy.app.dxyjsontodart;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory;
import org.jetbrains.plugins.terminal.TerminalView;

import java.io.IOException;
import java.util.List;


public class CommandUtil {


    private static void showInfo(String message) {
        Messages.showErrorDialog(message, "DxyJsonToDart");
    }

    public static void runFlutterPubRun(Project project) {

        String workingDirectory = project.getBasePath();

        TerminalView terminalView = TerminalView.getInstance(project);
        ToolWindow window = ToolWindowManager.getInstance(project).getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID);
        if (window == null) {
            showInfo("Please check that the following two plugins are installed: Terminal and Shell Script");
            return;
        }

        //判断是 pubspec.yaml 文件中是否已经添加了 json_annotation 、json_serializable、build_runner 依赖
        boolean hasJsonAnnotation = false;
        boolean hasJsonSerializable = false;
        boolean hasBuildRunner = false;

        PsiFile[] filesByName = FilenameIndex.getFilesByName(project, "pubspec.yaml", GlobalSearchScope.allScope(project));
        if (filesByName.length > 0) {
            PsiFile psiFile = filesByName[0];
            String yaml = psiFile.getText();
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
        }


        ShellTerminalWidget terminalWidget = terminalView.createLocalShellWidget(workingDirectory, "Local");
        executeCommand(terminalWidget, hasJsonAnnotation, "dart pub add json_annotation");
        executeCommand(terminalWidget, hasJsonSerializable, "dart pub add json_serializable --dev");
        executeCommand(terminalWidget, hasBuildRunner, "dart pub add build_runner --dev");
        executeCommand(terminalWidget, false, "flutter pub run build_runner build --delete-conflicting-outputs");
    }

    private static void executeCommand(ShellTerminalWidget terminalWidget, boolean hasAddDependency, String command) {
        if (hasAddDependency) {
            return;
        }
        try {
            terminalWidget.executeCommand(command);
        } catch (IOException exception) {
            showInfo("Cannot run command:" + command + "  " + exception.getMessage());
        }
    }
}
