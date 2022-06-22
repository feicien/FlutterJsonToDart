package cn.dxy.app.dxyjsontodart;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.plugins.terminal.ShellTerminalWidget;
import org.jetbrains.plugins.terminal.TerminalToolWindowFactory;
import org.jetbrains.plugins.terminal.TerminalView;

import java.io.IOException;


public class CommandUtil {


    private static void showInfo(String message) {
        Messages.showErrorDialog(message, "DxyJsonToDart");
    }

    public static void runFlutterPubRun(AnActionEvent e) {

        String workingDirectory = e.getProject().getBasePath();

        TerminalView terminalView = TerminalView.getInstance(e.getProject());
        ToolWindow window = ToolWindowManager.getInstance(e.getProject()).getToolWindow(TerminalToolWindowFactory.TOOL_WINDOW_ID);
        if (window == null) {
            showInfo("Please check that the following two plugins are installed: Terminal and Shell Script");
            return;
        }


        String command1 = "dart pub add json_annotation";
        String command2 = "dart pub add json_serializable --dev";
        String command3 = "dart pub add build_runner --dev";
        String command4 = "flutter pub run build_runner build --delete-conflicting-outputs";

        ShellTerminalWidget terminalWidget = terminalView.createLocalShellWidget(workingDirectory, "Local");
        try {
            terminalWidget.executeCommand(command1);
        } catch (IOException exception) {
            showInfo("Cannot run command:" + command1 + "  " + exception.getMessage());
        }

        try {
            terminalWidget.executeCommand(command2);
        } catch (IOException exception) {
            showInfo("Cannot run command:" + command2 + "  " + exception.getMessage());
        }

        try {
            terminalWidget.executeCommand(command3);
        } catch (IOException exception) {
            showInfo("Cannot run command:" + command3 + "  " + exception.getMessage());
        }

        try {
            terminalWidget.executeCommand(command4);
        } catch (IOException exception) {
            showInfo("Cannot run command:" + command4 + "  " + exception.getMessage());
        }
    }
}
