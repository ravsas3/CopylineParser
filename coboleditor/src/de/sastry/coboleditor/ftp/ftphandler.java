package de.sastry.coboleditor.ftp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.externaltools.internal.model.IExternalToolConstants;
import org.eclipse.ui.handlers.HandlerUtil;

@SuppressWarnings({ "unused", "restriction" })
public class ftphandler extends AbstractHandler {
    private QualifiedName path = new QualifiedName("html", "path");
    private FolderFTP FolderFTP=new FolderFTP();

	//private Shell shell;

	String name = "Maven clean install";
	String command = "${workspace_loc:/coboleditor/executables/ftpdocs.sh}";
	String params = "clean install";
	
@Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
	
	    //FTPDownloadFileDemo(ftpparams);
	    System.out.println("clicked for FTP!!");
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchPage page= workbench.getActiveWorkbenchWindow().getActivePage();
		TreeSelection selection= (TreeSelection) page.getSelection();
		IProject cobolProject = (IProject) selection.getFirstElement();
		IProjectDescription Description=null;
		try
		{
			Description=cobolProject.getDescription();
		}
		catch (Exception e) { System.out.println(e.getMessage());}
		System.out.println(Description.toString());
		String workingDir = System.getProperty("user.dir");		
		System.out.println(cobolProject.getLocation());
	    /*
		String[] ftpparams={"ftp.byethost16.com","b16_15961861","kunigan","put","/home/prabha/Filestobemoved","/htdocs"};	
	    ftpparams[4]=cobolProject.getLocation().toString();
	    ftppwd(ftpparams);
	    */	    
	    /*
		Runtime r = Runtime.getRuntime();
		Process p = null;
		try { p = r.exec("uname -a"); } catch (IOException e) { e.printStackTrace(); }
		try { p.waitFor(); } catch (InterruptedException e) {e.printStackTrace();}
		BufferedReader b = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		try {
			while ((line = b.readLine()) != null) {
			  System.out.println(line);
			}
		} catch (IOException e) { e.printStackTrace(); }

		try { b.close(); } catch (IOException e) { e.printStackTrace(); }  
		*/
	    return null;
  }

  private void ftppwd(String[] ftpparams) {
	  System.out.println("ek minute"+FolderFTP);
	  FolderFTP.Send(ftpparams);
}

private void createOutput(Shell shell, Object firstElement) {
    String directory;
    ICompilationUnit cu = (ICompilationUnit) firstElement;
    IResource res = cu.getResource();
    boolean newDirectory = true;
    directory = getPersistentProperty(res, path);

    if (directory != null && directory.length() > 0) {
      newDirectory = !(MessageDialog.openQuestion(shell, "Question",
          "Use the previous output directory?"));
    }
    if (newDirectory) {
      DirectoryDialog fileDialog = new DirectoryDialog(shell);
      directory = fileDialog.open();
    }
    if (directory != null && directory.length() > 0) {
      setPersistentProperty(res, path, directory);
      write(directory, cu);
    }
  }

  protected String getPersistentProperty(IResource res, QualifiedName qn) {
    try {
      return res.getPersistentProperty(qn);
    } catch (CoreException e) {
      return "";
    }
  }

  protected void setPersistentProperty(IResource res, QualifiedName qn,
      String value) {
    try {
      res.setPersistentProperty(qn, value);
    } catch (CoreException e) {
      e.printStackTrace();
    }
  }

  private void write(String dir, ICompilationUnit cu) {
    try {
      cu.getCorrespondingResource().getName();
      String test = cu.getCorrespondingResource().getName();
      // Need
      String[] name = test.split("\\.");
      String htmlFile = dir + "\\" + name[0] + ".html";
      FileWriter output = new FileWriter(htmlFile);
      @SuppressWarnings("resource")
	  BufferedWriter writer = new BufferedWriter(output);
      writer.write("<html>");
      writer.write("<head>");
      writer.write("</head>");
      writer.write("<body>");
      writer.write("<pre>");
      writer.write(cu.getSource());
      writer.write("</pre>");
      writer.write("</body>");
      writer.write("</html>");
      writer.flush();
    } catch (JavaModelException e) {
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
} 