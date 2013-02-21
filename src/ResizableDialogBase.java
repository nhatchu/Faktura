import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Rectangle;
import java.util.prefs.Preferences;


class ResizableDialogBase extends javax.swing.JDialog
{
    public ResizableDialogBase(java.awt.Frame parent, String title, boolean modal)
    {
        super(parent, title, modal);
        _Title = title;
        _DefaultRect = new Rectangle(0, 0,  400, 300);
        addWindowListener(new WindowL());
        restoreDialogPosition();
    }
    
    public ResizableDialogBase(java.awt.Frame parent, String title, boolean modal, Rectangle rect)
    {
        super(parent, title, modal);
        _Title = title;
        _DefaultRect = rect;
        addWindowListener(new WindowL());
        restoreDialogPosition();
    }
    
    public void saveDialogPosition()
    {
        Rectangle rect;
        rect = getBounds();
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs = prefs.node(_Title);
        prefs.putInt("X", rect.x);
        prefs.putInt("Y", rect.y);
        prefs.putInt("Width", rect.width);
        prefs.putInt("Height", rect.height);
    }
    
    public void restoreDialogPosition()
    {
        Rectangle rect = new Rectangle();
        Preferences prefs = Preferences.userNodeForPackage(getClass());
        prefs = prefs.node(_Title);
        rect.x = prefs.getInt("X", _DefaultRect.x);
        rect.y = prefs.getInt("Y", _DefaultRect.y);
        rect.width = prefs.getInt("Width", _DefaultRect.width);
        rect.height = prefs.getInt("Height", _DefaultRect.height);
        setBounds(rect);
    }
    
    private String _Title;
    private Rectangle _DefaultRect;
    
    private class WindowL extends WindowAdapter
    {
        public void windowClosing(WindowEvent e)
        {
            saveDialogPosition();
        }
    }
}
