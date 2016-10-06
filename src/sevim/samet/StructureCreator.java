package sevim.samet;

import com.intellij.ide.actions.CreateClassAction;
import com.intellij.ide.structureView.StructureViewModel;
import com.intellij.ide.structureView.StructureViewModelBase;
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.JavaDirectoryService;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by Samet Sevim on 21.9.2016.
 */
public class StructureCreator extends AnAction{
    public StructureCreator() {
        super("Spring Structure Creator");
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        PsiFile psiFile = anActionEvent.getData(PlatformDataKeys.PSI_FILE);
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);

        if(psiFile == null ){
            Messages.showMessageDialog(project,"Please select a model class to create repository and service","Ok",Messages.getErrorIcon());
        }
        else{
            String modelName = psiFile.getName().substring(0,psiFile.getName().lastIndexOf("."));
            PsiClass serviceClass =JavaDirectoryService.getInstance().createClass(psiFile.getContainingDirectory(),modelName+"Service");
            PsiClass repositoryClass =JavaDirectoryService.getInstance().createInterface(psiFile.getContainingDirectory(),modelName+"Repository");
        }
    }
}
