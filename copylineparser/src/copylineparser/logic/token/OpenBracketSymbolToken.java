/*
 * Created on 05.10.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package copylineparser.logic.token;


/**
 * @author 010627
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OpenBracketSymbolToken extends SymbolToken
{
    private final String SYMBOL = "(";
    
    public OpenBracketSymbolToken(int position)
    {
        super(position);
    }
    
    public String getContents()
    {
       return new String(SYMBOL);
    }

    public int length()
    {
        return SYMBOL.length();
    }
    
    public boolean isOpenBracketSymbolToken()
    {
        return true;
    }

}