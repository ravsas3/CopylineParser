/*
 * Created on 04.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.scanner;

import copylineparser.logic.exception.ScannerException;
import copylineparser.logic.token.CommentToken;

/**
 * @author 010627
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class ScannerCommentState extends ScannerValueState
{

    public ScannerCommentState(Scanner scanner)
    {
        super(scanner);
    }

    public void scan()
    {
        if (scanner.startsWith("\n"))
        {
            scanner.appendToken(new CommentToken(startPosition, scanner.getSubString(startPosition)));
            scanner.setState(scanner.theScannerStandardState);
            return;
        } else
        {
            scanner.skip(1);
            return;
        }
    }

    public void finalToken(int position) throws ScannerException
    {
        scanner.appendToken(new CommentToken(startPosition, scanner.getSubString(startPosition)));
        //throw new ScannerException("Comment", startPosition);
    }
}