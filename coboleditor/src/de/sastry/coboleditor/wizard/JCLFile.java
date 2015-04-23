package de.sastry.coboleditor.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class JCLFile extends WizardPage {
  private Text PGMNAME;
  private Composite container;

  public JCLFile() {
    super("Executable File details");
    setTitle("Executable File details");
    setDescription("Details of Executable script to be added...");
    setControl(PGMNAME);
  }

  @Override
  public void createControl(Composite parent) {
    container = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    container.setLayout(layout);
    layout.numColumns = 2;
    Label label1 = new Label(container, SWT.NONE);
    label1.setText("Executable script");

    PGMNAME = new Text(container, SWT.BORDER | SWT.SINGLE);
    PGMNAME.setText("IN0800");
    PGMNAME.addKeyListener(new KeyListener() { @Override public void keyPressed(KeyEvent e) { /* TODO Auto-generated method stub */ } @Override public void keyReleased(KeyEvent e) { if (!PGMNAME.getText().isEmpty()) { setPageComplete(true); } } });
    
    GridData gd = new GridData(GridData.FILL_HORIZONTAL);
    PGMNAME.setLayoutData(gd);

    //check.addSelectionListener(new SelectionAdapter() { @Override public void widgetSelected(SelectionEvent event) { Button btn = (Button) event.getSource(); System.out.println(btn.getSelection()); } });    

    // required to avoid an error in the system
    setControl(container);
    //setPageComplete(false);
  }

  public String getPGMNAME() {
    return PGMNAME.getText();
  }
  
  public String scriptname() {
	String cattype;
	String runfreq="d";
	String sequence=PGMNAME.getText().substring(3).toLowerCase();
	System.out.println("scriptname:"+PGMNAME.getText());
	if (PGMNAME.getText().substring(0,2).equals("IN") )
	{
		cattype="dep";
	}
	else if (PGMNAME.getText().substring(0,2).equals("CI") )
	{
		cattype="cif";
	}	
	else if (PGMNAME.getText().substring(0,2).equals("BR") )
	{
		cattype="lon";
	}
	else if (PGMNAME.getText().substring(1,2).equals("SY") )
	{
		cattype="cfp";
	}
	else if (PGMNAME.getText().substring(0,2).equals("SP") )
	{
		cattype="spm";
	}	
	else
	{
		cattype="adh";
	}
	String scriptname=cattype+runfreq+sequence;
	return scriptname;
  }
  
  public void checkentry() {
  }    
}