import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

public class HelloAction extends AnAction {
    public HelloAction() {
        super("Hello");
    }

    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();


        //getting the selected text
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        //replacing unwanted symbols to a basic comma
        selectedText = selectedText.replace("、", ",").replace("，", ",");

        Messages.showMessageDialog(project, selectedText, "Greeting", Messages.getInformationIcon());
    }
}