package de.sastry.coboleditor.wizard;

import java.util.LinkedList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

public class JCLWizardPage extends Wizard implements INewWizard {

	  protected JCLFile jclfile;
	  public LinkedList<IWizardPage> pages = new LinkedList<IWizardPage>();
	  //private NewConfigFileWizardPage newFileWizardPage;
	
	  public JCLWizardPage() {
	    super();
	    setNeedsProgressMonitor(true);
	  }

	  @Override
	  public String getWindowTitle() {
	    return "Export My Data";
	  }

	  @Override
	  public void addPages() {
		jclfile = new JCLFile();
	    addPage(jclfile);
	  }

	@Override
	public boolean performFinish() {
	    // Print the result to the console
	    System.out.println(jclfile.getPGMNAME());
	    String scriptname=jclfile.scriptname();
  	    String srcfolder="";
  		srcfolder="cat/";
  		String Extn="cat";
	    IProject project = getCurrentSelectedProject();
	    try { COBobjCreator.createFiles(project,scriptname,Extn,srcfolder); } catch (CoreException e) { e.printStackTrace(); }
	    return true;
	}

	public static IProject getCurrentSelectedProject() {
	    IProject project = null;
	    ISelectionService selectionService = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getSelectionService();

	    ISelection selection = selectionService.getSelection();

	    if(selection instanceof IStructuredSelection) {
	        Object element = ((IStructuredSelection)selection).getFirstElement();

	        if (element instanceof IResource) {
	            project= ((IResource)element).getProject();
	        }
	        /*
	        else if (element instanceof PackageFragmentRoot) {
	            IJavaProject jProject = ((PackageFragmentRoot)element).getJavaProject();
	            project = jProject.getProject();
	        } else if (element instanceof IJavaElement) {
	            IJavaProject jProject= ((IJavaElement)element).getJavaProject();
	            project = jProject.getProject();
	        }*/
	    }
	    return project;
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		// TODO Auto-generated method stub
		
	}	
}
