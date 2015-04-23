package de.sastry.coboleditor.ui.contentassist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.StringTokenizer;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.CompletionProposal;
import org.eclipse.jface.text.contentassist.ContextInformation;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;

import de.dengot.coboleditor.ui.editor.*;

public class CobolContentAssistProcessor implements IContentAssistProcessor {
	
        private static HashMap<String, String> displayReplacementContent;
        public CobolContentAssistProcessor() {
            createFullDisplayReplacemnetContent();
        }        
        
        public void createFullDisplayReplacemnetContent() {
        	System.out.println("in createFullDisplayReplacementContent with :"+CobolSourceViewerConfiguration.KEYWORDS.length);
            displayReplacementContent = new HashMap<String, String>();
             
            for (int i = 0; i < CobolSourceViewerConfiguration.KEYWORDS.length; i++) {
              displayReplacementContent.put(CobolSourceViewerConfiguration.KEYWORDS[i],CobolSourceViewerConfiguration.KEYWORDS[i]);
            }
          }        
        /**
         * Return completion hints for the given offset.
         * Here we always return all supported markup symbols.
         */
        @Override
        public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int offset) {
 	        ArrayList<CompletionProposal> proposal = new ArrayList<CompletionProposal>();
            //Sort display
            LinkedList<String> ll = new LinkedList<String>(displayReplacementContent.keySet());
            Collections.sort(ll);
            // Create completion proposal collection
            String last=(String)compute_ContextInformation(viewer,offset);
            System.out.println("computeCompletionProposals - the last word is:"+last+" at an offset of "+offset);           
            for (String toDisplay : ll )
            {
                //System.out.println("test:"+toDisplay);        	   
                IContextInformation info = new ContextInformation(toDisplay, toDisplay);
                if ( toDisplay.indexOf(last.toUpperCase()) == 0 )
                {
                   CompletionProposal complete = new CompletionProposal(displayReplacementContent.get(toDisplay),
                		                                                offset-last.length(),
                 		                                                last.length(),
                 		                                                displayReplacementContent.get(toDisplay).length(),
                 		                                                null, toDisplay,info, "");
                   //proposal.add(new CompletionProposal(toDisplay, documentOffset, 0, toDisplay.length()));
                   proposal.add(complete);
                }
     }
     ICompletionProposal[] result = new ICompletionProposal[proposal.size()];
     result = proposal.toArray(result);
     //writelog("Content Assist processor was called!!");
     System.out.println("Content Assist processor was called!! and returns:"+result.length);
     return result;        	
        }

        @Override
        public IContextInformation[] computeContextInformation(ITextViewer viewer, int offset) {
                return null;
        }

        // completion hints triggered when the user types '='
        @Override
        public char[] getCompletionProposalAutoActivationCharacters() {
                return new char[] { '=' };
        }

        @Override
        public char[] getContextInformationAutoActivationCharacters() {
                return null;
        }

        @Override
        public String getErrorMessage() {
                return null;
        }

        @Override
        public IContextInformationValidator getContextInformationValidator() {
                return null;
        }
        
        public String compute_ContextInformation(final ITextViewer viewer, final int documentOffset) {
              // no context infopops
              IDocument doc = viewer.getDocument();
              String last = lastWord( doc, documentOffset );
              System.out.println("the last word is:"+last);
              return last;
          }        

        protected String lastWord( IDocument doc, int offset ) {
            try {
                   int n = 0;
                   for( n = offset - 1; n > 0; n-- )
                   {
                      char c = doc.getChar( n );
                      if( Character.isWhitespace( c ) || c == '(' )
                      return doc.get( n + 1, offset - n - 1 ).toLowerCase();
                   }
                   // Check the case that the 'last word' is in fact the
                   // first word of the document, which we are interested in.
                   if( n == 0 ) return doc.get( 0, offset ).toLowerCase();
                }
            catch( Exception e )
            {
                  e.printStackTrace();
            }
            return "";
        } 
        
        static void loadMnemonicNames () throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(CobolContentAssistProcessor.class.getResourceAsStream("keywords.txt")));
            ArrayList<String> mnemonic = new ArrayList<String> ();
            String nextLine = null;
            while ( null != (nextLine = br.readLine())) {
                   // if keyword line
                   if (nextLine.trim().length() > 0 && nextLine.toUpperCase().equals(nextLine))
                   {
                      StringTokenizer st = new StringTokenizer(nextLine," \t", false);
                      final String word = st.nextToken();
                      mnemonic.add(word);
                   }
            }
            String [] mnemonicArray = new String [mnemonic.size()];
            mnemonicArray = mnemonic.toArray(mnemonicArray);
            //TODO CobolContentAssistProcessor.displayReplacementContent = mnemonicArray;
            System.out.println("loaded");
          }
}
