package de.dengot.coboleditor.ui.editor;

//import org.eclipse.jface.text.IDocument;
//import org.eclipse.ui.IEditorInput;

import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;
//import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

//import de.dengot.coboleditor.logic.CobolParser;
import de.dengot.coboleditor.model.CobolProgram;
import de.dengot.coboleditor.ui.outline.CobolContentOutlinePage;

public class CobolEditor extends AbstractDecoratedTextEditor
{
	private CobolContentOutlinePage outline;

	private CobolProgram model;

	public CobolEditor()
	{
		super();
		setSourceViewerConfiguration(new CobolSourceViewerConfiguration(this));
		System.out.println("it should be");
	}

	public Object getAdapter(@SuppressWarnings("rawtypes") Class required)
	{
		if (IContentOutlinePage.class.equals(required))
		{
			return this.getOutline();
		}
		return super.getAdapter(required);
	}

	private CobolContentOutlinePage getOutline()
	{
		if (this.outline == null)
		{
			this.outline = new CobolContentOutlinePage(this);
		}
		return this.outline;
	}

	public void setModel(CobolProgram program)
	{
		this.model = program;
		this.getOutline().setModel(program);
	}

	public CobolProgram getModel()
	{
		/*
		if (this.model == null)
		{
			IEditorInput input = this.getEditorInput();
			IDocumentProvider prov = this.getDocumentProvider();
			IDocument doc = prov.getDocument(input);
			CobolParser parser = new CobolParser();
			System.out.println("This is where everything is loaded!!"+input.getName()+doc);
			doc=Stripcolumns(input.getName(),doc);
			CobolProgram program = parser.parse(input.getName(), doc);
			setModel(program);
		}*/
		System.out.println("getModel...");
		return this.model;
	}
	
	/*
	public IDocument Stripcolumns(String filename,IDocument doc) {
		int lineCount = doc.getNumberOfLines();
		int lineNo=0;
		IRegion region = null;
		String textline = null;
		String displayText = "";
		System.out.println("going to strip columns...");
		while (lineNo < lineCount)
		{
			try { region = doc.getLineInformation(lineNo);                                         } catch (BadLocationException e) { e.printStackTrace(); }
			try { textline = doc.get(region.getOffset(), region.getLength());                      } catch (BadLocationException e) { e.printStackTrace(); }
			try { displayText=displayText+textline.substring(CobolParser.COLUMN_PARSE_OFFSET-1)+"\n";} catch (Exception e) { displayText=displayText+"\n"; }			
			lineNo++;
		}
		if ( !filename.equals("BR0000.COB"))
		{
		   doc.set(displayText);
		}
		System.out.println("Done...");			
		return doc;
    }
	*/
}
