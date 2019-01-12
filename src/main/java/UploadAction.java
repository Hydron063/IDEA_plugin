import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;

import java.io.*;
import java.security.GeneralSecurityException;


public class UploadAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        //getting the selected text
        Editor editor = e.getData(PlatformDataKeys.EDITOR);
        String selectedText = editor.getSelectionModel().getSelectedText();
        selectedText = selectedText.replace("、", ",").replace("，", ",");
        //printing the text in the file
        String fileName =  Messages.showInputDialog("Name the file","Input",Messages.getInformationIcon()) +".txt";
        File file = new File(fileName);

        try {
            FileWriter fw = new FileWriter(file);
            fw.write(selectedText);
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            Uploader.upload(file);
            Messages.showMessageDialog(e.getProject(), selectedText, "Your text", Messages.getInformationIcon());
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (GeneralSecurityException e1) {
            e1.printStackTrace();
        }

    }
}
